package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder

object LoreUtils {
    fun powerPerTick(powerPerTick: Int) = LoreBuilder.power(powerPerTick, "/t")

    fun powerPerSecond(powerPerTick: Int) =
        LoreBuilder.powerPerSecond((powerPerTick * 20.0 / Slimefun.getTickerTask().tickRate).toInt())
}
