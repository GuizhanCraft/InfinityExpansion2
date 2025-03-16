package net.guizhanss.infinityexpansion2.utils.compatibility

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import org.bukkit.inventory.ItemStack
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

// Slimefun experimental compatibility
fun SlimefunItemStack.toItem(): ItemStack {
    try {
        val itemStack = this as ItemStack
        return itemStack.clone()
    } catch (_: ClassCastException) {
        val lookup = MethodHandles.lookup()
        val methodType = MethodType.methodType(ItemStack::class.java)

        try {
            val methodHandle = lookup.findVirtual(this::class.java, "item", methodType)

            val result = methodHandle(this) as ItemStack
            return result
        } catch (e: Exception) {
            throw RuntimeException("Cannot get ItemStack from SlimefunItemStack.", e)
        }
    }
}

/**
 * Get a copy of the [SlimefunItemStack] with the given amount.
 */
fun SlimefunItemStack.withAmount(amount: Int): ItemStack = this.toItem().apply { this.amount = amount }
