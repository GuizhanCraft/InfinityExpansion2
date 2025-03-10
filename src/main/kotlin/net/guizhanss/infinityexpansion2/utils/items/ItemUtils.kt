package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import org.bukkit.inventory.ItemStack

/**
 * Simply check if two [ItemStack]s are similar. (not recommended in most cases)
 *
 * Returns `true` if two items are both null, or meet all the following conditions:
 * - Have the same type
 * - Have the same Slimefun ID
 */
fun isSimilar(item1: ItemStack?, item2: ItemStack?): Boolean {
    // null check
    if (item1 == null && item2 == null) return true
    if (item1 == null || item2 == null) return false

    // type check
    if (item1.type != item2.type) return false

    // slimefun id check
    val sfItem1 = SlimefunItem.getByItem(item1)
    val sfItem2 = SlimefunItem.getByItem(item2)
    if (sfItem1 == null && sfItem2 == null) return true
    if (sfItem1 != null && sfItem2 != null) {
        return sfItem1.id == sfItem2.id
    }

    return false
}
