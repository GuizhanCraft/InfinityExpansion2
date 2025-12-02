package net.guizhanss.infinityexpansion2.core.items.attributes

import io.github.thebusybiscuit.slimefun4.core.attributes.ItemAttribute

/**
 * This indicates the item has a custom tick rate.
 */
interface CustomTickRateMachine : ItemAttribute {

    fun getCustomTickRate(): Int
}
