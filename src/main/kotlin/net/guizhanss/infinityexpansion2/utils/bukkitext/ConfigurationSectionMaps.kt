@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.utils.bukkitext

import net.guizhanss.infinityexpansion2.utils.valueOfOrNull
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.enchantments.Enchantment
import java.util.Locale

/**
 * Loads a map from the [ConfigurationSection].
 */
inline fun <reified K, reified V> ConfigurationSection.loadMap(
    keyParser: (String) -> K? = { key -> key as? K },
    valueParser: (Any?) -> V? = { value -> value as? V },
    valuePredicate: (V) -> Boolean = { true }
): Map<K, V> {
    val result = mutableMapOf<K, V>()
    getKeys(false).forEach { keyStr ->
        val key = keyParser(keyStr) ?: return@forEach
        val value = valueParser(this[keyStr]) ?: return@forEach
        if (!valuePredicate(value)) return@forEach
        result[key] = value
    }
    return result.toMap()
}

/**
 * Loads a map from the [ConfigurationSection] with enum keys.

 */
inline fun <reified K : Enum<K>, reified V> ConfigurationSection.loadEnumKeyMap(
    valueParser: (Any?) -> V? = { value -> value as? V },
    valuePredicate: (V) -> Boolean = { true }
): Map<K, V> = loadMap(
    keyParser = { keyStr -> valueOfOrNull<K>(keyStr.uppercase(Locale.getDefault())) },
    valueParser,
    valuePredicate
)

fun ConfigurationSection.loadIntMap(section: ConfigurationSection?, valuePredicate: (Int) -> Boolean = { true }) =
    loadMap<String, Int>(valuePredicate = valuePredicate)

fun ConfigurationSection.loadDoubleMap(section: ConfigurationSection?, valuePredicate: (Double) -> Boolean = { true }) =
    loadMap<String, Double>(valuePredicate = valuePredicate)

fun ConfigurationSection.loadBooleanMap(section: ConfigurationSection?) = loadMap<String, Boolean>()

fun ConfigurationSection.loadStringMap(section: ConfigurationSection?, valuePredicate: (String) -> Boolean = { true }) =
    loadMap<String, String>(valueParser = { it.toString() }, valuePredicate = valuePredicate)

fun ConfigurationSection.loadSectionMap(section: ConfigurationSection?) = loadMap<String, ConfigurationSection>()

fun ConfigurationSection.loadEnchantmentKeyMap(section: ConfigurationSection?) =
    loadMap<Enchantment, Int>({ key -> Enchantment.getByName(key) }, valuePredicate = { it >= 1 })

