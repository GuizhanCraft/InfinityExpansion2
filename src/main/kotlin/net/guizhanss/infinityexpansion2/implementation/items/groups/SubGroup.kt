package net.guizhanss.infinityexpansion2.implementation.items.groups

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack

class SubGroup(
    key: NamespacedKey,
    item: ItemStack
) : ItemGroup(key, item)
