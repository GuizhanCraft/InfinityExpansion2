@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.HardenedGlass
import net.guizhanss.infinityexpansion2.implementation.items.groups.IEItemGroups
import net.guizhanss.infinityexpansion2.implementation.items.machines.MaterialGenerator
import net.guizhanss.infinityexpansion2.implementation.items.machines.VirtualFarm
import net.guizhanss.infinityexpansion2.implementation.items.materials.EnderEssence
import net.guizhanss.infinityexpansion2.implementation.items.materials.SimpleMaterial
import net.guizhanss.infinityexpansion2.implementation.items.materials.Singularity
import net.guizhanss.infinityexpansion2.implementation.items.materials.VoidBlock
import net.guizhanss.infinityexpansion2.implementation.items.materials.VoidGlass
import net.guizhanss.infinityexpansion2.implementation.items.tools.Strainer
import net.guizhanss.infinityexpansion2.implementation.items.tools.StrainerBase
import net.guizhanss.infinityexpansion2.implementation.recipes.IERecipeTypes
import net.guizhanss.infinityexpansion2.utils.RecipeUtils
import net.guizhanss.infinityexpansion2.utils.items.MaterialType
import net.guizhanss.infinityexpansion2.utils.items.buildSlimefunItem
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

object IEItems {
    private val GLASS = ItemStack(Material.GLASS)
    private val COBBLESTONE = ItemStack(Material.COBBLESTONE)
    private val STICK = ItemStack(Material.STICK)
    private val STRING = ItemStack(Material.STRING)
    private val DIAMOND_PICKAXE = ItemStack(Material.DIAMOND_PICKAXE)
    private val WATER_BUCKET = ItemStack(Material.WATER_BUCKET)
    private val LAVA_BUCKET = ItemStack(Material.LAVA_BUCKET)
    private val GRASS_BLOCK = ItemStack(Material.GRASS_BLOCK)

    // <editor-fold desc="Materials" collapsed="true">
    val ENDER_ESSENCE = buildSlimefunItem<EnderEssence> {
        id = "ENDER_ESSENCE"
        material = MaterialType.Material(Material.BLAZE_POWDER)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.GEO_MINER
        recipe = RecipeUtils.empty()
    }

    val COMPRESSED_COBBLESTONE_1 = buildSlimefunItem<SimpleMaterial> {
        id = "COMPRESSED_COBBLESTONE_1"
        material = MaterialType.Material(Material.ANDESITE)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = RecipeUtils.full(COBBLESTONE)
    }

    val COMPRESSED_COBBLESTONE_2 = buildSlimefunItem<SimpleMaterial> {
        id = "COMPRESSED_COBBLESTONE_2"
        material = MaterialType.Material(Material.ANDESITE)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = RecipeUtils.full(COMPRESSED_COBBLESTONE_1)
    }

    val COMPRESSED_COBBLESTONE_3 = buildSlimefunItem<SimpleMaterial> {
        id = "COMPRESSED_COBBLESTONE_3"
        material = MaterialType.Material(Material.ANDESITE)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = RecipeUtils.full(COMPRESSED_COBBLESTONE_2)
    }

    val COMPRESSED_COBBLESTONE_4 = buildSlimefunItem<SimpleMaterial> {
        id = "COMPRESSED_COBBLESTONE_4"
        material = MaterialType.Material(Material.STONE)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = RecipeUtils.full(COMPRESSED_COBBLESTONE_3)
    }

    val COMPRESSED_COBBLESTONE_5 = buildSlimefunItem<SimpleMaterial> {
        id = "COMPRESSED_COBBLESTONE_5"
        material = MaterialType.Material(Material.STONE)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = RecipeUtils.full(COMPRESSED_COBBLESTONE_4)
    }

    val MAGSTEEL = buildSlimefunItem<SimpleMaterial> {
        id = "MAGSTEEL"
        material = MaterialType.Material(Material.BRICK)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            SlimefunItems.MAGNESIUM_INGOT,
            SlimefunItems.STEEL_INGOT,
            SlimefunItems.MAGNESIUM_DUST,
        )
    }

    val TITANIUM = buildSlimefunItem<SimpleMaterial> {
        id = "TITANIUM"
        material = MaterialType.Material(Material.IRON_INGOT)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            SlimefunItems.REINFORCED_ALLOY_INGOT,
            SlimefunItems.DAMASCUS_STEEL_INGOT,
            SlimefunItems.HARDENED_METAL_INGOT,
        )
    }

    val MYTHRIL = buildSlimefunItem<SimpleMaterial> {
        id = "MYTHRIL"
        material = MaterialType.Material(Material.IRON_INGOT)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            SlimefunItems.MAGNESIUM_INGOT,
            IRON_SINGULARITY,
            SlimefunItems.MAGNESIUM_DUST,
        )
    }

    val ADAMANTITE = buildSlimefunItem<SimpleMaterial> {
        id = "ADAMANTITE"
        material = MaterialType.Material(Material.BRICK)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            SlimefunItems.REDSTONE_ALLOY,
            DIAMOND_SINGULARITY,
            MAGSTEEL,
        )
    }

    val MAGNONIUM = buildSlimefunItem<SimpleMaterial> {
        id = "MAGNONIUM"
        material = MaterialType.Material(Material.NETHER_BRICK)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            MAGSTEEL,
            MAGNESIUM_SINGULARITY,
            ENDER_ESSENCE,
        )
    }

    val VOID_BIT = buildSlimefunItem<SimpleMaterial> {
        id = "VOID_BIT"
        material = MaterialType.Material(Material.IRON_NUGGET)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = IERecipeTypes.VOID_HARVESTER
        recipe = RecipeUtils.empty()
    }

    val VOID_DUST = buildSlimefunItem<SimpleMaterial> {
        id = "VOID_DUST"
        material = MaterialType.Material(Material.GUNPOWDER)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = RecipeUtils.full(VOID_BIT)
    }

    val VOID_INGOT = buildSlimefunItem<SimpleMaterial> {
        id = "VOID_INGOT"
        material = MaterialType.Material(Material.NETHERITE_INGOT)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = RecipeUtils.full(VOID_DUST)
    }

    val VOID_BLOCK = buildSlimefunItem<VoidBlock> {
        id = "VOID_BLOCK"
        material = MaterialType.Material(Material.NETHERITE_BLOCK)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = RecipeUtils.full(VOID_INGOT)
    }

    val INFINITY_INGOT = buildSlimefunItem<SimpleMaterial> {
        id = "INFINITY_INGOT"
        material = MaterialType.Material(Material.IRON_INGOT)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            EARTH_SINGULARITY,
            MYTHRIL,
            FORTUNE_SINGULARITY,
            MAGIC_SINGULARITY,
            VOID_INGOT,
            METAL_SINGULARITY,
        )
    }

    val MAGSTEEL_PLATE = buildSlimefunItem<SimpleMaterial> {
        id = "MAGSTEEL_PLATE"
        material = MaterialType.Material(Material.NETHERITE_SCRAP)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.SMELTERY
        recipe = RecipeUtils.surround(SlimefunItems.HARDENED_METAL_INGOT, MAGSTEEL)
    }

    val MACHINE_CIRCUIT = buildSlimefunItem<SimpleMaterial> {
        id = "MACHINE_CIRCUIT"
        material = MaterialType.Material(Material.GOLD_INGOT)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            SlimefunItems.COPPER_INGOT, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.COPPER_INGOT,
            SlimefunItems.COPPER_INGOT, SlimefunItems.SILICON, SlimefunItems.COPPER_INGOT,
            SlimefunItems.COPPER_INGOT, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.COPPER_INGOT,
        )
    }

    val MACHINE_CORE = buildSlimefunItem<SimpleMaterial> {
        id = "MACHINE_CORE"
        material = MaterialType.Material(Material.IRON_BLOCK)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            TITANIUM, MACHINE_CIRCUIT, TITANIUM,
            MACHINE_CIRCUIT, MACHINE_PLATE, MACHINE_CIRCUIT,
            TITANIUM, MACHINE_CIRCUIT, TITANIUM,
        )
    }

    val MACHINE_PLATE = buildSlimefunItem<SimpleMaterial> {
        id = "MACHINE_PLATE"
        material = MaterialType.Material(Material.PAPER)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_ALLOY_INGOT,
            MAGSTEEL_PLATE, TITANIUM, MAGSTEEL_PLATE,
            SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_ALLOY_INGOT,
        )
    }

    val VOID_GLASS = buildSlimefunItem<VoidGlass>(16) {
        id = "VOID_GLASS"
        material = MaterialType.Material(Material.GLASS)
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = RecipeUtils.surround(VOID_BLOCK, GLASS)
    }
    // </editor-fold>

    // <editor-fold desc="Tools" collapsed="true">
    val ENDER_FLAME = buildSlimefunItem<SimpleMaterial> {
        id = "ENDER_FLAME"
        material = MaterialType.Material(Material.ENCHANTED_BOOK)
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.MAGIC_WORKBENCH
        recipe = RecipeUtils.surround(ItemStack(Material.BOOK), ENDER_ESSENCE)

        postCreate = {
            it.addUnsafeEnchantment(Enchantment.getByName("fire_aspect")!!, 10)
        }
    }

    val VEIN_MINER_RUNE = buildSlimefunItem<SimpleMaterial> {
        id = "VEIN_MINER_RUNE"
        material = MaterialType.Material(Material.DIAMOND)
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.MAGIC_WORKBENCH
        recipe = arrayOf(
            MAGSTEEL_PLATE, SlimefunItems.PICKAXE_OF_VEIN_MINING, MAGSTEEL_PLATE,
            ENDER_ESSENCE, SlimefunItems.BLANK_RUNE, ENDER_ESSENCE,
            MAGSTEEL_PLATE, SlimefunItems.PICKAXE_OF_VEIN_MINING, MAGSTEEL_PLATE,
        )
    }

    val STRAINER_BASE = buildSlimefunItem<StrainerBase> {
        id = "STRAINER_BASE"
        material = MaterialType.Material(Material.SANDSTONE_WALL)
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            STICK, STRING, STICK,
            STICK, STRING, STICK,
            MAGSTEEL, MAGSTEEL, MAGSTEEL,
        )
    }

    val STRAINER_1 = buildSlimefunItem<Strainer>(30) {
        id = "STRAINER_1"
        material = MaterialType.Material(Material.FISHING_ROD)
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            STICK, STRING, STICK,
            STRING, STICK, STRING,
            STICK, STRING, STICK,
        )
    }

    val STRAINER_2 = buildSlimefunItem<Strainer>(60) {
        id = "STRAINER_2"
        material = MaterialType.Material(Material.FISHING_ROD)
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL, STRING, MAGSTEEL,
            STRING, STRAINER_1, STRING,
            MAGSTEEL, STRING, MAGSTEEL,
        )
    }

    val STRAINER_3 = buildSlimefunItem<Strainer>(90) {
        id = "STRAINER_3"
        material = MaterialType.Material(Material.FISHING_ROD)
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            SlimefunItems.REINFORCED_ALLOY_INGOT, STRING, SlimefunItems.REINFORCED_ALLOY_INGOT,
            STRING, STRAINER_2, STRING,
            SlimefunItems.REINFORCED_ALLOY_INGOT, STRING, SlimefunItems.REINFORCED_ALLOY_INGOT,
        )
    }
    // </editor-fold>

    // <editor-fold desc="Machines" collapsed="true">
    val COBBLESTONE_GENERATOR_1 = buildSlimefunItem<MaterialGenerator>(Material.COBBLESTONE, 1, 24) {
        id = "COBBLESTONE_GENERATOR_1"
        material = MaterialType.Material(Material.SMOOTH_STONE)
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL, DIAMOND_PICKAXE, MAGSTEEL,
            WATER_BUCKET, COMPRESSED_COBBLESTONE_2, LAVA_BUCKET,
            MAGSTEEL, MACHINE_CIRCUIT, MAGSTEEL,
        )
    }

    val COBBLESTONE_GENERATOR_2 = buildSlimefunItem<MaterialGenerator>(Material.COBBLESTONE, 4, 120) {
        id = "COBBLESTONE_GENERATOR_2"
        material = MaterialType.Material(Material.SMOOTH_STONE)
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL, COBBLESTONE_GENERATOR_1, MAGSTEEL,
            WATER_BUCKET, COMPRESSED_COBBLESTONE_3, LAVA_BUCKET,
            MACHINE_CIRCUIT, COBBLESTONE_GENERATOR_1, MACHINE_CIRCUIT,
        )
    }

    val COBBLESTONE_GENERATOR_3 = buildSlimefunItem<MaterialGenerator>(Material.COBBLESTONE, 16, 360) {
        id = "COBBLESTONE_GENERATOR_3"
        material = MaterialType.Material(Material.SMOOTH_STONE)
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            VOID_BLOCK, COBBLESTONE_GENERATOR_2, MACHINE_PLATE,
            WATER_BUCKET, COMPRESSED_COBBLESTONE_4, LAVA_BUCKET,
            MACHINE_CIRCUIT, COBBLESTONE_GENERATOR_2, VOID_BLOCK,
        )
    }

    val COBBLESTONE_GENERATOR_4 = buildSlimefunItem<MaterialGenerator>(Material.COBBLESTONE, 64, 800) {
        id = "COBBLESTONE_GENERATOR_4"
        material = MaterialType.Material(Material.SMOOTH_STONE)
        itemGroup = IEItemGroups.INFINITY_DISPLAY
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            INFINITY_INGOT, VOID_INGOT, null, null, VOID_INGOT, INFINITY_INGOT,
            null, VOID_INGOT, COBBLESTONE_GENERATOR_3, COBBLESTONE_GENERATOR_3, VOID_INGOT, null,
            null, VOID_INGOT, COBBLESTONE_GENERATOR_3, COBBLESTONE_GENERATOR_3, VOID_INGOT, null,
            null, VOID_INGOT, COBBLESTONE_GENERATOR_3, COBBLESTONE_GENERATOR_3, VOID_INGOT, null,
            null, VOID_INGOT, COBBLESTONE_GENERATOR_3, COBBLESTONE_GENERATOR_3, VOID_INGOT, null,
            INFINITY_INGOT, VOID_INGOT, null, null, VOID_INGOT, INFINITY_INGOT,
        )
    }

    val VIRTUAL_FARM_1 = buildSlimefunItem<VirtualFarm>(300, 18) {
        id = "VIRTUAL_FARM_1"
        material = MaterialType.Material(Material.GRASS_BLOCK)
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            GLASS, GLASS, GLASS,
            MAGSTEEL, ItemStack(Material.DIAMOND_HOE), MAGSTEEL,
            MACHINE_CIRCUIT, GRASS_BLOCK, MACHINE_CIRCUIT,
        )
    }

    val VIRTUAL_FARM_2 = buildSlimefunItem<VirtualFarm>(60, 90) {
        id = "VIRTUAL_FARM_2"
        material = MaterialType.Material(Material.CRIMSON_NYLIUM)
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            SlimefunItems.HARDENED_GLASS, SlimefunItems.HARDENED_GLASS, SlimefunItems.HARDENED_GLASS,
            MAGNONIUM, VIRTUAL_FARM_1, MAGNONIUM,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
        )
    }

    val VIRTUAL_FARM_3 = buildSlimefunItem<VirtualFarm>(30, 270) {
        id = "VIRTUAL_FARM_3"
        material = MaterialType.Material(Material.WARPED_NYLIUM)
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            VOID_BLOCK, VIRTUAL_FARM_2, VOID_BLOCK,
            MACHINE_CORE, VIRTUAL_FARM_2, MACHINE_CORE,
            VOID_BLOCK, VIRTUAL_FARM_2, VOID_BLOCK,
        )
    }

    val VIRTUAL_FARM_4 = buildSlimefunItem<VirtualFarm>(15, 540) {
        id = "VIRTUAL_FARM_4"
        material = MaterialType.Material(Material.END_STONE)
        itemGroup = IEItemGroups.INFINITY_DISPLAY
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = RecipeUtils.expand6( // TODO: revert to arrayOf
            GLASS, GLASS, GLASS, GLASS, GLASS, GLASS,
            GLASS, null, null, null, null, GLASS,
            GLASS, null, null, null, null, GLASS,
            GLASS, GRASS_BLOCK, GRASS_BLOCK, GRASS_BLOCK, GRASS_BLOCK, GLASS,
            MACHINE_PLATE, SlimefunItems.CROP_GROWTH_ACCELERATOR_2, VIRTUAL_FARM_3, VIRTUAL_FARM_3, SlimefunItems.CROP_GROWTH_ACCELERATOR_2, MACHINE_PLATE,
            // TODO: MACHINE_PLATE, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CIRCUIT, MACHINE_PLATE,
        )
    }
    // </editor-fold>

    // <editor-fold desc="Generators" collapsed="true">
    // </editor-fold>

    // <editor-fold desc="Singularities" collapsed="true">
    val IRON_SINGULARITY = buildSlimefunItem<Singularity>(
        2000, mapOf(
            ItemStack(Material.IRON_INGOT) to 1,
            ItemStack(Material.IRON_BLOCK) to 9,
        )
    ) {
        id = "IRON_SINGULARITY"
        material = MaterialType.Material(Material.IRON_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val GOLD_SINGULARITY = buildSlimefunItem<Singularity>(
        2000, mapOf(
            ItemStack(Material.GOLD_INGOT) to 1,
            ItemStack(Material.GOLD_BLOCK) to 9,
            SlimefunItems.GOLD_4K to 1,
            SlimefunItems.GOLD_6K to 2,
            SlimefunItems.GOLD_8K to 3,
            SlimefunItems.GOLD_10K to 4,
            SlimefunItems.GOLD_12K to 5,
            SlimefunItems.GOLD_14K to 6,
            SlimefunItems.GOLD_16K to 7,
            SlimefunItems.GOLD_18K to 8,
            SlimefunItems.GOLD_20K to 9,
            SlimefunItems.GOLD_22K to 10,
            SlimefunItems.GOLD_24K to 11,
            SlimefunItems.GOLD_24K_BLOCK to 99,
        )
    ) {
        id = "GOLD_SINGULARITY"
        material = MaterialType.Material(Material.GOLD_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val LAPIS_SINGULARITY = buildSlimefunItem<Singularity>(
        1500, mapOf(
            ItemStack(Material.LAPIS_LAZULI) to 1,
            ItemStack(Material.LAPIS_BLOCK) to 9,
        )
    ) {
        id = "LAPIS_SINGULARITY"
        material = MaterialType.Material(Material.LAPIS_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val REDSTONE_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            ItemStack(Material.REDSTONE) to 1,
            ItemStack(Material.REDSTONE_BLOCK) to 9,
        )
    ) {
        id = "REDSTONE_SINGULARITY"
        material = MaterialType.Material(Material.REDSTONE_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val QUARTZ_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            ItemStack(Material.QUARTZ) to 1,
            ItemStack(Material.QUARTZ_BLOCK) to 4,
        )
    ) {
        id = "QUARTZ_SINGULARITY"
        material = MaterialType.Material(Material.QUARTZ_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val COPPER_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            ItemStack(Material.COPPER_INGOT) to 1,
            ItemStack(Material.COPPER_BLOCK) to 9,
            SlimefunItems.COPPER_INGOT to 1,
        )
    ) {
        id = "COPPER_SINGULARITY"
        material = MaterialType.Material(Material.COPPER_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val TIN_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.TIN_INGOT to 1,
        )
    ) {
        id = "TIN_SINGULARITY"
        material = MaterialType.Material(Material.IRON_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val LEAD_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.LEAD_INGOT to 1,
        )
    ) {
        id = "LEAD_SINGULARITY"
        material = MaterialType.Material(Material.IRON_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val SILVER_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.SILVER_INGOT to 1,
        )
    ) {
        id = "SILVER_SINGULARITY"
        material = MaterialType.Material(Material.IRON_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val ZINC_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.ZINC_INGOT to 1,
        )
    ) {
        id = "ZINC_SINGULARITY"
        material = MaterialType.Material(Material.IRON_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val ALUMINUM_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.ALUMINUM_INGOT to 1,
        )
    ) {
        id = "ALUMINUM_SINGULARITY"
        material = MaterialType.Material(Material.IRON_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val MAGNESIUM_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.MAGNESIUM_INGOT to 1,
        )
    ) {
        id = "MAGNESIUM_SINGULARITY"
        material = MaterialType.Material(Material.IRON_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val DIAMOND_SINGULARITY = buildSlimefunItem<Singularity>(
        500, mapOf(
            ItemStack(Material.DIAMOND) to 1,
            ItemStack(Material.DIAMOND_BLOCK) to 9,
            SlimefunItems.SYNTHETIC_DIAMOND to 1,
        )
    ) {
        id = "DIAMOND_SINGULARITY"
        material = MaterialType.Material(Material.DIAMOND_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val EMERALD_SINGULARITY = buildSlimefunItem<Singularity>(
        500, mapOf(
            ItemStack(Material.EMERALD) to 1,
            ItemStack(Material.EMERALD_BLOCK) to 9,
            SlimefunItems.SYNTHETIC_EMERALD to 1,
        )
    ) {
        id = "EMERALD_SINGULARITY"
        material = MaterialType.Material(Material.EMERALD_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val NETHERITE_SINGULARITY = buildSlimefunItem<Singularity>(
        200, mapOf(
            ItemStack(Material.NETHERITE_INGOT) to 1,
            ItemStack(Material.NETHERITE_BLOCK) to 9,
        )
    ) {
        id = "NETHERITE_SINGULARITY"
        material = MaterialType.Material(Material.NETHERITE_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val COAL_SINGULARITY = buildSlimefunItem<Singularity>(
        1500, mapOf(
            ItemStack(Material.COAL) to 1,
            ItemStack(Material.COAL_BLOCK) to 9,
            SlimefunItems.CARBON to 8,
            SlimefunItems.COMPRESSED_CARBON to 32,
            SlimefunItems.CARBON_CHUNK to 256,
        )
    ) {
        id = "COAL_SINGULARITY"
        material = MaterialType.Material(Material.COAL_BLOCK)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
        recipe = RecipeUtils.empty()
    }

    val INFINITY_SINGULARITY = buildSlimefunItem<Singularity>(
        100, mapOf(
            INFINITY_INGOT to 1,
        )
    ) {
        id = "INFINITY_SINGULARITY"
        material = MaterialType.Material(Material.SMOOTH_QUARTZ)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = RecipeUtils.empty()
    }

    val FORTUNE_SINGULARITY = buildSlimefunItem<SimpleMaterial> {
        id = "FORTUNE_SINGULARITY"
        material = MaterialType.Material(Material.NETHER_STAR)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            GOLD_SINGULARITY, DIAMOND_SINGULARITY, EMERALD_SINGULARITY,
            NETHERITE_SINGULARITY, ADAMANTITE,
        )
    }

    val MAGIC_SINGULARITY = buildSlimefunItem<SimpleMaterial> {
        id = "MAGIC_SINGULARITY"
        material = MaterialType.Material(Material.NETHER_STAR)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            REDSTONE_SINGULARITY, LAPIS_SINGULARITY, QUARTZ_SINGULARITY,
            MAGNESIUM_SINGULARITY, MAGNONIUM,
        )
    }

    val EARTH_SINGULARITY = buildSlimefunItem<SimpleMaterial> {
        id = "EARTH_SINGULARITY"
        material = MaterialType.Material(Material.NETHER_STAR)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            COMPRESSED_COBBLESTONE_4, COAL_SINGULARITY, IRON_SINGULARITY,
            COPPER_SINGULARITY, LEAD_SINGULARITY,
        )
    }

    val METAL_SINGULARITY = buildSlimefunItem<SimpleMaterial> {
        id = "METAL_SINGULARITY"
        material = MaterialType.Material(Material.NETHER_STAR)
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            SILVER_SINGULARITY, ALUMINUM_SINGULARITY, TIN_SINGULARITY,
            ZINC_SINGULARITY, TITANIUM,
        )
    }
    // </editor-fold>
}
