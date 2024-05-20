package net.guizhanss.infinityexpansion2.implementation.items.groups

import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.constant.Keys
import net.guizhanss.infinityexpansion2.utils.items.MaterialType
import org.bukkit.Material

object IEItemGroups {
    val MAIN = MainGroup(
        Keys.GROUP_MAIN,
        InfinityExpansion2.localization.getItemGroupItem(
            MaterialType.Material(Material.NETHER_STAR),
            "main"
        )
    )

    val MATERIALS = SubGroup(
        Keys.GROUP_MATERIALS,
        InfinityExpansion2.localization.getItemGroupItem(
            MaterialType.Material(Material.NETHER_STAR),
            "materials"
        )
    )

    val BASIC_MACHINES = SubGroup(
        Keys.GROUP_BASIC_MACHINES,
        InfinityExpansion2.localization.getItemGroupItem(
            MaterialType.Material(Material.LOOM),
            "basic_machines"
        )
    )

    val ADVANCED_MACHIENS = SubGroup(
        Keys.GROUP_ADVANCED_MACHINES,
        InfinityExpansion2.localization.getItemGroupItem(
            MaterialType.Material(Material.BLAST_FURNACE),
            "advanced_machines"
        )
    )

    val SINGULARITIES = SubGroup(
        Keys.GROUP_SINGULARITIES,
        InfinityExpansion2.localization.getItemGroupItem(
            MaterialType.Material(Material.NETHERITE_BLOCK),
            "singularities"
        )
    )

    val MOB_SIMULATION = SubGroup(
        Keys.GROUP_MOB_SIMULATION,
        InfinityExpansion2.localization.getItemGroupItem(
            MaterialType.Material(Material.BEACON),
            "mob_simulation"
        )
    )

    val STORAGE = SubGroup(
        Keys.GROUP_STORAGE,
        InfinityExpansion2.localization.getItemGroupItem(
            MaterialType.Material(Material.BEEHIVE),
            "storage"
        )
    )

    // TODO: infinity group

    init {
        MAIN.addSubGroups(MATERIALS, BASIC_MACHINES, ADVANCED_MACHIENS, SINGULARITIES, MOB_SIMULATION, STORAGE)
        MOB_SIMULATION.isCrossAddonItemGroup = true
        MAIN.register(InfinityExpansion2.instance)
    }
}
