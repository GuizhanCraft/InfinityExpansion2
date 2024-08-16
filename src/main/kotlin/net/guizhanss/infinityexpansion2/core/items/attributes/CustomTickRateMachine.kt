package net.guizhanss.infinityexpansion2.core.items.attributes

import io.github.thebusybiscuit.slimefun4.core.attributes.ItemAttribute
import net.guizhanss.infinityexpansion2.InfinityExpansion2

/**
 * This indicates the item has a custom tick rate.
 */
interface CustomTickRateMachine : ItemAttribute {

    fun getCustomTickRate(): Int

    fun shouldRun() = InfinityExpansion2.sfTickCount() % getCustomTickRate() == 0
}
