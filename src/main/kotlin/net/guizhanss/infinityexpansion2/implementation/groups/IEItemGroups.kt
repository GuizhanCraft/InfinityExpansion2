package net.guizhanss.infinityexpansion2.implementation.groups

import net.guizhanss.guizhanlib.kt.slimefun.items.builder.MaterialType
import net.guizhanss.guizhanlib.kt.slimefun.items.builder.asMaterialType
import net.guizhanss.guizhanlib.kt.slimefun.items.toItem
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.bukkitext.createKey
import org.bukkit.Material

object IEItemGroups {

    val MAIN = MainGroup(
        "main".createKey(),
        InfinityExpansion2.localization.getItemGroupItem(
            Material.NETHER_STAR.asMaterialType(),
            "main"
        ).toItem()
    )

    val MATERIALS = createSubGroup("materials", Material.NETHER_STAR.asMaterialType())
    val SINGULARITIES = createSubGroup("singularities", Material.NETHERITE_BLOCK.asMaterialType())
    val SLIMEFUN_EXPANSION = createSubGroup("slimefun_expansion", Material.SLIME_BLOCK.asMaterialType())
    val FOOD = createSubGroup("food", Material.COOKED_BEEF.asMaterialType())
    val TOOLS = createSubGroup("tools", Material.DIAMOND_PICKAXE.asMaterialType())
    val GEAR = createSubGroup("gear", Material.DIAMOND_CHESTPLATE.asMaterialType())
    val MACHINES = createSubGroup("machines", Material.LOOM.asMaterialType())
    val GENERATORS = createSubGroup("generators", Material.BLAST_FURNACE.asMaterialType())
    val MOB_SIMULATION = createSubGroup("mob_simulation", Material.BEACON.asMaterialType())
    val STORAGE = createSubGroup("storage", Material.BEEHIVE.asMaterialType())
    val HIDDEN = createSubGroup("hidden", Material.BARRIER.asMaterialType())

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

    private fun createSubGroup(key: String, material: MaterialType) = SubGroup(
        key.createKey(),
        InfinityExpansion2.localization.getItemGroupItem(
            material,
            key
        ).toItem()
    )
}
