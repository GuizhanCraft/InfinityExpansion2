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

        const val RANGE = 1.5

        private val blocks = mutableSetOf<Material>()

        init {
            blocks.addAll(SlimefunTag.ORES.values)
            blocks.addAll(SlimefunTag.LOGS.values)
        }

        val ALLOWED_BLOCKS = blocks.toSet()
    }
}
