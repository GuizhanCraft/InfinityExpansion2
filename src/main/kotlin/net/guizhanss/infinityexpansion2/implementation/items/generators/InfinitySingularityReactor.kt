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

class InfinitySingularityReactor(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    defaultProduction: Int,
) : AbstractReactor(itemGroup, itemStack, recipeType, recipe, defaultProduction) {

    private val infinitySingularityDurationSetting =
        IntRangeSetting(this, "infinity-singularity-duration", 1, 5_184_000, Int.MAX_VALUE) // 30 days

    init {
        addItemSetting(infinitySingularityDurationSetting)
    }

    override fun setup(preset: BlockMenuPreset) {
        preset.drawBackground(BACKGROUND)
        preset.drawBackground(BORDER_ITEM, INPUT_BORDER)
    }


    override fun getInputSlots() = intArrayOf(INPUT)

    override fun getInputSlots(menu: DirtyChestMenu, item: ItemStack): IntArray {
        return if (item.isInfinitySingularity()) intArrayOf(INPUT)
        else intArrayOf()
    }

    override fun getGeneratedOutput(l: Location, data: Config): Int {
        val menu = BlockStorage.getInventory(l) ?: return 0
        val progress = l.getProgress()
        val infinitySingInput = menu.getItemInSlot(INPUT)

        if (progress == 0) { // fresh start
            if (infinitySingInput == null || !infinitySingInput.isInfinitySingularity()) {
                menu.setStatus { NEED }
                return 0
            }

            // input is valid, start new progress
            menu.consumeItem(INPUT)
            menu.setStatus { info(infinitySingularityDurationSetting.value) }
            l.setProgress(1)
            return getEnergyProduction()
        }

        if (progress > infinitySingularityDurationSetting.value) { // done
            menu.setStatus { GuiItems.PRODUCING }
            l.setProgress(0)
            return getEnergyProduction()
        }

        // progressing
        l.setProgress(progress + 1)
        menu.setStatus {
            info(infinitySingularityDurationSetting.value - progress)
        }
        return getEnergyProduction()
    }

    private fun ItemStack.isInfinitySingularity() =
        SlimefunItem.getByItem(this)?.id == IEItems.INFINITY_SINGULARITY.itemId

    private fun BlockMenu.setStatus(itemStack: () -> ItemStack) {
        if (hasViewer()) {
            replaceExistingItem(STATUS_SLOT, itemStack())
        }
    }

    override fun getInformationalItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyProductionPerTick(getEnergyProduction()),
        infoInfinitySing(infinitySingularityDurationSetting.value),
    )

    companion object {

        private val BACKGROUND = intArrayOf(0, 1, 2, 3, 4, 8)
        private val INPUT_BORDER = intArrayOf(5, 7)
        private const val INPUT = 6
        private const val STATUS_SLOT = 2

        private val BORDER_ITEM = InfinityExpansion2.localization.getGuiItem(
            Material.LIGHT_BLUE_STAINED_GLASS_PANE.asMaterialType(), "isr_border"
        )
        private val NEED = InfinityExpansion2.localization.getGuiItem(
            Material.RED_STAINED_GLASS_PANE.asMaterialType(), "isr_need"
        )

        private fun infoInfinitySing(ticks: Int) = InfinityExpansion2.localization.getGuiItem(
            Material.LIME_STAINED_GLASS_PANE.asMaterialType(),
            "isr_duration",
            "${ChatColor.GRAY}${MachineLore.format(ticks)}"
        )

        private fun info(infinitySing: Int) = InfinityExpansion2.localization.getGuiItem(
            Material.LIME_STAINED_GLASS_PANE.asMaterialType(),
            "isr_info",
            InfinityExpansion2.localization.getString("items._UI_ISR_INFO.lore-overrides.1"),
            "${ChatColor.GRAY}${MachineLore.format(infinitySing)}",
        )
    }
}
