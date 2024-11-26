package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.seggan.sf4k.item.builder.asMaterialType
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingActionMachine
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.items.isSlimefunItem
import net.guizhanss.infinityexpansion2.utils.tags.IETag
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

class GearTransformer(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerUse: Int,
) : AbstractTickingActionMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.GEAR_TRANSFORMER, energyPerUse),
    InformationalRecipeDisplayItem {

    override fun setup(preset: BlockMenuPreset) {
        super.setup(preset)
        preset.drawBackground(GEAR_BORDER_ITEM, GEAR_BORDER)
        preset.drawBackground(MATERIAL_BORDER_ITEM, MATERIAL_BORDER)
    }

    override fun getInputSlots(menu: DirtyChestMenu, item: ItemStack): IntArray {
        return if (IETag.UPGRADABLE_TOOL.isTagged(item.type) || IETag.UPGRADABLE_ARMOR.isTagged(item.type)) {
            intArrayOf(inputSlots[0])
        } else {
            intArrayOf(inputSlots[1])
        }
    }

    override fun process(b: Block, menu: BlockMenu): Boolean {
        val gear = menu.getItemInSlot(inputSlots[0])
        val material = menu.getItemInSlot(inputSlots[1])

        if (gear == null || material == null) {
            menu.setStatus { GuiItems.INVALID_INPUT }
            return false
        }

        if (!InfinityExpansion2.configService.allowSfItemTransform && gear.isSlimefunItem()) {
            menu.setStatus { NO_SLIMEFUN_ITEM }
            return false
        }

        menu.setStatus { GuiItems.PRODUCING }

        // check if item is a tool/weapon
        if (IETag.UPGRADABLE_TOOL.isTagged(gear.type)) {
            // tool
            return process(menu, TOOLS, gear, material)
        } else if (IETag.UPGRADABLE_ARMOR.isTagged(gear.type)) {
            // armor
            return process(menu, ARMORS, gear, material)
        }

        return false
    }

    private fun process(menu: BlockMenu, map: Map<String, ItemStack>, gear: ItemStack, material: ItemStack): Boolean {
        // make sure the current gear material is in the map
        val current = map.entries.firstOrNull { gear.type.name.startsWith(it.key) } ?: return false

        // find the new material
        val new = map.entries.firstOrNull { ItemUtils.canStack(material, it.value) } ?: return false

        // the new material shouldn't be same as current
        if (current.key == new.key) {
            return false
        }

        // change the material
        val newGear = Material.getMaterial(gear.type.name.replace(current.key, new.key))!!.toItem(gear.amount)
        newGear.itemMeta = gear.itemMeta

        // update the menu
        if (!menu.fits(newGear, *outputSlots)) {
            menu.setStatus { GuiItems.NO_ROOM }
            return false
        }
        menu.consumeItem(inputSlots[0])
        menu.consumeItem(inputSlots[1], new.value.amount)
        menu.pushItem(newGear, *outputSlots)

        return true
    }

    override fun getInformationalItems() = listOf(
        GuiItems.energyConsumptionPerUse(getEnergyConsumptionPerAction()),
        GuiItems.sfItem(InfinityExpansion2.configService.allowSfItemTransform)
    )

    override fun getDefaultDisplayRecipes(): List<ItemStack?> {
        val result = mutableListOf<ItemStack?>()

        TOOLS.values.forEach { result.add(it) }

        if (TOOLS.size % 2 != 0) result.add(null)
        repeat(2) { result.add(null) }

        ARMORS.values.forEach { result.add(it) }

        return result
    }

    companion object {

        private val GEAR_BORDER = intArrayOf(
            0, 1, 2,
            9, 11,
            18, 19, 20,
        )
        private val MATERIAL_BORDER = intArrayOf(
            6, 7, 8,
            15, 17,
            24, 25, 26
        )

        private val GEAR_BORDER_ITEM = InfinityExpansion2.localization.getGuiItem(
            Material.BLUE_STAINED_GLASS_PANE.asMaterialType(),
            "gt_gear"
        )
        private val MATERIAL_BORDER_ITEM = InfinityExpansion2.localization.getGuiItem(
            Material.BLUE_STAINED_GLASS_PANE.asMaterialType(),
            "gt_material"
        )
        private val NO_SLIMEFUN_ITEM = InfinityExpansion2.localization.getGuiItem(
            Material.BARRIER.asMaterialType(),
            "gt_no_slimefun"
        )

        private val TOOLS = mapOf(
            "WOODEN_" to Material.OAK_PLANKS.toItem(4),
            "STONE_" to Material.COBBLESTONE.toItem(4),
            "IRON_" to Material.IRON_INGOT.toItem(4),
            "GOLDEN_" to Material.GOLD_INGOT.toItem(4),
            "DIAMOND_" to Material.DIAMOND.toItem(4),
            "NETHERITE_" to Material.NETHERITE_INGOT.toItem(2),
        )
        private val ARMORS = mapOf(
            "LEATHER_" to Material.LEATHER.toItem(4),
            "CHAINMAIL_" to Material.CHAIN.toItem(4),
            "IRON_" to Material.IRON_INGOT.toItem(4),
            "GOLDEN_" to Material.GOLD_INGOT.toItem(4),
            "DIAMOND_" to Material.DIAMOND.toItem(4),
            "NETHERITE_" to Material.NETHERITE_INGOT.toItem(2),
        )
    }
}
