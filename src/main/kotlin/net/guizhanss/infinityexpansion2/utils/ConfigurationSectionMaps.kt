@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.utils

import org.bukkit.configuration.ConfigurationSection
import org.bukkit.enchantments.Enchantment

private inline fun <reified V> loadMap(
    section: ConfigurationSection?,
    parser: (Any?) -> V? = { value -> value as? V }
): Map<String, V> {
    if (section == null) {
        return mapOf()
    }

    val result = mutableMapOf<String, V>()
    for (key in section.getKeys(false)) {
        val value = parser(section[key])
        if (value != null) {
            result[key] = value
        }
    }

    return result.toMap()
}

fun loadIntMap(section: ConfigurationSection?) = loadMap<Int>(section)
fun loadDoubleMap(section: ConfigurationSection?) = loadMap<Double>(section)
fun loadBooleanMap(section: ConfigurationSection?) = loadMap<Boolean>(section)
fun loadStringMap(section: ConfigurationSection?) = loadMap<String>(section) { it.toString() }
fun loadSectionMap(section: ConfigurationSection?) = loadMap<ConfigurationSection>(section)

fun loadEnchantmentKeyMap(section: ConfigurationSection?): Map<Enchantment, Int> {
    val result = mutableMapOf<Enchantment, Int>()
    loadIntMap(section).entries.forEach { (key, value) ->
        val enchantment = Enchantment.getByName(key) ?: return@forEach
        result[enchantment] = if (value > enchantment.maxLevel) value else enchantment.maxLevel
    }
    return result
}
