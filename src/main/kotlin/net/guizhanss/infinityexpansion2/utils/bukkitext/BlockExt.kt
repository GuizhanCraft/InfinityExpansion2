package net.guizhanss.infinityexpansion2.utils.bukkitext

import net.guizhanss.guizhanlib.kt.slimefun.utils.getBoolean
import net.guizhanss.guizhanlib.kt.slimefun.utils.setBoolean
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import org.bukkit.block.Block
import org.bukkit.block.data.Waterlogged

private const val WATERLOGGED_KEY = "water_logged"

/**
 * Check if a block is waterlogged (result is cached and is updated every [tickRate] sf ticks).
 */
fun Block.isWaterLogged(tickRate: Int = 64) =
    if (InfinityExpansion2.sfTickCount() % tickRate == 0) {
        val blockData = blockData

        if (blockData is Waterlogged) {
            location.setBoolean(WATERLOGGED_KEY, blockData.isWaterlogged)
            blockData.isWaterlogged
        } else {
            false
        }
    } else {
        location.getBoolean(WATERLOGGED_KEY)
    }
