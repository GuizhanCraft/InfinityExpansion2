package net.guizhanss.infinityexpansion2.core.attributes

import net.guizhanss.infinityexpansion2.InfinityExpansion2

interface CustomTickRateMachine {
    fun getCustomTickRate(): Int

    fun shouldRun() = InfinityExpansion2.sfTickCount() % getCustomTickRate() == 0
}
