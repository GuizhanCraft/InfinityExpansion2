package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class UraniumExtractor(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
    inputAmount: Int,
    outputAmount: Int,
) : HopperMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick) {
    init {
        addRecipe(
            ItemStack(Material.COBBLESTONE, inputAmount),
            arrayOf(SlimefunItemStack(SlimefunItems.SMALL_URANIUM, outputAmount))
        )
        addRecipe(
            ItemStack(Material.STONE, inputAmount),
            arrayOf(SlimefunItemStack(SlimefunItems.SMALL_URANIUM, outputAmount))
        )
        addRecipe(
            ItemStack(Material.ANDESITE, inputAmount),
            arrayOf(SlimefunItemStack(SlimefunItems.SMALL_URANIUM, outputAmount))
        )
        addRecipe(
            ItemStack(Material.DIORITE, inputAmount),
            arrayOf(SlimefunItemStack(SlimefunItems.SMALL_URANIUM, outputAmount))
        )
        addRecipe(
            ItemStack(Material.GRANITE, inputAmount),
            arrayOf(SlimefunItemStack(SlimefunItems.SMALL_URANIUM, outputAmount))
        )
    }
}
