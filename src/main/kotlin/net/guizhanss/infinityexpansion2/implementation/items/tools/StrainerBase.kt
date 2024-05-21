@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.items.tools

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import io.github.thebusybiscuit.slimefun4.libraries.dough.inventory.InvUtils
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu
import net.guizhanss.guizhanlib.slimefun.machines.TickingMenuBlock
import net.guizhanss.guizhanlib.utils.RandomUtil
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.BlockUtils
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import kotlin.random.Random

class StrainerBase(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>
) : TickingMenuBlock(itemGroup, itemStack, recipeType, recipe), RecipeDisplayItem {
    companion object {
        private val INPUT_BORDER = intArrayOf(0, 2)
        private const val INPUT_SLOT = 1
        private val BACKGROUND = intArrayOf(3, 5, 6, 7, 8)
        private const val STATUS_SLOT = 4
        private val OUTPUT_BORDER = intArrayOf(
            9, 10, 11, 12, 13, 14, 15, 16, 17,
            45, 46, 47, 48, 49, 50, 51, 52, 53
        )
        private val OUTPUT_SLOTS = intArrayOf(
            18, 19, 20, 21, 22, 23, 24, 25, 26,
            27, 28, 29, 30, 31, 32, 33, 34, 35,
            36, 37, 38, 39, 40, 41, 42, 43, 44,
        )

        private val OUTPUTS = listOf(
            ItemStack(Material.STICK),
            ItemStack(Material.SAND),
            ItemStack(Material.GRAVEL),
            ItemStack(Material.QUARTZ),
            ItemStack(Material.REDSTONE),
            ItemStack(Material.EMERALD),
            SlimefunItemStack(SlimefunItems.MAGNESIUM_DUST, 1),
            SlimefunItemStack(SlimefunItems.COPPER_DUST, 1),
            SlimefunItemStack(SlimefunItems.COPPER_DUST, 1),
            SlimefunItemStack(SlimefunItems.SILVER_DUST, 1),
            SlimefunItemStack(SlimefunItems.ALUMINUM_DUST, 1),
            SlimefunItemStack(SlimefunItems.LEAD_DUST, 1),
            SlimefunItemStack(SlimefunItems.IRON_DUST, 1),
            SlimefunItemStack(SlimefunItems.GOLD_DUST, 1),
            SlimefunItemStack(SlimefunItems.TIN_DUST, 1),
            SlimefunItemStack(SlimefunItems.ZINC_DUST, 1),
        )

        private val POTATO_FISH = InfinityExpansion2.localization.getItem(
            "POTATO_FISH",
            Material.POTATO
        )
    }

    private val tickRateSetting = IntRangeSetting(this, "tick-rate", 1, 10, 120)

    init {
        addItemSetting(tickRateSetting)
    }

    override fun getRecipeSectionLabel(p: Player) = InfinityExpansion2.integrationService.getLore(p, "collect")

    override fun getDisplayRecipes() = OUTPUTS

    override fun setup(preset: BlockMenuPreset) {
        preset.drawBackground(BACKGROUND)
        preset.drawBackground(ChestMenuUtils.getInputSlotTexture(), INPUT_BORDER)
        preset.drawBackground(ChestMenuUtils.getOutputSlotTexture(), OUTPUT_BORDER)
        preset.addItem(STATUS_SLOT, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler())
    }

    override fun getInputSlots(menu: DirtyChestMenu, item: ItemStack): IntArray {
        return if (SlimefunItem.getByItem(item) is Strainer) inputSlots
        else intArrayOf()
    }

    override fun getInputSlots() = intArrayOf(INPUT_SLOT)

    override fun getOutputSlots() = OUTPUT_SLOTS

    override fun tick(b: Block, menu: BlockMenu) {
        if (isDisabledIn(b.world)) return
        if (!BlockUtils.isWaterLogged(b)) return
        if (InfinityExpansion2.sfTickCount() % tickRateSetting.value != 0) return

        val strainerItem = menu.getItemInSlot(INPUT_SLOT)
        val strainer = SlimefunItem.getByItem(strainerItem) as? Strainer
        if (strainer == null || strainer.isDisabledIn(b.world)) {
            if (menu.hasViewer()) {
                menu.replaceExistingItem(STATUS_SLOT, GuiItems.NO_STRAINER)
            }
            return
        }

        if (menu.hasViewer()) {
            menu.replaceExistingItem(STATUS_SLOT, GuiItems.COLLECTING)
        }
        if (!RandomUtil.testChance(strainer.chance, 100)) return

        if (RandomUtil.testChance(1, 10_000)) {
            menu.pushItem(POTATO_FISH, *OUTPUT_SLOTS)
        }

        val output = OUTPUTS.random()
        if (!InvUtils.fits(menu.toInventory(), output, *OUTPUT_SLOTS)) {
            menu.replaceExistingItem(STATUS_SLOT, GuiItems.NO_SPACE)
            return
        }

        menu.pushItem(output.clone(), *OUTPUT_SLOTS)

        // strainer reduce durability
        val bound = strainerItem.getEnchantmentLevel(Enchantment.getByName("unbreaking")!!) +
            3 * strainerItem.getEnchantmentLevel(Enchantment.getByName("mending")!!) +
            1
        if (Random.nextInt(bound) == 0) {
            val meta = strainerItem.itemMeta as Damageable
            val newDamage = meta.damage + 1

            if (newDamage >= Material.FISHING_ROD.maxDurability.toInt()) {
                menu.consumeItem(INPUT_SLOT)
            } else {
                meta.damage = newDamage
                strainerItem.itemMeta = meta
                menu.replaceExistingItem(INPUT_SLOT, strainerItem)
            }
        }
    }
}
