package net.guizhanss.infinityexpansion2.utils

import org.bukkit.configuration.ConfigurationSection

object ConfigUtils {
    inline fun <reified V> loadMap(section: ConfigurationSection?): Map<String, V> {
        if (section == null) {
            return mapOf()
        }

        val result = mutableMapOf<String, V>()
        for (key in section.getKeys(false)) {
            val value = section[key]
            if (value is V) {
                result[key] = value
            }
        }

        return result.toMap()
    }

    fun loadIntMap(section: ConfigurationSection?) = loadMap<Int>(section)
    fun loadDoubleMap(section: ConfigurationSection?) = loadMap<Double>(section)
    fun loadBooleanMap(section: ConfigurationSection?) = loadMap<Boolean>(section)
    fun loadStringMap(section: ConfigurationSection?) = loadMap<String>(section)
    fun loadSectionMap(section: ConfigurationSection?) = loadMap<ConfigurationSection>(section)
}
