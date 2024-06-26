package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import java.text.NumberFormat
import java.util.Locale

object MachineLore {
    private const val POWER_PREFIX = "&8\u21E8 &e\u26A1 &7"
    private val numberFormatter get() = NumberFormat.getNumberInstance(Locale.getDefault())

    fun power(power: Int, suffix: String) = "$POWER_PREFIX${numberFormatter.format(power)} J$suffix"
    fun powerPerTick(powerPerTick: Int) = power(powerPerTick, "/t")
    fun powerPerSecond(powerPerTick: Int) =
        power((powerPerTick * 20.0 / Slimefun.getTickerTask().tickRate).toInt(), "/s")

    fun powerPerUse(powerPerUse: Int) = power(powerPerUse, "/use")
}
