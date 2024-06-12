@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.utils

import org.bukkit.configuration.ConfigurationSection
import org.bukkit.enchantments.Enchantment
import java.util.Locale

inline fun <reified K, reified V> loadMap(
    section: ConfigurationSection?,
    keyParser: (String) -> K? = { key -> key as? K },
    valueParser: (Any?) -> V? = { value -> value as? V },
    valuePredicate: (V) -> Boolean = { true }
): Map<K, V> {
    if (section == null) {
        return mapOf()
    }

    val result = mutableMapOf<K, V>()
    section.getKeys(false).forEach { keyStr ->
        val key = keyParser(keyStr) ?: return@forEach
        val value = valueParser(section[keyStr]) ?: return@forEach
        if (!valuePredicate(value)) return@forEach
        result[key] = value
    }
    return result.toMap()
}

inline fun <reified K : Enum<K>, reified V> loadEnumKeyMap(
    section: ConfigurationSection?,
    valueParser: (Any?) -> V? = { value -> value as? V },
    valuePredicate: (V) -> Boolean = { true }
): Map<K, V> = loadMap(
    section,
    keyParser = { keyStr -> valueOfOrNull<K>(keyStr.uppercase(Locale.getDefault())) },
    valueParser,
    valuePredicate
)

fun loadIntMap(section: ConfigurationSection?, valuePredicate: (Int) -> Boolean = { true }) =
    loadMap<String, Int>(section, valuePredicate = valuePredicate)

fun loadDoubleMap(section: ConfigurationSection?, valuePredicate: (Double) -> Boolean = { true }) =
    loadMap<String, Double>(section, valuePredicate = valuePredicate)

fun loadBooleanMap(section: ConfigurationSection?) = loadMap<String, Boolean>(section)

fun loadStringMap(section: ConfigurationSection?, valuePredicate: (String) -> Boolean = { true }) =
    loadMap<String, String>(section, valueParser = { it.toString() }, valuePredicate = valuePredicate)

fun loadSectionMap(section: ConfigurationSection?) = loadMap<String, ConfigurationSection>(section)

fun loadEnchantmentKeyMap(section: ConfigurationSection?) =
    loadMap<Enchantment, Int>(section, { key -> Enchantment.getByName(key) }, valuePredicate = { it >= 1 })

