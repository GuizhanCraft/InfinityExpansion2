@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.items.tools

import io.github.seggan.sf4k.item.builder.asMaterialType
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu
import net.guizhanss.guizhanlib.common.utils.RandomUtil
import net.guizhanss.guizhanlib.slimefun.machines.TickingMenuBlock
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.utils.bukkitext.isWaterLogged
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import net.guizhanss.infinityexpansion2.utils.bukkitext.withAmount
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import kotlin.random.Random

class StrainerBase(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>
) : TickingMenuBlock(itemGroup, itemStack, recipeType, recipe), InformationalRecipeDisplayItem {

    private val tickRateSetting = IntRangeSetting(this, "tick-rate", 1, 10, 120)

    init {
        addItemSetting(tickRateSetting)
    }

    override fun setup(preset: BlockMenuPreset) {
        LAYOUT.setupPreset(preset)
    }

    override fun getInputSlots(menu: DirtyChestMenu, item: ItemStack): IntArray {
        return if (SlimefunItem.getByItem(item) is Strainer) inputSlots
        else intArrayOf()
    }

    override fun getInputSlots() = LAYOUT.inputSlots

    override fun getOutputSlots() = LAYOUT.outputSlots

    override fun tick(b: Block, menu: BlockMenu) {
        if (isDisabledIn(b.world)) return
        if (!b.isWaterLogged()) return
        if (InfinityExpansion2.sfTickCount() % tickRateSetting.value != 0) return

        val strainerItem = menu.getItemInSlot(inputSlots[0])
        val strainer = SlimefunItem.getByItem(strainerItem) as? Strainer
        if (strainer == null || strainer.isDisabledIn(b.world)) {
            if (menu.hasViewer()) {
                menu.replaceExistingItem(LAYOUT.statusSlot, GuiItems.INVALID_INPUT)
            }
            return
        }

        if (menu.hasViewer()) {
            menu.replaceExistingItem(LAYOUT.statusSlot, GuiItems.COLLECTING)
        }
        if (!RandomUtil.testChance(strainer.chance, 100)) return

        if (RandomUtil.testChance(1, 10_000)) {
            menu.pushItem(POTATO_FISH, *outputSlots)
        }

        val output = OUTPUTS.random()
        if (!menu.fits(output, *outputSlots)) {
            menu.replaceExistingItem(LAYOUT.statusSlot, GuiItems.NO_ROOM)
            return
        }

        menu.pushItem(output.clone(), *outputSlots)

        // strainer reduce durability
        val bound = strainerItem.getEnchantmentLevel(Enchantment.getByName("unbreaking")!!) +
            3 * strainerItem.getEnchantmentLevel(Enchantment.getByName("mending")!!) +
            1
        if (Random.nextInt(bound) == 0) {
            val meta = strainerItem.itemMeta as Damageable
            val newDamage = meta.damage + 1

            if (newDamage >= Material.FISHING_ROD.maxDurability.toInt()) {
                menu.consumeItem(inputSlots[0])
            } else {
                meta.damage = newDamage
                strainerItem.itemMeta = meta
                menu.replaceExistingItem(inputSlots[0], strainerItem)
            }
        }
    }

    override fun getInformationalItems() = listOf(GuiItems.tickRate(tickRateSetting.value))

    override fun getDefaultDisplayRecipes() = OUTPUTS.flatMap { listOf(ANY_STRAINER_ITEM, it) }

    companion object {

        private val LAYOUT = MenuLayout.SINGLE_INPUT

        private val ANY_STRAINER_ITEM = InfinityExpansion2.localization.getGuiItem(
            Material.FISHING_ROD.asMaterialType(),
            "any_strainer"
        )

        private val OUTPUTS = listOf(
            Material.STICK.toItem(),
            Material.SAND.toItem(),
            Material.GRAVEL.toItem(),
            Material.QUARTZ.toItem(),
            Material.REDSTONE.toItem(),
            Material.EMERALD.toItem(),
            SlimefunItems.MAGNESIUM_DUST.withAmount(1),
            SlimefunItems.COPPER_DUST.withAmount(1),
            SlimefunItems.COPPER_DUST.withAmount(1),
            SlimefunItems.SILVER_DUST.withAmount(1),
            SlimefunItems.ALUMINUM_DUST.withAmount(1),
            SlimefunItems.LEAD_DUST.withAmount(1),
            SlimefunItems.IRON_DUST.withAmount(1),
            SlimefunItems.GOLD_DUST.withAmount(1),
            SlimefunItems.TIN_DUST.withAmount(1),
            SlimefunItems.ZINC_DUST.withAmount(1),
        )

        private val POTATO_FISH = InfinityExpansion2.localization.getItem(
            "POTATO_FISH",
            Material.POTATO
        )
    }
}
