package net.guizhanss.infinityexpansion2.implementation.groups

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * A [SubGroup] is an [ItemGroup] that is used to display Infinity Expansion's items.
 */
open class SubGroup(
    key: NamespacedKey,
    item: ItemStack
) : ItemGroup(key, item) {
    override fun isVisible(p: Player) = false

    fun getName() = item.itemMeta!!.displayName
}
