package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import net.guizhanss.guizhanlib.kt.minecraft.extensions.toItem
import net.guizhanss.guizhanlib.kt.slimefun.items.edit
import net.guizhanss.infinityexpansion2.core.items.attributes.CustomWikiItem
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.RandomHopperMachine
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
) : RandomHopperMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick), CustomWikiItem {

    override val wikiUrl = "machines/dust-extractor"

    init {
        addRecipe(Material.COBBLESTONE.toItem(inputAmount), getDusts(outputAmount))
        addRecipe(Material.STONE.toItem(inputAmount), getDusts(outputAmount))
        addRecipe(Material.ANDESITE.toItem(inputAmount), getDusts(outputAmount))
        addRecipe(Material.DIORITE.toItem(inputAmount), getDusts(outputAmount))
        addRecipe(Material.GRANITE.toItem(inputAmount), getDusts(outputAmount))
    }

    companion object {

        private fun getDusts(amount: Int) = arrayOf(
            SlimefunItems.COPPER_DUST.edit { amount(amount) },
            SlimefunItems.ZINC_DUST.edit { amount(amount) },
            SlimefunItems.TIN_DUST.edit { amount(amount) },
            SlimefunItems.ALUMINUM_DUST.edit { amount(amount) },
            SlimefunItems.LEAD_DUST.edit { amount(amount) },
            SlimefunItems.SILVER_DUST.edit { amount(amount) },
            SlimefunItems.GOLD_DUST.edit { amount(amount) },
            SlimefunItems.IRON_DUST.edit { amount(amount) },
            SlimefunItems.MAGNESIUM_DUST.edit { amount(amount) }
        )
    }
}
