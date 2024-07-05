package net.guizhanss.infinityexpansion2.utils.bukkitext

import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.config.SerializableSection
import org.bukkit.configuration.ConfigurationSection
import java.util.logging.Level
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.functions

/**
 * Get a [SerializableSection] by deserializing the [ConfigurationSection].
 */
inline fun <reified T : SerializableSection> ConfigurationSection.getAsSerializable(
    defaultVal: T? = null,
): T? {
    try {
        T::class.companionObject?.functions?.find { it.name == "deserialize" }?.let { deserialize ->
            return deserialize.call(T::class.companionObjectInstance, this) as T
        }
    } catch (e: Exception) {
        InfinityExpansion2.log(Level.SEVERE, e, "An error has occurred while deserializing as ${T::class.simpleName}")
    }
    return defaultVal
}

/**
 * Get a [SerializableSection] by deserializing the [ConfigurationSection] with the given key.
 */
inline fun <reified T : SerializableSection> ConfigurationSection.getSerializable(
    key: String,
    defaultVal: T? = null,
): T? {
    val section = this.getConfigurationSection(key) ?: return defaultVal
    return section.getAsSerializable(defaultVal)
}
