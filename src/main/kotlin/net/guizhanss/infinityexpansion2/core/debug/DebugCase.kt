package net.guizhanss.infinityexpansion2.core.debug

/**
 * Enum representing different debug cases that can be enabled/disabled.
 */
enum class DebugCase {
    TAG,
    INFINITY_GEAR_CONFIG,
    MACHINE_RECIPE,
    OSCILLATOR,
    VEIN_MINER,
    ADVANCED_ANVIL,
    MOB_SIMULATION,
    STORAGE_UNIT,
    ;

    companion object {
        fun fromString(name: String): DebugCase? = entries.find { it.name.equals(name, ignoreCase = true) }
    }
}
