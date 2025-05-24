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

class UraniumIngotExtractor(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
    inputAmount: Int,
    outputAmount: Int,
) : HopperMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick), CustomWikiItem {

    override val wikiUrl = "machines/uranium-ingot-extractor"

    init {
        addRecipe(
            Material.COBBLESTONE.toItem(inputAmount),
            SlimefunItems.URANIUM.edit { amount(outputAmount) }
        )
        addRecipe(
            Material.STONE.toItem(inputAmount),
            SlimefunItems.URANIUM.edit { amount(outputAmount) }
        )
        addRecipe(
            Material.ANDESITE.toItem(inputAmount),
            SlimefunItems.URANIUM.edit { amount(outputAmount) }
        )
        addRecipe(
            Material.DIORITE.toItem(inputAmount),
            SlimefunItems.URANIUM.edit { amount(outputAmount) }
        )
        addRecipe(
            Material.GRANITE.toItem(inputAmount),
            SlimefunItems.URANIUM.edit { amount(outputAmount) }
        )
    }
}
