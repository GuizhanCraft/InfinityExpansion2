package net.guizhanss.infinityexpansion2.utils.bukkitext

import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.slimefunext.getBoolean
import net.guizhanss.infinityexpansion2.utils.slimefunext.setBoolean
import org.bukkit.block.Block
import org.bukkit.block.data.Waterlogged

/**
 * Check if a block is waterlogged (result is cached and is updated every [tickRate] sf ticks).
 */
fun Block.isWaterLogged(tickRate: Int = 64) =
    if (InfinityExpansion2.sfTickCount() % tickRate == 0) {
        val blockData = blockData

        if (blockData is Waterlogged) {
            location.setBoolean("water_logged", blockData.isWaterlogged)
            blockData.isWaterlogged
        } else {
            false
        }
    } else {
        location.getBoolean("water_logged")
    }
