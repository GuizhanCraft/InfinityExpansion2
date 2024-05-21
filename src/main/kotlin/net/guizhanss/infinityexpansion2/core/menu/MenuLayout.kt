package net.guizhanss.infinityexpansion2.core.menu

import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset

data class MenuLayout(
    val background: IntArray = intArrayOf(),
    val inputBorder: IntArray = intArrayOf(),
    val inputSlots: IntArray = intArrayOf(),
    val outputBorder: IntArray = intArrayOf(),
    val outputSlots: IntArray = intArrayOf(),
    val statusSlot: Int = 0
) {
    companion object {
        val OUTPUT_ONLY_ONE_ROW = MenuLayout(
            outputBorder = intArrayOf(1, 8),
            outputSlots = intArrayOf(2, 3, 4, 5, 6, 7),
        )

        val SINGLE_INPUT = MenuLayout(
            background = intArrayOf(3, 5, 6, 7, 8),
            inputBorder = intArrayOf(0, 2),
            inputSlots = intArrayOf(1),
            outputBorder = intArrayOf(9, 10, 11, 12, 13, 14, 15, 16, 17),
            outputSlots = intArrayOf(
                18, 19, 20, 21, 22, 23, 24, 25, 26,
                27, 28, 29, 30, 31, 32, 33, 34, 35,
                36, 37, 38, 39, 40, 41, 42, 43, 44,
            ),
            statusSlot = 4
        )
    }

    fun setupPreset(preset: BlockMenuPreset) {
        preset.drawBackground(background)
        preset.drawBackground(ChestMenuUtils.getInputSlotTexture(), inputBorder)
        preset.drawBackground(ChestMenuUtils.getOutputSlotTexture(), outputBorder)
        preset.drawBackground(intArrayOf(statusSlot))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MenuLayout

        if (!background.contentEquals(other.background)) return false
        if (!inputBorder.contentEquals(other.inputBorder)) return false
        if (!inputSlots.contentEquals(other.inputSlots)) return false
        if (!outputBorder.contentEquals(other.outputBorder)) return false
        if (!outputSlots.contentEquals(other.outputSlots)) return false
        if (statusSlot != other.statusSlot) return false

        return true
    }

    override fun hashCode(): Int {
        var result = background.contentHashCode()
        result = 31 * result + inputBorder.contentHashCode()
        result = 31 * result + inputSlots.contentHashCode()
        result = 31 * result + outputBorder.contentHashCode()
        result = 31 * result + outputSlots.contentHashCode()
        result = 31 * result + statusSlot
        return result
    }
}
