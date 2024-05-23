package net.guizhanss.infinityexpansion2.utils

import net.guizhanss.infinityexpansion2.InfinityExpansion2
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.block.data.Waterlogged

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

fun Block.hasLightFromSky() =
    location.add(0.0, 1.0, 0.0).block.lightFromSky.toInt() == 15

fun World.isDay() = time in 0 until 13000

fun World.isNight() = time in 13000..24000
