@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.utils.bukkitext

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import net.guizhanss.guizhanlib.kt.minecraft.extensions.toItem
import net.guizhanss.infinityexpansion2.core.IERegistry
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * Parse the string to an [ItemStack]. The result is only calculated once and cached.
 *
 * - If the string is a Slimefun item ID, returns the template item.
 * - If the string is a vanilla material name, returns an item stack with the material.
 * - Returns an air item stack if the string is invalid.
 */
fun String.toItemStack(): ItemStack = IERegistry.itemMapping.getOrPut(this) {
    SlimefunItem.getById(this)?.item // sf item
        ?: Material.getMaterial(this)?.toItem() // vanilla material
        ?: Material.AIR.toItem() // invalid
}
