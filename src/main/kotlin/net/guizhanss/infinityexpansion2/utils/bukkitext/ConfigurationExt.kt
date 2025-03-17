package net.guizhanss.infinityexpansion2.utils.bukkitext

import net.guizhanss.guizhanlib.kt.common.extensions.flatten
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.config.SerializableSection
import org.bukkit.configuration.ConfigurationSection
import java.util.logging.Level
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.functions
import kotlin.reflect.full.isSuperclassOf
import kotlin.reflect.jvm.jvmErasure

/**
 * Get a [SerializableSection] by deserializing the [ConfigurationSection] or [Map].
 */
inline fun <reified T : SerializableSection> Any?.getAsSerializable(
    defaultVal: T? = null,
): T? {
    if (this == null) return defaultVal

    val current = when (this) {
        is ConfigurationSection -> this
        is Map<*, *> -> this
        else -> return defaultVal
    }

    try {
        T::class.companionObject?.functions?.find { func ->
            func.name == "deserialize" && func.parameters.getOrNull(1)?.type?.jvmErasure?.isSuperclassOf(current::class) == true
        }?.let { deserialize ->
            return deserialize.call(T::class.companionObjectInstance, current) as T
        }
    } catch (e: Exception) {
        InfinityExpansion2.log(Level.SEVERE, e, "An error has occurred while deserializing as ${T::class.simpleName}")
    }
    return defaultVal
}

inline fun <reified T : SerializableSection> List<Map<*, *>>?.getAsSerializableList(): List<T> {
    if (this == null) return emptyList()
    val result = mutableListOf<T>()

    this.forEach { entry ->
        entry.flatten().getAsSerializable<T>()?.let { result.add(it) }
    }
    return result
}

/**
 * Get a [SerializableSection] by deserializing the [ConfigurationSection] with the given key.
 */
inline fun <reified T : SerializableSection> ConfigurationSection?.getSerializable(
    key: String,
    defaultVal: T? = null,
): T? {
    if (this == null) return defaultVal
    val section = this.getConfigurationSection(key) ?: return defaultVal
    return section.getAsSerializable(defaultVal)
}
