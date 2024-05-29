package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ExtremeFreezer(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
) : HopperMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick) {
    init {
        addRecipe(ItemStack(Material.ICE, 2), arrayOf(SlimefunItems.REACTOR_COOLANT_CELL))
        addRecipe(ItemStack(Material.MAGMA_BLOCK, 2), arrayOf(SlimefunItems.NETHER_ICE_COOLANT_CELL))
    }
}
