package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import net.guizhanss.infinityexpansion2.utils.bukkitext.withAmount
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
            SlimefunItems.COPPER_DUST.withAmount(inputAmount),
            SlimefunItems.COPPER_INGOT.withAmount(outputAmount)
        )
        addRecipe(
            SlimefunItems.ZINC_DUST.withAmount(inputAmount),
            SlimefunItems.ZINC_INGOT.withAmount(outputAmount)
        )
        addRecipe(
            SlimefunItems.TIN_DUST.withAmount(inputAmount),
            SlimefunItems.TIN_INGOT.withAmount(outputAmount)
        )
        addRecipe(
            SlimefunItems.ALUMINUM_DUST.withAmount(inputAmount),
            SlimefunItems.ALUMINUM_INGOT.withAmount(outputAmount)
        )
        addRecipe(
            SlimefunItems.LEAD_DUST.withAmount(inputAmount),
            SlimefunItems.LEAD_INGOT.withAmount(outputAmount)
        )
        addRecipe(
            SlimefunItems.SILVER_DUST.withAmount(inputAmount),
            SlimefunItems.SILVER_INGOT.withAmount(outputAmount)
        )
        addRecipe(
            SlimefunItems.GOLD_DUST.withAmount(inputAmount),
            SlimefunItems.GOLD_4K.withAmount(outputAmount)
        )
        addRecipe(
            SlimefunItems.IRON_DUST.withAmount(inputAmount),
            Material.IRON_INGOT.toItem(outputAmount)
        )
        addRecipe(
            SlimefunItems.MAGNESIUM_DUST.withAmount(inputAmount),
            SlimefunItems.MAGNESIUM_INGOT.withAmount(outputAmount)
        )
    }
}
