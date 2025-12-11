package net.guizhanss.infinityexpansion2.implementation.items.generators

import net.guizhanss.guizhanlib.kt.minecraft.extensions.hasLightFromSky
import net.guizhanss.guizhanlib.kt.minecraft.extensions.isDay
import net.guizhanss.guizhanlib.kt.minecraft.extensions.isNight
import net.guizhanss.infinityexpansion2.utils.bukkitext.isWaterLogged
import org.bukkit.World
import org.bukkit.World.Environment
import org.bukkit.block.Block

enum class GeneratorType {
    HYDROELECTRIC {

        override fun generate(world: World, block: Block, sfItem: EnergyGenerator, def: Int) =
            if (block.isWaterLogged(sfItem.tickCount, sfItem.getCustomTickRate() * 30)) def else 0
    },
    GEOTHERMAL {

        override fun generate(world: World, block: Block, sfItem: EnergyGenerator, def: Int) =
            when (world.environment) {
                Environment.NETHER -> def * 2
                Environment.NORMAL -> def
                else -> 0
            }
    },
    SOLAR {

        override fun generate(world: World, block: Block, sfItem: EnergyGenerator, def: Int) =
            when (world.environment) {
                Environment.NORMAL -> {
                    if (world.isDay() && block.hasLightFromSky()) def
                    else 0
                }

                else -> 0
            }
    },
    LUNAR {

        override fun generate(world: World, block: Block, sfItem: EnergyGenerator, def: Int) =
            when (world.environment) {
                Environment.NETHER, Environment.THE_END -> def

                Environment.NORMAL -> {
                    if (world.isNight() || !block.hasLightFromSky()) def
                    else 0
                }

                else -> 0
            }
    },
    INFINITY {

        override fun generate(world: World, block: Block, sfItem: EnergyGenerator, def: Int) = def
    };

    abstract fun generate(world: World, block: Block, sfItem: EnergyGenerator, def: Int): Int
}
