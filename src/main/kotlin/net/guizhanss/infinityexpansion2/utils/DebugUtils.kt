package net.guizhanss.infinityexpansion2.utils

import net.guizhanss.infinityexpansion2.utils.items.getSlimefunItem
import net.guizhanss.infinityexpansion2.utils.items.isSlimefunItem
import org.bukkit.inventory.ItemStack

fun Array<out ItemStack?>.toDebugMessage(): String {
    return this.joinToString(", ") { itemStack ->
        if (itemStack == null) {
            return@joinToString "null"
        }

        if (itemStack.isSlimefunItem()) {
            val sfItem = itemStack.getSlimefunItem()
            return@joinToString sfItem.item.toString()
        }

        return@joinToString itemStack.toString()
    }
}
