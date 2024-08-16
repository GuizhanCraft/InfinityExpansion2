package net.guizhanss.infinityexpansion2.core.items.attributes

import io.github.thebusybiscuit.slimefun4.core.attributes.ItemAttribute

interface EnergyProducer : ItemAttribute {

    fun getEnergyProduction(): Int
}
