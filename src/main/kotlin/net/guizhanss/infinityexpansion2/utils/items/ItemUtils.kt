package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import net.guizhanss.guizhanlib.kt.minecraft.extensions.toItem
import net.guizhanss.infinityexpansion2.core.IERegistry
import net.guizhanss.infinityexpansion2.utils.constant.Patterns
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * Parse the string to an [ItemStack]. The result is only calculated once and cached.
 *
 * - If the string is in minecraft NamespacedKey format (e.g., "minecraft:item"), returns the matching material within ItemStack.
 * - If the string is a valid Slimefun item ID, returns the template item.
 * - If the string is a vanilla material name, returns an ItemStack with the material.
 * - Otherwise, returns air.
 */
fun String.toItemStack(): ItemStack = IERegistry.itemMapping.getOrPut(this) {
    if (Patterns.MINECRAFT_NAMESPACEDKEY.matcher(this).matches()) {
        return Material.getMaterial(this)?.toItem() ?: Material.AIR.toItem()
    }

    return SlimefunItem.getById(this)?.item ?: Material.getMaterial(this)?.toItem() ?: Material.AIR.toItem()
}

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
