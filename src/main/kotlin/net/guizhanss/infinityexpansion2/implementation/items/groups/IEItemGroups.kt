package net.guizhanss.infinityexpansion2.implementation.items.groups

import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.items.groups.infinity.InfinityGroup
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

    val TOOLS = SubGroup(
        Keys.GROUP_TOOLS,
        InfinityExpansion2.localization.getItemGroupItem(
            MaterialType.Material(Material.DIAMOND_PICKAXE),
            "tools"
        )
    )

    val MACHINES = SubGroup(
        Keys.GROUP_MACHINES,
        InfinityExpansion2.localization.getItemGroupItem(
            MaterialType.Material(Material.LOOM),
            "machines"
        )
    )

    val GENERATORS = SubGroup(
        Keys.GROUP_GENERATORS,
        InfinityExpansion2.localization.getItemGroupItem(
            MaterialType.Material(Material.BLAST_FURNACE),
            "generators"
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

    val INFINITY = InfinityGroup(
        Keys.GROUP_INFINITY,
        InfinityExpansion2.localization.getItemGroupItem(
            MaterialType.Material(Material.RESPAWN_ANCHOR),
            "infinity"
        )
    )

    val INFINITY_DISPLAY = SubGroup(
        Keys.GROUP_INFINITY_DISPLAY,
        InfinityExpansion2.localization.getItemGroupItem(
            MaterialType.Material(Material.RESPAWN_ANCHOR),
            "infinity_display"
        )
    )

    init {
        MAIN.addMenuItem(WikiMenuItem)
        MAIN.addSubGroups(MATERIALS, TOOLS, MACHINES, GENERATORS, SINGULARITIES, MOB_SIMULATION, STORAGE, INFINITY)
        MOB_SIMULATION.isCrossAddonItemGroup = true
        MAIN.register(InfinityExpansion2.instance)
    }
}
