package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class IngotFormer(
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
            SlimefunItemStack(SlimefunItems.COPPER_DUST, inputAmount),
            arrayOf(SlimefunItemStack(SlimefunItems.COPPER_INGOT, outputAmount))
        )
        addRecipe(
            SlimefunItemStack(SlimefunItems.ZINC_DUST, inputAmount),
            arrayOf(SlimefunItemStack(SlimefunItems.ZINC_INGOT, outputAmount))
        )
        addRecipe(
            SlimefunItemStack(SlimefunItems.TIN_DUST, inputAmount),
            arrayOf(SlimefunItemStack(SlimefunItems.TIN_INGOT, outputAmount))
        )
        addRecipe(
            SlimefunItemStack(SlimefunItems.ALUMINUM_DUST, inputAmount),
            arrayOf(SlimefunItemStack(SlimefunItems.ALUMINUM_INGOT, outputAmount))
        )
        addRecipe(
            SlimefunItemStack(SlimefunItems.LEAD_DUST, inputAmount),
            arrayOf(SlimefunItemStack(SlimefunItems.LEAD_INGOT, outputAmount))
        )
        addRecipe(
            SlimefunItemStack(SlimefunItems.SILVER_DUST, inputAmount),
            arrayOf(SlimefunItemStack(SlimefunItems.SILVER_INGOT, outputAmount))
        )
        addRecipe(
            SlimefunItemStack(SlimefunItems.GOLD_DUST, inputAmount),
            arrayOf(SlimefunItemStack(SlimefunItems.GOLD_4K, outputAmount))
        )
        addRecipe(
            SlimefunItemStack(SlimefunItems.IRON_DUST, inputAmount),
            arrayOf(ItemStack(Material.IRON_INGOT, outputAmount))
        )
        addRecipe(
            SlimefunItemStack(SlimefunItems.MAGNESIUM_DUST, inputAmount),
            arrayOf(SlimefunItemStack(SlimefunItems.MAGNESIUM_INGOT, outputAmount))
        )
    }
}
