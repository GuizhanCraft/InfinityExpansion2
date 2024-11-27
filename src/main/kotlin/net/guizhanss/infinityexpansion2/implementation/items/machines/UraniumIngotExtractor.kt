package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import net.guizhanss.infinityexpansion2.utils.bukkitext.withAmount
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class UraniumIngotExtractor(
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
            Material.COBBLESTONE.toItem(inputAmount),
            SlimefunItems.URANIUM.withAmount(outputAmount)
        )
        addRecipe(
            Material.STONE.toItem(inputAmount),
            SlimefunItems.URANIUM.withAmount(outputAmount)
        )
        addRecipe(
            Material.ANDESITE.toItem(inputAmount),
            SlimefunItems.URANIUM.withAmount(outputAmount)
        )
        addRecipe(
            Material.DIORITE.toItem(inputAmount),
            SlimefunItems.URANIUM.withAmount(outputAmount)
        )
        addRecipe(
            Material.GRANITE.toItem(inputAmount),
            SlimefunItems.URANIUM.withAmount(outputAmount)
        )
    }
}
