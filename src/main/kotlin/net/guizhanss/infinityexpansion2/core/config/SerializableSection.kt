package net.guizhanss.infinityexpansion2.core.config

import org.bukkit.configuration.ConfigurationSection

/**
 * Represents a serializable [ConfigurationSection].
 *
 * The reason why I create this interface is bukkit's will leave a classpath in config,
 * which can cause problems when the class is not loaded. (like when loading Slimefun's Items config)
 *
 * The implementation should also have a companion function called `deserialize` that
 * accepts a [ConfigurationSection] parameter and returns the class instance.
 */
fun interface SerializableSection {
    fun serialize(): Map<String, Any>
}
