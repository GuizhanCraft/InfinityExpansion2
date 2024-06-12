package net.guizhanss.infinityexpansion2.implementation.items.tools

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock
import org.bukkit.inventory.ItemStack

class Oscillator(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
) : UnplaceableBlock(itemGroup, itemStack, recipeType, recipe) {
    fun getType() = item.type

    companion object {
        fun getFromItem(item: ItemStack) = SlimefunItem.getByItem(item) as? Oscillator
    }
}
