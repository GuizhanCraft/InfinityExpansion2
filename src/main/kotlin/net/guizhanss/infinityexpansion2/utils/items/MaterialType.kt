package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils
import org.bukkit.Material as BukkitMaterial
import org.bukkit.inventory.ItemStack as BukkitItemStack

sealed interface MaterialType {
    fun convert(): BukkitItemStack

    class Material(private val material: BukkitMaterial) : MaterialType {
        override fun convert() = BukkitItemStack(material)
    }

    class ItemStack(private val itemStack: BukkitItemStack) : MaterialType {
        override fun convert() = itemStack
    }

    class Head(private val texture: String) : MaterialType {
        override fun convert() = SlimefunUtils.getCustomHead(texture)
    }
}
