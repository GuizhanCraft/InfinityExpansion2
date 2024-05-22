package net.guizhanss.infinityexpansion2.utils

import org.bukkit.inventory.ItemStack

/**
 * Recipe helpers for recipes (3x3).
 */
object RecipeUtils {
    fun full(item: ItemStack?) = Array(9) { item }

    fun empty() = full(null)

    fun expand(vararg items: ItemStack?) = Array(9) { items.getOrNull(it) }

    fun center(item: ItemStack) = arrayOf(
        null, null, null,
        null, item, null,
        null, null, null
    )

    fun surround(center: ItemStack?, surround: ItemStack?) = arrayOf(
        surround, surround, surround,
        surround, center, surround,
        surround, surround, surround
    )

    fun expand6(vararg items: ItemStack?) = Array(36) { items.getOrNull(it) }
}
