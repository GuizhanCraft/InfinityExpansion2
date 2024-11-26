package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import net.guizhanss.infinityexpansion2.utils.bukkitext.withAmount
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
        addRecipe(Material.COBBLESTONE.toItem(inputAmount), getDusts(outputAmount))
        addRecipe(Material.STONE.toItem(inputAmount), getDusts(outputAmount))
        addRecipe(Material.ANDESITE.toItem(inputAmount), getDusts(outputAmount))
        addRecipe(Material.DIORITE.toItem(inputAmount), getDusts(outputAmount))
        addRecipe(Material.GRANITE.toItem(inputAmount), getDusts(outputAmount))
    }

    companion object {

        private fun getDusts(amount: Int) = arrayOf(
            SlimefunItems.COPPER_DUST.withAmount(amount),
            SlimefunItems.ZINC_DUST.withAmount(amount),
            SlimefunItems.TIN_DUST.withAmount(amount),
            SlimefunItems.ALUMINUM_DUST.withAmount(amount),
            SlimefunItems.LEAD_DUST.withAmount(amount),
            SlimefunItems.SILVER_DUST.withAmount(amount),
            SlimefunItems.GOLD_DUST.withAmount(amount),
            SlimefunItems.IRON_DUST.withAmount(amount),
            SlimefunItems.MAGNESIUM_DUST.withAmount(amount)
        )
    }
}
