package net.guizhanss.infinityexpansion2.core.config

import org.bukkit.configuration.ConfigurationSection

/**
 * Represents a serializable configuration object.
 *
 * The reason why I create this interface is bukkit's will leave a classpath in config,
 * which can cause problems when the classpath is removed.
 *
 * The implementation should also have a public constructor that accepts a [ConfigurationSection] parameter.
 */
interface ConfigurationSerializable {
    fun serialize(): Map<String, Any>
}
