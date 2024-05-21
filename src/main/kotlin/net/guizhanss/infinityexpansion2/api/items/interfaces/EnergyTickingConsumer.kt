package net.guizhanss.infinityexpansion2.api.items.interfaces

import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent

/**
 * This is just an informational interface which provides convenience for other addons to retrieve the information.
 *
 * Should be implemented by an [EnergyNetComponent] that consumes energy every tick.
 */
interface EnergyTickingConsumer {
    fun getEnergyConsumptionPerTick(): Int
}
