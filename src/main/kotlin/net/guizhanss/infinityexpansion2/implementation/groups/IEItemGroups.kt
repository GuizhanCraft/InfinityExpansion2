package net.guizhanss.infinityexpansion2.implementation.groups

import io.github.seggan.sf4k.item.builder.asMaterialType
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.bukkitext.createKey
import org.bukkit.Material

object IEItemGroups {

    val MAIN = MainGroup(
        "main".createKey(),
        InfinityExpansion2.localization.getItemGroupItem(
            Material.NETHER_STAR.asMaterialType(),
            "main"
        )
    )

    val MATERIALS = SubGroup(
        "materials".createKey(),
        InfinityExpansion2.localization.getItemGroupItem(
            Material.NETHER_STAR.asMaterialType(),
            "materials"
        )
    )

    val SINGULARITIES = SubGroup(
        "singularities".createKey(),
        InfinityExpansion2.localization.getItemGroupItem(
            Material.NETHERITE_BLOCK.asMaterialType(),
            "singularities"
        )
    )

    val SLIMEFUN_EXPANSION = SubGroup(
        "slimefun_expansion".createKey(),
        InfinityExpansion2.localization.getItemGroupItem(
            Material.SLIME_BLOCK.asMaterialType(),
            "slimefun_expansion"
        )
    )

    val FOOD = SubGroup(
        "food".createKey(),
        InfinityExpansion2.localization.getItemGroupItem(
            Material.COOKED_BEEF.asMaterialType(),
            "food"
        )
    )

    val TOOLS = SubGroup(
        "tools".createKey(),
        InfinityExpansion2.localization.getItemGroupItem(
            Material.DIAMOND_PICKAXE.asMaterialType(),
            "tools"
        )
    )

    val GEAR = SubGroup(
        "gear".createKey(),
        InfinityExpansion2.localization.getItemGroupItem(
            Material.DIAMOND_CHESTPLATE.asMaterialType(),
            "gear"
        )
    )

    val MACHINES = SubGroup(
        "machines".createKey(),
        InfinityExpansion2.localization.getItemGroupItem(
            Material.LOOM.asMaterialType(),
            "machines"
        )
    )

    val GENERATORS = SubGroup(
        "generators".createKey(),
        InfinityExpansion2.localization.getItemGroupItem(
            Material.BLAST_FURNACE.asMaterialType(),
            "generators"
        )
    )

    val MOB_SIMULATION = SubGroup(
        "mob_simulation".createKey(),
        InfinityExpansion2.localization.getItemGroupItem(
            Material.BEACON.asMaterialType(),
            "mob_simulation"
        )
    )

    val STORAGE = SubGroup(
        "storage".createKey(),
        InfinityExpansion2.localization.getItemGroupItem(
            Material.BEEHIVE.asMaterialType(),
            "storage"
        )
    )

    val HIDDEN = SubGroup(
        "hidden".createKey(),
        InfinityExpansion2.localization.getItemGroupItem(
            Material.BARRIER.asMaterialType(),
            "hidden"
        )
    )

    init {
        // TODO: Guide group
        MAIN.addSubGroups(
            MATERIALS,
            SINGULARITIES,
            SLIMEFUN_EXPANSION,
            FOOD,
            TOOLS,
            GEAR,
            MACHINES,
            GENERATORS,
            MOB_SIMULATION,
            STORAGE,
        )
        MOB_SIMULATION.isCrossAddonItemGroup = true
        MAIN.register(InfinityExpansion2.instance)
    }
}
