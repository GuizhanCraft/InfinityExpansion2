package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import org.bukkit.inventory.ItemStack

fun String.isSlimefunItem() = SlimefunItem.getById(this) != null

@JvmName("isSlimefunItemGeneric")
inline fun <reified T : SlimefunItem> String.isSlimefunItem() = SlimefunItem.getById(this) is T

fun ItemStack?.isSlimefunItem() = SlimefunItem.getByItem(this) != null

@JvmName("isSlimefunItemGeneric")
inline fun <reified T : SlimefunItem> ItemStack?.isSlimefunItem() = SlimefunItem.getByItem(this) is T
