package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import org.bukkit.inventory.ItemStack

// Slimefun experimental compatibility
fun SlimefunItemStack.toItem(): ItemStack = this.item()
fun ItemStack.toItem(): ItemStack = this.clone()

/**
 * Get a copy of the [SlimefunItemStack] with the given amount.
 */
fun SlimefunItemStack.withAmount(amount: Int): ItemStack = this.toItem().apply { this.amount = amount }
