package net.guizhanss.infinityexpansion2.utils

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import java.lang.reflect.Method

object SlimefunReflection {

    private val _isAutoLoadingEnabled: Boolean by lazy {
        detectAutoLoadingState()
    }

    fun isAutoLoadingEnabled(): Boolean = _isAutoLoadingEnabled

    private fun detectAutoLoadingState(): Boolean {
        // Try official Slimefun API first: Slimefun.getRegistry().isAutoLoadingEnabled()
        try {
            val registryMethod: Method = Slimefun::class.java.getMethod("getRegistry")
            val registry = registryMethod.invoke(Slimefun.instance())
            val isEnabledMethod: Method = registry.javaClass.getMethod("isAutoLoadingEnabled")
            return isEnabledMethod.invoke(registry) as Boolean
        } catch (_: Exception) {
            // Other exceptions, try fork version
        }

        // Try fork version: Slimefun.getConfigManager().isAutoLoadingEnable()
        try {
            val configManagerMethod: Method = Slimefun::class.java.getMethod("getConfigManager")
            val configManager = configManagerMethod.invoke(Slimefun.instance())
            val isEnabledMethod: Method = configManager.javaClass.getMethod("isAutoLoadingEnable")
            return isEnabledMethod.invoke(configManager) as Boolean
        } catch (_: Exception) {
            // Other exceptions
        }

        // Default to false if neither method is available
        return false
    }
}
