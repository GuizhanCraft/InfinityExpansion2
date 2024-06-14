package net.guizhanss.infinityexpansion2.core.items

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import org.bukkit.inventory.ItemStack

/**
 * An [ExtraItem] stores the recipe of a non-standard Slimefun item.
 */
data class ExtraItem(
    val recipeType: RecipeType,
    val input: Array<out ItemStack?>,
    val output: ItemStack,
) {
    fun register() {
        recipeType.register(input, output)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExtraItem

        if (recipeType != other.recipeType) return false
        if (!input.contentEquals(other.input)) return false
        if (output != other.output) return false

        return true
    }

    override fun hashCode(): Int {
        var result = recipeType.hashCode()
        result = 31 * result + input.contentHashCode()
        result = 31 * result + output.hashCode()
        return result
    }
}
