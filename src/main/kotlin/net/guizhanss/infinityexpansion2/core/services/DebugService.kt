package net.guizhanss.infinityexpansion2.core.services

import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.debug.DebugCase
import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Level

class DebugService(private val plugin: InfinityExpansion2) {

    private var debugEnabled: Boolean = false
    private val debugCases = ConcurrentHashMap<DebugCase, Boolean>()

    init {
        val config = InfinityExpansion2.configService

        debugEnabled = config.debugEnabled.value
        val cfgCases = config.debugCases.value

        for (debugCase in DebugCase.entries) {
            val enabled = cfgCases.any { it.equals(debugCase.name, ignoreCase = true) }
            debugCases[debugCase] = enabled
        }
    }

    /**
     * Check if general debug is enabled
     */
    fun isDebugEnabled(): Boolean {
        return debugEnabled
    }

    /**
     * Enable or disable general debug
     */
    fun setDebugEnabled(enabled: Boolean) {
        debugEnabled = enabled
    }

    /**
     * Toggle general debug state
     */
    fun toggleDebug(): Boolean {
        debugEnabled = !debugEnabled
        return debugEnabled
    }

    /**
     * Check if a specific debug case is enabled
     */
    fun isEnabled(debugCase: DebugCase): Boolean {
        return debugEnabled && debugCases.getOrDefault(debugCase, false)
    }

    /**
     * Enable or disable a debug case at runtime
     */
    fun setEnabled(debugCase: DebugCase, enabled: Boolean) {
        debugCases[debugCase] = enabled
    }

    /**
     * Toggle a debug case at runtime
     */
    fun toggle(debugCase: DebugCase): Boolean {
        val newState = !debugCases.getOrDefault(debugCase, false)
        debugCases[debugCase] = newState
        return newState
    }

    /**
     * Get all debug cases and their current states
     */
    fun getAllStates(): Map<DebugCase, Boolean> {
        return debugCases.toMap()
    }

    /**
     * Log a debug message for a specific debug case
     */
    fun log(debugCase: DebugCase, message: String) {
        if (isEnabled(debugCase)) {
            plugin.logger.log(Level.INFO, "[DEBUG:${debugCase.name}] $message")
        }
    }

    /**
     * Log a debug message with exception for a specific debug case
     */
    fun log(debugCase: DebugCase, ex: Throwable, message: String) {
        if (isEnabled(debugCase)) {
            plugin.logger.log(Level.WARNING, "[DEBUG:${debugCase.name}] $message", ex)
        }
    }

    /**
     * Get a list of all available debug cases
     */
    fun getAvailableCases(): List<DebugCase> {
        return DebugCase.entries.toList()
    }
}
