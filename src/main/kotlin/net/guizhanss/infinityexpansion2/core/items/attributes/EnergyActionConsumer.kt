package net.guizhanss.infinityexpansion2.core.items.attributes

import io.github.thebusybiscuit.slimefun4.core.attributes.ItemAttribute

/**
 * An informational [ItemAttribute] that provides the energy consumption per action.
 */
interface EnergyActionConsumer : ItemAttribute {
    fun getEnergyConsumptionPerAction(): Int
}
