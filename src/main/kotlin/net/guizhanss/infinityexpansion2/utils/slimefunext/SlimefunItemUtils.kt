package net.guizhanss.infinityexpansion2.utils.slimefunext

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import org.bukkit.inventory.ItemStack

fun String.isSlimefunItem() = SlimefunItem.getById(this) != null

@JvmName("isSlimefunItemGeneric")
inline fun <reified T : SlimefunItem> String.isSlimefunItem() = SlimefunItem.getById(this) is T

fun ItemStack?.isSlimefunItem() = SlimefunItem.getByItem(this) != null

@JvmName("isSlimefunItemGeneric")
inline fun <reified T : SlimefunItem> ItemStack?.isSlimefunItem() = SlimefunItem.getByItem(this) is T

/**
 * This should be called only after [isSlimefunItem] check, or this will throw [IllegalStateException].
 * Cast to the base [SlimefunItem]. Use the generic one to set the class for casting to.
 */
fun ItemStack?.getSlimefunItem() = SlimefunItem.getByItem(this) ?: error("Not a SlimefunItem")

/**
 * This should be called only after [isSlimefunItem] check, or this will throw [IllegalStateException].
 * Cast to the specified [SlimefunItem] subclass.
 */
@JvmName("getSlimefunItemGeneric")
inline fun <reified T : SlimefunItem> ItemStack?.getSlimefunItem() =
    SlimefunItem.getByItem(this) as? T ?: error("Not a SlimefunItem")
