package net.guizhanss.infinityexpansion2.utils.items

import net.guizhanss.infinityexpansion2.utils.powerPerTickToSecond
import java.text.NumberFormat
import java.util.Locale

object MachineLore {

    private const val POWER_PREFIX = "&8\u21E8 &e\u26A1 &7"
    private val numberFormatter get() = NumberFormat.getNumberInstance(Locale.getDefault())

    fun format(value: Int) = numberFormatter.format(value)

    fun power(power: Int, suffix: String) = "$POWER_PREFIX${format(power)} J$suffix"

    fun powerPerTick(powerPerTick: Int) = power(powerPerTick, "/t")

    fun powerPerSecond(powerPerTick: Int, tickRate: Int = 1) =
        power(powerPerTickToSecond(powerPerTick, tickRate), "/s")

    fun powerPerUse(powerPerUse: Int) = power(powerPerUse, "/use")
}
