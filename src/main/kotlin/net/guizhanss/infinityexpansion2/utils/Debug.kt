package net.guizhanss.infinityexpansion2.utils

import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.debug.DebugCase

/**
 * Utility object for convenient debug logging
 */
object Debug {

    /**
     * Log a debug message for a specific debug case
     */
    fun log(debugCase: DebugCase, message: String) {
        InfinityExpansion2.debugService.log(debugCase, message)
    }
}
