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

fun Block.hasLightFromSky() = location.add(0.0, 1.0, 0.0).block.lightFromSky.toInt() == 15

fun World.isDay() = time in 0 until 13000

fun World.isNight() = time in 13000..24000

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

fun String.toItemStack(): ItemStack = IERegistry.itemMapping.getOrPut(this) {
    SlimefunItem.getById(this)?.item // sf item
        ?: Material.getMaterial(this)?.let { ItemStack(it) } // material
        ?: ItemStack(Material.COBBLESTONE) // fallback
}

fun getPotionEffectType(name: String) = PotionEffectType.getByKey(NamespacedKey.minecraft(name))
fun getEnchantment(name: String) = Enchantment.getByKey(NamespacedKey.minecraft(name))

fun buildPotionEffect(name: String, duration: Int, amplifier: Int) = PotionEffect(
    getPotionEffectType(name) ?: throw IllegalArgumentException("Invalid potion effect type: $name"),
    duration,
    amplifier,
    false,
    false,
    false
)
