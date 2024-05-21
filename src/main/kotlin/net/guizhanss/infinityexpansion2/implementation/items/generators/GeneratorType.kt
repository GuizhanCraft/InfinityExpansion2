package net.guizhanss.infinityexpansion2.implementation.items.generators

import net.guizhanss.infinityexpansion2.utils.BlockUtils
import org.bukkit.World
import org.bukkit.block.Block

enum class GeneratorType {
    HYDROELECTRIC {
        override fun generate(world: World, block: Block, def: Int): Int {
            return if (BlockUtils.isWaterLogged(block)) def else 0
        }
    },
    GEOTHERMAL {
        override fun generate(world: World, block: Block, def: Int): Int {
            return when (world.environment) {
                World.Environment.NETHER -> def * 2
                World.Environment.NORMAL -> def
                else -> 0
            }
        }
    },
    SOLAR {
        override fun generate(world: World, block: Block, def: Int): Int {
            return when (world.environment) {
                World.Environment.NORMAL -> {
                    if (world.time < 13000 && BlockUtils.hasLightFromSky(block)) def
                    else 0
                }

                else -> 0
            }
        }
    },
    LUNAR {
        override fun generate(world: World, block: Block, def: Int): Int {
            return when (world.environment) {
                World.Environment.NETHER,
                World.Environment.THE_END -> def

                World.Environment.NORMAL -> {
                    if (world.time >= 13000 || !BlockUtils.hasLightFromSky(block)) def
                    else 0
                }

                else -> 0
            }
        }
    },
    INFINITY {
        override fun generate(world: World, block: Block, def: Int): Int {
            return def
        }
    };

    abstract fun generate(world: World, block: Block, def: Int): Int
}
