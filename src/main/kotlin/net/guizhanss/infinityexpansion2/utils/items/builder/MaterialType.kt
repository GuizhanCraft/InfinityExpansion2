package net.guizhanss.infinityexpansion2.utils.items.builder

import io.github.thebusybiscuit.slimefun4.utils.HeadTexture
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils
import org.bukkit.Material as BukkitMaterial
import org.bukkit.inventory.ItemStack as BukkitItemStack

/**
 * A sealed interface for converting different types of item representations to [BukkitItemStack].
 *
 * Modified from [sf4k by Seggan](https://github.com/Seggan/sf4k/blob/master/src/main/kotlin/io/github/seggan/sf4k/item/builder/MaterialType.kt).
 */
sealed interface MaterialType {

    fun convert(): BukkitItemStack

    /**
     * Represents a [BukkitMaterial] as a [MaterialType].
     */
    class Material(private val material: BukkitMaterial) : MaterialType {

        override fun convert() = BukkitItemStack(material)
    }

    /**
     * Represents a [BukkitItemStack] as a [MaterialType].
     */
    class ItemStack(private val itemStack: BukkitItemStack) : MaterialType {

        override fun convert() = itemStack
    }

    /**
     * Represents a head texture [String] as a [MaterialType].
     */
    class Head(private val texture: String) : MaterialType {

        override fun convert() = SlimefunUtils.getCustomHead(texture)
    }
}

fun BukkitMaterial.asMaterialType() = MaterialType.Material(this)
fun BukkitItemStack.asMaterialType() = MaterialType.ItemStack(this)
fun String.asMaterialType() = MaterialType.Head(this)
fun HeadTexture.asMaterialType() = MaterialType.Head(this.texture)
