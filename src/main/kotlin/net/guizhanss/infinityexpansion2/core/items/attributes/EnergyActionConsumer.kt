package net.guizhanss.infinityexpansion2.core.items.attributes

import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent
import io.github.thebusybiscuit.slimefun4.core.attributes.ItemAttribute

/**
 * This is just an informational interface which provides convenience for other addons to retrieve the information.
 *
 * Should be implemented by an [EnergyNetComponent] that consumes energy when an action is performed.
 */
interface EnergyActionConsumer : ItemAttribute {
    fun getEnergyConsumptionPerAction(): Int
}
