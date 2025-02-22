@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.items.storage

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.bukkitext.isAir
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class StorageCache(
    stack: ItemStack?,
    var amount: Int,
    val limit: Int,
    var voidExcess: Boolean,
) {

    private var _meta: ItemMeta? = null
    private var metaCached: Boolean = false

    var itemStack: ItemStack? = stack
        internal set(value) {
            field = value
            metaCached = false
            _meta = null
        }

    val itemMeta: ItemMeta?
        get() {
            if (_meta == null && !metaCached) {
                _meta = if (itemStack != null && itemStack!!.hasItemMeta()) itemStack!!.itemMeta else null
                metaCached = !metaCached
            }
            return _meta
        }

    fun isEmpty() = amount == 0 || itemStack.isAir()

    /**
     * Check if the current [ItemStack] matches the storage unit
     */
    fun matches(stack: ItemStack?): Boolean {
        if (stack == null) return itemStack == null

        return itemStack?.type == stack.type
            && (itemMeta != null) == stack.hasItemMeta()
            && (itemMeta == null || itemMeta == stack.itemMeta)
    }

    /**
     * Increase the amount of the storage unit by the given amount.
     * Returns leftover amount.
     */
    internal fun increaseAmount(amount: Int): Int {
        val newAmount: Long = this.amount.toLong() + amount.toLong()
        if (newAmount > limit) {
            this.amount = limit
            if (!voidExcess) return (newAmount - limit).toInt()
        } else {
            this.amount = newAmount.toInt()
        }
        return 0
    }

    /**
     * Add the lore to the target [ItemMeta] of the storage unit
     */
    fun addLore(meta: ItemMeta) {
        // no need to add lore if the storage unit is empty
        if (isEmpty()) return

        val lore = if (meta.hasLore()) meta.lore!! else mutableListOf()

        lore.add("")
        lore.add(InfinityExpansion2.localization.getLore("storage.item", ItemUtils.getItemName(itemStack)))
        lore.add(InfinityExpansion2.localization.getLore("storage.amount", "$amount / $limit"))
        lore.add(InfinityExpansion2.localization.getLore("storage.void-excess", voidExcess))

        meta.lore = lore
    }

    override fun toString() = "StorageCache(itemStack=$itemStack, amount=$amount, limit=$limit, voidExcess=$voidExcess)"
}
