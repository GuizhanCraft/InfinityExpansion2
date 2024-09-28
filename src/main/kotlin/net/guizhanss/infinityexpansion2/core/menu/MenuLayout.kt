package net.guizhanss.infinityexpansion2.core.menu

import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset

/**
 * Defines a series of layouts used in this addon.
 */
data class MenuLayout(
    val background: IntArray = intArrayOf(),
    val inputBorder: IntArray = intArrayOf(),
    val inputSlots: IntArray = intArrayOf(),
    val outputBorder: IntArray = intArrayOf(),
    val outputSlots: IntArray = intArrayOf(),
    val statusSlot: Int = 0
) {

    /**
     * Sets up the preset with this layout.
     */
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

    companion object {

        // one row, no input
        val OUTPUT_ONLY_ONE_ROW = MenuLayout(
            outputBorder = intArrayOf(1, 8),
            outputSlots = intArrayOf(2, 3, 4, 5, 6, 7),
            statusSlot = 0,
        )

        // all output slots
        val OUTPUT_ONLY = MenuLayout(
            background = intArrayOf(0, 1, 2, 3, 5, 6, 7, 8),
            outputBorder = intArrayOf(
                9, 10, 11, 12, 13, 14, 15, 16, 17,
                45, 46, 47, 48, 49, 50, 51, 52, 53,
            ),
            outputSlots = intArrayOf(
                18, 19, 20, 21, 22, 23, 24, 25, 26,
                27, 28, 29, 30, 31, 32, 33, 34, 35,
                36, 37, 38, 39, 40, 41, 42, 43, 44,
            ),
            statusSlot = 4,
        )

        // single item input, 3 rows of output
        val SINGLE_INPUT = MenuLayout(
            background = intArrayOf(3, 5, 6, 7, 8),
            inputBorder = intArrayOf(0, 2),
            inputSlots = intArrayOf(1),
            outputBorder = intArrayOf(
                9, 10, 11, 12, 13, 14, 15, 16, 17,
                45, 46, 47, 48, 49, 50, 51, 52, 53,
            ),
            outputSlots = intArrayOf(
                18, 19, 20, 21, 22, 23, 24, 25, 26,
                27, 28, 29, 30, 31, 32, 33, 34, 35,
                36, 37, 38, 39, 40, 41, 42, 43, 44,
            ),
            statusSlot = 4,
        )

        val HOPPER = MenuLayout(
            background = intArrayOf(18, 19, 20, 21, 23, 24, 25, 26),
            inputBorder = intArrayOf(0, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17),
            inputSlots = intArrayOf(1, 2, 3, 4, 5, 6, 7),
            outputBorder = intArrayOf(27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 44),
            outputSlots = intArrayOf(37, 38, 39, 40, 41, 42, 43),
            statusSlot = 22,
        )

        // single input, single output
        val SINGLE_INPUT_OUTPUT = MenuLayout(
            background = intArrayOf(3, 5),
            inputBorder = intArrayOf(0, 2),
            inputSlots = intArrayOf(1),
            outputBorder = intArrayOf(6, 8),
            outputSlots = intArrayOf(7),
            statusSlot = 4,
        )

        val CRAFTING_DEFAULT = MenuLayout(
            background = intArrayOf(
                5, 6, 7, 8,
                32,
                41, 42, 43, 44,
            ),
            inputBorder = intArrayOf(
                0, 1, 2, 3, 4,
                9, 13,
                18, 22,
                27, 31,
                36, 37, 38, 39, 40,
            ),
            inputSlots = intArrayOf(
                10, 11, 12,
                19, 20, 21,
                28, 29, 30,
            ),
            outputBorder = intArrayOf(
                15, 16, 17,
                24, 26,
                33, 34, 35,
            ),
            outputSlots = intArrayOf(25),
            statusSlot = 14,
        )

        // special layouts
        val STONEWORKS_FACTORY = MenuLayout(
            background = intArrayOf(
                0, 1, 2, 3, 4, 5, 6,
                11, 13, 15,
                18, 19, 20, 21, 22, 23, 24,
            ),
            outputBorder = intArrayOf(7, 8, 17, 25, 26),
            outputSlots = intArrayOf(16),
            statusSlot = 9,
        )

        val RESOURCE_SYNTHESIZER = MenuLayout(
            background = intArrayOf(
                3, 4, 5,
                12, 13, 14,
                21, 22, 23,
                27, 29, 33, 35,
                36, 44,
                45, 46, 47, 51, 52, 53,
            ),
            inputBorder = intArrayOf(
                0, 1, 2, 6, 7, 8,
                9, 11, 15, 17,
                18, 19, 20, 24, 25, 26,
            ),
            inputSlots = intArrayOf(10, 16),
            outputBorder = intArrayOf(
                28, 34, 37, 38, 42, 43,
                30, 31, 32,
                39, 41,
                48, 49, 50,
            ),
            outputSlots = intArrayOf(40),
            statusSlot = 13,
        )

        val GEAR_TRANSFORMER = MenuLayout(
            background = intArrayOf(
                3, 4, 5,
                12, 14,
                21, 22, 23,
                27, 29, 33, 35,
                36, 44,
                45, 46, 47, 51, 52, 53,
            ),
            inputSlots = intArrayOf(10, 16),
            outputBorder = intArrayOf(
                28, 30, 31, 32, 34,
                37, 38, 39, 41, 42, 43,
                48, 49, 50,
            ),
            outputSlots = intArrayOf(40),
            statusSlot = 13,
        )

        val ADVANCED_ANVIL = MenuLayout(
            background = intArrayOf(
                27, 28, 29, 33, 34, 35,
                36, 37, 38, 42, 43, 44,
                45, 46, 52, 53,
            ),
            inputBorder = intArrayOf(
                0, 1, 2, 3, 4, 5,
                9, 11, 12, 14,
                18, 19, 20, 21, 22, 23,
            ),
            inputSlots = intArrayOf(10, 13),
            outputBorder = intArrayOf(
                6, 7, 8,
                15, 17,
                24, 25, 26,
            ),
            outputSlots = intArrayOf(16),
            statusSlot = 40,
        )

        val INFINITY_WORKBENCH = MenuLayout(
            background = intArrayOf(
                6, 7, 8,
                15, 16, 17,
                24, 26,
                33, 35,
            ),
            inputSlots = intArrayOf(
                0, 1, 2, 3, 4, 5,
                9, 10, 11, 12, 13, 14,
                18, 19, 20, 21, 22, 23,
                27, 28, 29, 30, 31, 32,
                36, 37, 38, 39, 40, 41,
                45, 46, 47, 48, 49, 50,
            ),
            outputBorder = intArrayOf(42, 43, 44, 51, 53),
            outputSlots = intArrayOf(52),
            statusSlot = 34,
        )
    }
}
