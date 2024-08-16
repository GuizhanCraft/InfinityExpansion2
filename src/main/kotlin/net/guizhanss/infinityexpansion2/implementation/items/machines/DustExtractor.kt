package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class DustExtractor(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
    inputAmount: Int,
    outputAmount: Int,
) : RandomHopperMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick) {

    init {
        addRecipe(ItemStack(Material.COBBLESTONE, inputAmount), getDusts(outputAmount))
        addRecipe(ItemStack(Material.STONE, inputAmount), getDusts(outputAmount))
        addRecipe(ItemStack(Material.ANDESITE, inputAmount), getDusts(outputAmount))
        addRecipe(ItemStack(Material.DIORITE, inputAmount), getDusts(outputAmount))
        addRecipe(ItemStack(Material.GRANITE, inputAmount), getDusts(outputAmount))
    }

    companion object {

        private fun getDusts(amount: Int) = arrayOf<ItemStack>(
            SlimefunItemStack(SlimefunItems.COPPER_DUST, amount),
            SlimefunItemStack(SlimefunItems.ZINC_DUST, amount),
            SlimefunItemStack(SlimefunItems.TIN_DUST, amount),
            SlimefunItemStack(SlimefunItems.ALUMINUM_DUST, amount),
            SlimefunItemStack(SlimefunItems.LEAD_DUST, amount),
            SlimefunItemStack(SlimefunItems.SILVER_DUST, amount),
            SlimefunItemStack(SlimefunItems.GOLD_DUST, amount),
            SlimefunItemStack(SlimefunItems.IRON_DUST, amount),
            SlimefunItemStack(SlimefunItems.MAGNESIUM_DUST, amount)
        )
    }
}
