package net.guizhanss.infinityexpansion2.utils

import net.guizhanss.infinityexpansion2.InfinityExpansion2
import org.bukkit.block.Block
import org.bukkit.block.data.Waterlogged

object BlockUtils {
    fun isWaterLogged(b: Block, tickRate: Int = 63) =
        if (InfinityExpansion2.sfTickCount() % tickRate == 0) {
            val blockData = b.blockData

            if (blockData is Waterlogged) {
                BlockStorageUtils.setBoolean(b.location, "water_logged", blockData.isWaterlogged)
                blockData.isWaterlogged
            } else {
                false
            }
        } else {
            BlockStorageUtils.getBoolean(b.location, "water_logged")
        }

    fun hasLightFromSky(b: Block) =
        b.location.add(0.0, 1.0, 0.0).block.lightFromSky.toInt() == 15
}
