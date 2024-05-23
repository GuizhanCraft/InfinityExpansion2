package net.guizhanss.infinityexpansion2.implementation.items.generators

import net.guizhanss.infinityexpansion2.utils.hasLightFromSky
import net.guizhanss.infinityexpansion2.utils.isDay
import net.guizhanss.infinityexpansion2.utils.isNight
import net.guizhanss.infinityexpansion2.utils.isWaterLogged
import org.bukkit.World
import org.bukkit.block.Block

enum class GeneratorType {
    HYDROELECTRIC {
        override fun generate(world: World, block: Block, def: Int) =
            if (block.isWaterLogged()) def else 0

    },
    GEOTHERMAL {
        override fun generate(world: World, block: Block, def: Int) = when (world.environment) {
            World.Environment.NETHER -> def * 2
            World.Environment.NORMAL -> def
            else -> 0
        }
    },
    SOLAR {
        override fun generate(world: World, block: Block, def: Int) = when (world.environment) {
            World.Environment.NORMAL -> {
                if (world.isDay() && block.hasLightFromSky()) def
                else 0
            }

            else -> 0
        }
    },
    LUNAR {
        override fun generate(world: World, block: Block, def: Int) = when (world.environment) {
            World.Environment.NETHER,
            World.Environment.THE_END -> def

            World.Environment.NORMAL -> {
                if (world.isNight() || !block.hasLightFromSky()) def
                else 0
            }

            else -> 0
        }
    },
    INFINITY {
        override fun generate(world: World, block: Block, def: Int) = def
    };

    abstract fun generate(world: World, block: Block, def: Int): Int
}
