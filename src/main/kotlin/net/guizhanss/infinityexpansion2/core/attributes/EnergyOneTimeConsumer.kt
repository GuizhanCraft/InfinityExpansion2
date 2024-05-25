package net.guizhanss.infinityexpansion2.core.attributes

import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent

/**
 * This is just an informational interface which provides convenience for other addons to retrieve the information.
 *
 * Should be implemented by an [EnergyNetComponent] that consumes energy when an action is performed.
 */
interface EnergyOneTimeConsumer {
    fun getEnergyConsumptionPerAction(): Int
}
