package net.guizhanss.infinityexpansion2.utils

import org.bukkit.inventory.ItemStack

/**
 * Fill the 3x3 recipe with the same [ItemStack].
 */
fun ItemStack?.fillRecipe() = Array(9) { this }

/**
 * Returns a 3x3 recipe with `null` in all slots.
 */
fun emptyRecipe() = null.fillRecipe()

/**
 * Returns a 3x3 recipe where the given items are the first few items in the recipe,
 * and the rest are `null`.
 */
fun expandRecipe(vararg items: ItemStack?) = Array(9) { items.getOrNull(it) }

/**
 * Returns a 3x3 recipe where the given item is at the center,
 * and the rest are `null`.
 */
fun ItemStack?.centerRecipe() = arrayOf(
    null, null, null,
    null, this, null,
    null, null, null
)

/**
 * Returns a 3x3 recipe where the given item is at the center,
 * and is surrounded by the other item.
 */
infix fun ItemStack?.surroundedBy(other: ItemStack) = arrayOf(
    other, other, other,
    other, this, other,
    other, other, other
)

/**
 * Returns a 6x6 recipe where the given items are the first few items in the recipe,
 * and the rest are `null`.
 */
fun expandRecipe6(vararg items: ItemStack?) = Array(36) { items.getOrNull(it) }
