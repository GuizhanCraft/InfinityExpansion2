package net.guizhanss.infinityexpansion2.utils

import net.guizhanss.infinityexpansion2.InfinityExpansion2
import java.util.logging.Level

object Debug {

    fun info(case: DebugCase, message: String) {
        InfinityExpansion2.instance.logger.log(Level.INFO, "[DEBUG] $case: $message")
    }

    enum class DebugCase {
        NONE,
        TAG,
    }
}
