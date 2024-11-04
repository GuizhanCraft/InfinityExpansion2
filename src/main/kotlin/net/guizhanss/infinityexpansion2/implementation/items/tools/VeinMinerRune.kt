package net.guizhanss.infinityexpansion2.implementation.items.tools

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.implementation.items.materials.SimpleMaterial
import org.bukkit.inventory.ItemStack

class VeinMinerRune(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>
) : SimpleMaterial(itemGroup, itemStack, recipeType, recipe) {

    companion object {

        const val ACTIVATE_RANGE = 1.5
    }
}
