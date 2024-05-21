package net.guizhanss.infinityexpansion2.implementation.items.groups

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

open class SubGroup(
    key: NamespacedKey,
    item: ItemStack
) : ItemGroup(key, item) {
    override fun isVisible(p: Player) = false
}
