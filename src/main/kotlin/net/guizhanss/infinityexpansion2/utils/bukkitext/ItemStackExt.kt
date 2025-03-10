@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.utils.bukkitext

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import net.guizhanss.guizhanlib.minecraft.utils.ChatUtil
import net.guizhanss.infinityexpansion2.core.IERegistry
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

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

/**
 * Create an [ItemStack] from the [Material] with the given amount (default 1).
 */
fun Material.toItem(amount: Int = 1): ItemStack = ItemStack(this, amount)

/**
 * Get a copy of the [ItemStack] with the given amount.
 */
fun ItemStack.withAmount(amount: Int): ItemStack = clone().apply { this.amount = amount }

fun ItemStack.withName(name: String): ItemStack = clone().apply {
    val meta = itemMeta
    meta.setDisplayName(ChatUtil.color(name))
    itemMeta = meta
}

fun ItemStack.withLore(lore: List<String>): ItemStack = clone().apply {
    val meta = itemMeta
    meta.lore = lore.map { ChatUtil.color(it) }
    itemMeta = meta
}

@JvmName("withLoreVararg")
fun ItemStack.withLore(vararg lore: String): ItemStack = withLore(lore.toList())

/**
 * A shortcut to check if an [ItemStack] is null or air.
 */
@OptIn(ExperimentalContracts::class)
fun ItemStack?.isAir(): Boolean {
    contract {
        returns(false) implies (this@isAir != null)
    }
    return this?.type?.isAir ?: true
}

/**
 * Drop the [ItemStack] with the given amount at the [Location].
 */
fun ItemStack.dropItem(loc: Location, amount: Int = 1) {
    val fullStacks = amount / maxStackSize
    val remaining = amount % maxStackSize
    repeat(fullStacks) {
        val item = clone().apply { this.amount = amount }
        loc.world.dropItem(loc, item)
    }
    if (remaining > 0) {
        val item = clone().apply { this.amount = remaining }
        loc.world.dropItem(loc, item)
    }
}
