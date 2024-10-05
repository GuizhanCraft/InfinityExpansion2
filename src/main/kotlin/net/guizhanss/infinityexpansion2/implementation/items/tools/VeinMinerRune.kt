package net.guizhanss.infinityexpansion2.implementation.items.tools

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag
import net.guizhanss.infinityexpansion2.implementation.items.materials.SimpleMaterial
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class VeinMinerRune(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>
) : SimpleMaterial(itemGroup, itemStack, recipeType, recipe) {

    companion object {

        const val ACTIVATE_RANGE = 1.5

        // TODO: find a better way to manage blocks
        val ALLOWED_BLOCKS = arrayOf(
            "_ORE", "_LOG", "_WOOD", "GILDED", "SOUL", "GRAVEL",
            "MAGMA", "OBSIDIAN", "DIORITE", "ANDESITE", "GRANITE", "_LEAVES",
            "GLASS", "DIRT", "GRASS", "DEBRIS", "GLOWSTONE"
        )
    }
}
