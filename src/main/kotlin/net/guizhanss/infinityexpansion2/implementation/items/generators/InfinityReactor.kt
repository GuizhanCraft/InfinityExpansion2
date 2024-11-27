@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.items.generators

import io.github.seggan.sf4k.item.builder.asMaterialType
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
import me.mrCookieSlime.Slimefun.api.BlockStorage
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.items.MachineLore
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.logging.Level

class InfinityReactor(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    defaultProduction: Int,
) : AbstractReactor(itemGroup, itemStack, recipeType, recipe, defaultProduction) {

    private val voidIngotDurationSetting =
        IntRangeSetting(this, "void-ingot-duration", 1, 28_800, Int.MAX_VALUE) // 4 hours
    private val infinityIngotDurationSetting =
        IntRangeSetting(this, "infinity-ingot-duration", 1, 172_800, Int.MAX_VALUE) // 24 hours

    init {
        addItemSetting(voidIngotDurationSetting, infinityIngotDurationSetting)
    }

    override fun postRegister() {
        super.postRegister()

        // check the duration of void ingot and infinity ingot
        val voidIngotDuration = voidIngotDurationSetting.value
        val infinityIngotDuration = infinityIngotDurationSetting.value
        if (infinityIngotDuration % voidIngotDuration != 0) {
            InfinityExpansion2.log(Level.WARNING, "Invalid item settings for $id:")
            InfinityExpansion2.log(
                Level.WARNING,
                "The duration of infinity ingot must be divisible by the duration of void ingot. "
            )
            InfinityExpansion2.log(Level.WARNING, "Using default values now, please update the config.")
            voidIngotDurationSetting.update(voidIngotDurationSetting.defaultValue)
            infinityIngotDurationSetting.update(infinityIngotDurationSetting.defaultValue)
        }
    }

    override fun setup(preset: BlockMenuPreset) {
        preset.drawBackground(BACKGROUND)
        preset.drawBackground(VOID_BORDER_ITEM, VOID_BORDER)
        preset.drawBackground(INFINITY_BORDER_ITEM, INFINITY_BORDER)
    }

    override fun getInputSlots() = intArrayOf(VOID_INPUT, INFINITY_INPUT)

    override fun getInputSlots(menu: DirtyChestMenu, item: ItemStack): IntArray {
        return if (item.isVoidIngot()) intArrayOf(VOID_INPUT)
        else if (item.isInfinityIngot()) intArrayOf(INFINITY_INPUT)
        else intArrayOf()
    }

    override fun getGeneratedOutput(l: Location, data: Config): Int {
        val menu = BlockStorage.getInventory(l) ?: return 0
        val progress = l.getProgress()
        val voidInput = menu.getItemInSlot(VOID_INPUT)
        val infinityInput = menu.getItemInSlot(INFINITY_INPUT)

        if (progress == 0) { // fresh start
            if (infinityInput == null || !infinityInput.isInfinityIngot()) {
                menu.setStatus { NEED_INFINITY }
                return 0
            }
            if (voidInput == null || !voidInput.isVoidIngot()) {
                menu.setStatus { NEED_VOID }
                return 0
            }

            // input is valid, start new progress
            menu.consumeItem(VOID_INPUT)
            menu.consumeItem(INFINITY_INPUT)
            menu.setStatus { info(infinityIngotDurationSetting.value, voidIngotDurationSetting.value) }
            l.setProgress(1)
            return getEnergyProduction()
        }

        if (progress > infinityIngotDurationSetting.value) { // done
            menu.setStatus { GuiItems.PRODUCING }
            l.setProgress(0)
            return getEnergyProduction()
        }

        if (Math.floorMod(progress, voidIngotDurationSetting.value) == 0) { // need void
            if (voidInput == null || !voidInput.isVoidIngot()) {
                menu.setStatus { NEED_VOID }
                return 0
            }

            menu.consumeItem(VOID_INPUT)
        }

        // progressing
        l.setProgress(progress + 1)
        menu.setStatus {
            info(
                infinityIngotDurationSetting.value - progress,
                voidIngotDurationSetting.value - Math.floorMod(progress, voidIngotDurationSetting.value)
            )
        }
        return getEnergyProduction()
    }

    private fun ItemStack.isVoidIngot() = SlimefunItem.getByItem(this)?.id == IEItems.VOID_INGOT.itemId
    private fun ItemStack.isInfinityIngot() = SlimefunItem.getByItem(this)?.id == IEItems.INFINITY_INGOT.itemId

    private fun BlockMenu.setStatus(itemStack: () -> ItemStack) {
        if (hasViewer()) {
            replaceExistingItem(STATUS_SLOT, itemStack())
        }
    }

    override fun getInformationalItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyProductionPerTick(getEnergyProduction()),
        infoVoid(voidIngotDurationSetting.value),
        infoInfinity(infinityIngotDurationSetting.value),
    )

    companion object {

        private val BACKGROUND = intArrayOf(3, 4, 5)
        private val VOID_BORDER = intArrayOf(0, 2)
        private val INFINITY_BORDER = intArrayOf(6, 8)
        private const val VOID_INPUT = 1
        private const val INFINITY_INPUT = 7
        private const val STATUS_SLOT = 4

        private val VOID_BORDER_ITEM = InfinityExpansion2.localization.getGuiItem(
            Material.BLUE_STAINED_GLASS_PANE.asMaterialType(), "ir_border_void"
        )
        private val INFINITY_BORDER_ITEM = InfinityExpansion2.localization.getGuiItem(
            Material.LIGHT_BLUE_STAINED_GLASS_PANE.asMaterialType(), "ir_border_infinity"
        )
        private val NEED_VOID = InfinityExpansion2.localization.getGuiItem(
            Material.RED_STAINED_GLASS_PANE.asMaterialType(), "ir_need_void"
        )
        private val NEED_INFINITY = InfinityExpansion2.localization.getGuiItem(
            Material.RED_STAINED_GLASS_PANE.asMaterialType(), "ir_need_infinity"
        )

        private fun infoVoid(ticks: Int) = InfinityExpansion2.localization.getGuiItem(
            Material.LIME_STAINED_GLASS_PANE.asMaterialType(),
            "ir_duration_void",
            "${ChatColor.GRAY}${MachineLore.format(ticks)}"
        )

        private fun infoInfinity(ticks: Int) = InfinityExpansion2.localization.getGuiItem(
            Material.LIME_STAINED_GLASS_PANE.asMaterialType(),
            "ir_duration_infinity",
            "${ChatColor.GRAY}${MachineLore.format(ticks)}"
        )

        private fun info(infinity: Int, void: Int) = InfinityExpansion2.localization.getGuiItem(
            Material.LIME_STAINED_GLASS_PANE.asMaterialType(),
            "ir_info",
            InfinityExpansion2.localization.getString("items._UI_IR_INFO.lore-overrides.1"),
            "${ChatColor.GRAY}${MachineLore.format(infinity)}",
            InfinityExpansion2.localization.getString("items._UI_IR_INFO.lore-overrides.3"),
            "${ChatColor.GRAY}${MachineLore.format(void)}",
        )
    }
}
