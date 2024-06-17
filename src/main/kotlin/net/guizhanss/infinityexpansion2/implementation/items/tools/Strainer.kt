package net.guizhanss.infinityexpansion2.implementation.items.tools

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock
import net.guizhanss.infinityexpansion2.implementation.items.materials.SimpleMaterial
import org.bukkit.inventory.ItemStack

class Strainer(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    val chance: Int
) : SimpleMaterial(itemGroup, itemStack, recipeType, recipe)
