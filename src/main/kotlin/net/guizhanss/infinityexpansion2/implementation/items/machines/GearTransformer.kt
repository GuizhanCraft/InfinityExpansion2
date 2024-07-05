package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingActionMachine
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
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
        preset.drawBackground(GuiItems.GT_GEAR, GEAR_BORDER)
        preset.drawBackground(GuiItems.GT_MATERIAL, MATERIAL_BORDER)
    }

    override fun process(b: Block, menu: BlockMenu): Boolean {
        val gear = menu.getItemInSlot(inputSlots[0])
        val material = menu.getItemInSlot(inputSlots[1])

        // TODO

        return false
    }

    override fun getInformationalItems() = listOf(
        GuiItems.energyConsumptionPerUse(getEnergyConsumptionPerAction())
    )

    override fun getDefaultDisplayRecipes(): List<ItemStack?> {
        val result = mutableListOf<ItemStack?>()
        // TODO: add recipes
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

    }
}
