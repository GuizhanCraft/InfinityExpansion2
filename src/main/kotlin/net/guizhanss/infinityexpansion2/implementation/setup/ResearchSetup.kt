package net.guizhanss.infinityexpansion2.implementation.setup

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.researches.Research
import net.guizhanss.guizhanlib.kt.slimefun.items.toItem
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.utils.bukkitext.ie2Key

internal object ResearchSetup {

    private const val FIRST_ID = 314000000

    // create(0, "harvesting_void", "Harvesting the Void", 35,
    //                Materials.VOID_BIT, Materials.VOID_DUST, Materials.VOID_INGOT, Machines.VOID_HARVESTER
    //        );
    //        create(1, "infinity_gear", "Infinity Gear", 60,
    //                Gear.AXE, Gear.BLADE, Gear.BOW, Gear.PICKAXE, Gear.SHOVEL, Gear.SHIELD,
    //                Gear.CROWN, Gear.CHESTPLATE, Gear.LEGGINGS, Gear.BOOTS, Gear.INFINITY_MATRIX
    //        );
    //        create(2, "singularities", "Creating Singularities", 35,
    //                Materials.ALUMINUM_SINGULARITY,
    //                Materials.SILVER_SINGULARITY,
    //                Materials.COPPER_SINGULARITY,
    //                Materials.LEAD_SINGULARITY,
    //                Materials.ZINC_SINGULARITY,
    //                Materials.TIN_SINGULARITY,
    //                Materials.MAGNESIUM_SINGULARITY,
    //                Materials.COAL_SINGULARITY,
    //                Materials.IRON_SINGULARITY,
    //                Materials.GOLD_SINGULARITY,
    //                Materials.DIAMOND_SINGULARITY,
    //                Materials.EMERALD_SINGULARITY,
    //                Materials.LAPIS_SINGULARITY,
    //                Materials.REDSTONE_SINGULARITY,
    //                Materials.NETHERITE_SINGULARITY,
    //                Materials.QUARTZ_SINGULARITY,
    //                Machines.SINGULARITY_CONSTRUCTOR,
    //                Machines.RESOURCE_SYNTHESIZER
    //        );
    //        create(3, "infinity_ingot", "Creating Infinity", 40,
    //                Materials.EARTH_SINGULARITY, Materials.FORTUNE_SINGULARITY, Materials.MAGIC_SINGULARITY,
    //                Materials.METAL_SINGULARITY, Materials.INFINITE_INGOT, Blocks.INFINITY_FORGE
    //        );
    //        create(4, "harvesting_end", "Harvesting the End", 20,
    //                Materials.ENDER_ESSENCE, Gear.ENDER_FLAME, Gear.VEIN_MINER_RUNE
    //        );
    //        create(5, "mob_data", "Virtual Mob Farms", 30,
    //                MobData.CHAMBER, MobData.EMPTY_DATA_CARD, MobData.INFUSER,
    //                MobData.COW, MobData.SHEEP, MobData.CHICKEN
    //        );
    //        create(6, "oscillators", "Improving Quarries", 30,
    //                Quarries.DIAMOND_OSCILLATOR, Quarries.EMERALD_OSCILLATOR, Quarries.LAPIS_OSCILLATOR,
    //                Quarries.REDSTONE_OSCILLATOR, Quarries.QUARTZ_OSCILLATOR
    //        );
    //        create(7, "machine_materials", "Machine Materials", 20,
    //                Materials.MAGSTEEL, Materials.MAGSTEEL_PLATE, Materials.MACHINE_CIRCUIT,
    //                Materials.MACHINE_CORE, Materials.MACHINE_PLATE
    //        );
    //        create(8, "compressed_cobble", "Compressing Cobblestone", 15,
    //                Materials.COBBLE_1, Materials.COBBLE_2, Materials.COBBLE_3,
    //                Materials.COBBLE_4, Materials.COBBLE_5, Machines.COBBLE_PRESS
    //        );
    //        create(9, "starter_machines", "Starter Machines", 15,
    //                Machines.BASIC_COBBLE, Machines.BASIC_GROWER,
    //                Machines.BASIC_TREE, Quarries.BASIC_QUARRY
    //        );
    //        create(10, "strainers", "Material Strainers", 10,
    //                Blocks.STRAINER_BASE, Materials.BASIC_STRAINER,
    //                Materials.ADVANCED_STRAINER, Materials.REINFORCED_STRAINER
    //        );
    //        create(11, "starter_power", "Starter Power", 15,
    //                Generators.BASIC_PANEL, Generators.HYDRO
    //        );
    //        create(12, "advanced_power", "Advanced Power", 35,
    //                Generators.ADVANCED_PANEL, Generators.GEOTHERMAL, Generators.REINFORCED_HYDRO,
    //                Generators.REINFORCED_GEOTHERMAL, Generators.CELESTIAL_PANEL,
    //                SlimefunExtension.ADVANCED_NETHER_STAR_REACTOR
    //        );
    //        create(13, "advanced_machines", "Advanced Machines", 40,
    //                Machines.DUST_EXTRACTOR, Machines.EXTREME_FREEZER, Machines.GEO_QUARRY,
    //                Machines.DECOMPRESSOR, Machines.STONEWORKS_FACTORY, Machines.BASIC_OBSIDIAN,
    //                Machines.INGOT_FORMER, Blocks.ADVANCED_ANVIL, Machines.URANIUM_EXTRACTOR,
    //                Machines.GEAR_TRANSFORMER
    //        );
    //        create(14, "upgraded_machines", "Upgraded Machines", 40,
    //                SlimefunExtension.ADVANCED_CHARGER, SlimefunExtension.ADVANCED_ENCHANTER,
    //                SlimefunExtension.ADVANCED_DISENCHANTER, SlimefunExtension.ADVANCED_SMELTERY,
    //                Machines.ADVANCED_COBBLE, Machines.ADVANCED_GROWER, Machines.ADVANCED_TREE,
    //                SlimefunExtension.ADVANCED_GEO_MINER, Quarries.ADVANCED_QUARRY
    //        );
    //        create(15, "infinity_upgrades", "Infinity Upgrades", 80,
    //                Machines.INFINITE_VOID_HARVESTER, Machines.INFINITY_COBBLE, Machines.INFINITY_CONSTRUCTOR,
    //                Machines.INFINITY_GROWER, Machines.INFINITY_TREE, Machines.INFINITY_INGOT_FORMER,
    //                Generators.INFINITE_PANEL, Generators.INFINITY_REACTOR, Storage.INFINITY_STORAGE,
    //                SlimefunExtension.INFINITY_CAPACITOR, SlimefunExtension.INFINITY_CHARGER,
    //                SlimefunExtension.INFINITY_DISENCHANTER, SlimefunExtension.INFINITY_ENCHANTER,
    //                Quarries.INFINITY_QUARRY, Machines.INFINITY_DUST_EXTRACTOR
    //        );
    //        create(16, "void_upgrades", "Void Upgrades", 45,
    //                Generators.VOID_PANEL, SlimefunExtension.VOID_CAPACITOR, Storage.VOID_STORAGE,
    //                Machines.POWERED_BEDROCK, Quarries.VOID_QUARRY
    //        );
    //        create(17, "advanced_alloys", "Advanced Alloys", 30,
    //                Materials.TITANIUM, Materials.ADAMANTITE, Materials.MAGNONIUM, Materials.MYTHRIL
    //        );
    //        create(18, "big_storage", "Big Storage", 20,
    //                Storage.STORAGE_FORGE, Storage.BASIC_STORAGE, Storage.ADVANCED_STORAGE, Storage.REINFORCED_STORAGE
    //        );
    //        create(19, "infinity_materials", "Infinity Materials", 40,
    //                Materials.INFINITY_SINGULARITY, Materials.INFINITE_CORE, Materials.INFINITE_CIRCUIT
    //        );
    //        create(20, "neutral_mob_data", "Neutral Mob Data", 25,
    //                MobData.SLIME, MobData.MAGMA_CUBE, MobData.BEE, MobData.VILLAGER
    //        );
    //        create(21, "hostile_mob_data", "Hostile Mob Data", 30,
    //                MobData.ZOMBIE, MobData.SPIDER, MobData.SKELETON,
    //                MobData.CREEPER, MobData.GUARDIAN, MobData.WITCH
    //        );
    //        create(22, "advanced_mob_data", "Advanced Mob Data", 45,
    //                MobData.WITHER_SKELETON, MobData.ENDERMEN, MobData.IRON_GOLEM, MobData.BLAZE
    //        );
    //        create(23, "boss_mob_data", "Boss Mob Data", 60,
    //                MobData.WITHER, MobData.ENDER_DRAGON
    //        );

    val HARVESTING_VOID = createResearch(0, "harvesting_void", 35)
    val INFINITY_GEAR = createResearch(1, "infinity_gear", 60)
    val SINGULARITIES = createResearch(2, "singularities", 35)
    val INFINITY_INGOT = createResearch(3, "infinity_ingot", 40)
    val HARVESTING_END = createResearch(4, "harvesting_end", 20)
    val MOB_DATA = createResearch(5, "mob_data", 40)
    val OSCILLATORS = createResearch(6, "oscillators", 30)
    val MACHINE_MATERIALS = createResearch(7, "machine_materials", 20)
    val COMPRESSED_COBBLE = createResearch(8, "compressed_cobble", 15)
    val STARTER_MACHINES = createResearch(9, "starter_machines", 15)
    val STRAINERS = createResearch(10, "strainers", 10)
    val STARTER_POWER = createResearch(11, "starter_power", 15)
    val ADVANCED_POWER = createResearch(12, "advanced_power", 35)
    val ADVANCED_MACHINES = createResearch(13, "advanced_machines", 40)
    val UPGRADED_MACHINES = createResearch(14, "upgraded_machines", 40)
    val INFINITY_UPGRADES = createResearch(15, "infinity_upgrades", 80)
    val VOID_UPGRADES = createResearch(16, "void_upgrades", 45)
    val ADVANCED_ALLOYS = createResearch(17, "advanced_alloys", 30)
    val BIG_STORAGE = createResearch(18, "big_storage", 20)
    val INFINITY_MATERIALS = createResearch(19, "infinity_materials", 40)


    init {
        HARVESTING_VOID.addItems(
            IEItems.VOID_BIT,
            IEItems.VOID_DUST,
            IEItems.VOID_INGOT,
            IEItems.VOID_BLOCK,
            IEItems.VOID_HARVESTER,
        ).register()

        INFINITY_GEAR.addItems(
            IEItems.INFINITY_PICKAXE,
            IEItems.INFINITY_AXE,
            IEItems.INFINITY_SHOVEL,
            IEItems.INFINITY_HOE,
            IEItems.INFINITY_MATRIX,
            IEItems.INFINITY_HELMET,
            IEItems.INFINITY_CHESTPLATE,
            IEItems.INFINITY_LEGGINGS,
            IEItems.INFINITY_BOOTS,
            IEItems.INFINITY_SHIELD,
            IEItems.INFINITY_SWORD,
            IEItems.INFINITY_BOW,
            IEItems.INFINITY_CROSSBOW,
        ).register()

        SINGULARITIES.addItems(
            IEItems.ALUMINUM_SINGULARITY,
            IEItems.SILVER_SINGULARITY,
            IEItems.COPPER_SINGULARITY,
            IEItems.LEAD_SINGULARITY,
            IEItems.ZINC_SINGULARITY,
            IEItems.TIN_SINGULARITY,
            IEItems.MAGNESIUM_SINGULARITY,
            IEItems.COAL_SINGULARITY,
            IEItems.IRON_SINGULARITY,
            IEItems.GOLD_SINGULARITY,
            IEItems.DIAMOND_SINGULARITY,
            IEItems.EMERALD_SINGULARITY,
            IEItems.LAPIS_SINGULARITY,
            IEItems.REDSTONE_SINGULARITY,
            IEItems.NETHERITE_SINGULARITY,
            IEItems.QUARTZ_SINGULARITY,
            IEItems.SINGULARITY_CONSTRUCTOR,
            IEItems.RESOURCE_SYNTHESIZER,
        ).register()

    }

    private fun createResearch(numId: Int, id: String, defaultCost: Int) =
        Research(ie2Key(id), FIRST_ID + numId, InfinityExpansion2.localization.getResearchName(id), defaultCost)

    private fun Research.addItems(vararg items: SlimefunItemStack): Research {
        items.forEach { item ->
            addItems(item.toItem())
        }
        return this
    }
}
