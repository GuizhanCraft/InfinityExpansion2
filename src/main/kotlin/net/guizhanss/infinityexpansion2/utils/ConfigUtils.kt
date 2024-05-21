package net.guizhanss.infinityexpansion2.utils

import org.bukkit.configuration.ConfigurationSection

object ConfigUtils {
    private inline fun <reified V> loadMap(
        section: ConfigurationSection?,
        parser: (Any?) -> V? = { value -> if (value is V) value else null }
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
}
