package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import net.guizhanss.guizhanlib.kt.minecraft.extensions.toItem
import net.guizhanss.guizhanlib.kt.slimefun.items.edit
import net.guizhanss.infinityexpansion2.core.items.attributes.CustomWikiItem
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.HopperMachine
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
) : HopperMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick), CustomWikiItem {

    override val wikiUrl = "machines/ingot-former"

    init {
        addRecipe(
            SlimefunItems.COPPER_DUST.edit { amount(inputAmount) },
            SlimefunItems.COPPER_INGOT.edit { amount(outputAmount) }
        )
        addRecipe(
            SlimefunItems.ZINC_DUST.edit { amount(inputAmount) },
            SlimefunItems.ZINC_INGOT.edit { amount(outputAmount) }
        )
        addRecipe(
            SlimefunItems.TIN_DUST.edit { amount(inputAmount) },
            SlimefunItems.TIN_INGOT.edit { amount(outputAmount) }
        )
        addRecipe(
            SlimefunItems.ALUMINUM_DUST.edit { amount(inputAmount) },
            SlimefunItems.ALUMINUM_INGOT.edit { amount(outputAmount) }
        )
        addRecipe(
            SlimefunItems.LEAD_DUST.edit { amount(inputAmount) },
            SlimefunItems.LEAD_INGOT.edit { amount(outputAmount) }
        )
        addRecipe(
            SlimefunItems.SILVER_DUST.edit { amount(inputAmount) },
            SlimefunItems.SILVER_INGOT.edit { amount(outputAmount) }
        )
        addRecipe(
            SlimefunItems.GOLD_DUST.edit { amount(inputAmount) },
            SlimefunItems.GOLD_4K.edit { amount(outputAmount) }
        )
        addRecipe(
            SlimefunItems.IRON_DUST.edit { amount(inputAmount) },
            Material.IRON_INGOT.toItem(outputAmount)
        )
        addRecipe(
            SlimefunItems.MAGNESIUM_DUST.edit { amount(inputAmount) },
            SlimefunItems.MAGNESIUM_INGOT.edit { amount(outputAmount) }
        )
    }
}
