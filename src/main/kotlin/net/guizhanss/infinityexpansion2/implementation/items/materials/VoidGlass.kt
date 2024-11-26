package net.guizhanss.infinityexpansion2.implementation.items.materials

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.HardenedGlass
import net.guizhanss.infinityexpansion2.utils.bukkitext.withAmount
import org.bukkit.inventory.ItemStack

class VoidGlass(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    amount: Int,
) : HardenedGlass(itemGroup, itemStack, recipeType, recipe, itemStack.withAmount(amount))
