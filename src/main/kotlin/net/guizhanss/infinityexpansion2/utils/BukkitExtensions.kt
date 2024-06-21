@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.utils

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.IERegistry
import net.guizhanss.infinityexpansion2.core.config.ConfigurationSerializable
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.block.data.Waterlogged
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import kotlin.reflect.full.createType

/**
 * Check if a block is waterlogged.
 */
fun Block.isWaterLogged(tickRate: Int = 64) = if (InfinityExpansion2.sfTickCount() % tickRate == 0) {
    val blockData = blockData

    if (blockData is Waterlogged) {
        location.setBoolean("water_logged", blockData.isWaterlogged)
        blockData.isWaterlogged
    } else {
        false
    }
} else {
    location.getBoolean("water_logged")
}

/**
 * Check if the block has light from the sky.
 */
fun Block.hasLightFromSky() = location.add(0.0, 1.0, 0.0).block.lightFromSky.toInt() == 15

/**
 * Check if the world is day.
 */
fun World.isDay() = time in 0 until 13000

/**
 * Check if the world is night.
 */
fun World.isNight() = time in 13000..24000

/**
 * Get a [ConfigurationSerializable] by deserializing the [ConfigurationSection].
 */
inline fun <reified T : ConfigurationSerializable> ConfigurationSection.getSerializable(
    key: String,
    defaultVal: T? = null,
): T? {
    val section = this.getConfigurationSection(key) ?: return defaultVal
    T::class.constructors.forEach { constructor ->
        if (constructor.parameters.size == 1 && constructor.parameters[0].type == ConfigurationSection::class.createType()) {
            return constructor.call(section)
        }
    }
    return defaultVal
}

/**
 * Parse the string to an [ItemStack]. The result is only calculated once and cached.
 *
 * - If the string is a Slimefun item ID, returns the template item.
 * - If the string is a vanilla material name, returns an item stack with the material.
 * - Returns an air item stack if the string is invalid.
 */
fun String.toItemStack(): ItemStack = IERegistry.itemMapping.getOrPut(this) {
    SlimefunItem.getById(this)?.item // sf item
        ?: Material.getMaterial(this)?.let { ItemStack(it) } // vanilla material
        ?: ItemStack(Material.AIR) // invalid
}

/**
 * A shortcut to check if an [ItemStack] is air.
 */
fun ItemStack.isAir() = type.isAir

/**
 * Retrieve the [PotionEffectType] by the name. For 1.20.5+ compatibility.
 */
fun getPotionEffectType(name: String) = PotionEffectType.getByKey(name.toMinecraftKey())

/**
 * Retrieve the [Enchantment] by the name. For 1.20.5+ compatibility.
 */
fun getEnchantment(name: String) = Enchantment.getByKey(name.toMinecraftKey())

/**
 * Build a [PotionEffect] by the name, duration, and amplifier.
 */
fun buildPotionEffect(name: String, duration: Int, amplifier: Int) = PotionEffect(
    getPotionEffectType(name) ?: throw IllegalArgumentException("Invalid potion effect type: $name"),
    duration,
    amplifier,
)

/**
 * Build a hidden [PotionEffect] by the name, duration, and amplifier.
 */
fun buildHiddenPotionEffect(name: String, duration: Int, amplifier: Int) = PotionEffect(
    getPotionEffectType(name) ?: throw IllegalArgumentException("Invalid potion effect type: $name"),
    duration,
    amplifier,
    false,
    false,
    false,
)
