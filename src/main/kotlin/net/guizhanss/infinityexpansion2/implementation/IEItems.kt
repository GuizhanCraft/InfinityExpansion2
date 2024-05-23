@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import net.guizhanss.infinityexpansion2.implementation.items.groups.IEItemGroups
import net.guizhanss.infinityexpansion2.implementation.items.machines.MaterialGenerator
import net.guizhanss.infinityexpansion2.implementation.items.machines.StoneworksFactory
import net.guizhanss.infinityexpansion2.implementation.items.machines.TreeGrower
import net.guizhanss.infinityexpansion2.implementation.items.machines.VirtualFarm
import net.guizhanss.infinityexpansion2.implementation.items.machines.VoidHarvester
import net.guizhanss.infinityexpansion2.implementation.items.materials.EnderEssence
import net.guizhanss.infinityexpansion2.implementation.items.materials.SimpleMaterial
import net.guizhanss.infinityexpansion2.implementation.items.materials.Singularity
import net.guizhanss.infinityexpansion2.implementation.items.materials.VoidBlock
import net.guizhanss.infinityexpansion2.implementation.items.materials.VoidGlass
import net.guizhanss.infinityexpansion2.implementation.items.tools.Strainer
import net.guizhanss.infinityexpansion2.implementation.items.tools.StrainerBase
import net.guizhanss.infinityexpansion2.implementation.recipes.IERecipeTypes
import net.guizhanss.infinityexpansion2.utils.fillRecipe
import net.guizhanss.infinityexpansion2.utils.items.MaterialType
import net.guizhanss.infinityexpansion2.utils.items.buildSlimefunItem
import net.guizhanss.infinityexpansion2.utils.items.convert
import net.guizhanss.infinityexpansion2.utils.surroundedBy
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
    private val PODZOL = ItemStack(Material.PODZOL)

    // <editor-fold desc="Singularities" collapsed="true">
    val IRON_SINGULARITY = buildSlimefunItem<Singularity>(
        2000, mapOf(
            ItemStack(Material.IRON_INGOT) to 1,
            ItemStack(Material.IRON_BLOCK) to 9,
        )
    ) {
        id = "IRON_SINGULARITY"
        material = Material.IRON_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
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
        material = Material.GOLD_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val LAPIS_SINGULARITY = buildSlimefunItem<Singularity>(
        1500, mapOf(
            ItemStack(Material.LAPIS_LAZULI) to 1,
            ItemStack(Material.LAPIS_BLOCK) to 9,
        )
    ) {
        id = "LAPIS_SINGULARITY"
        material = Material.LAPIS_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val REDSTONE_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            ItemStack(Material.REDSTONE) to 1,
            ItemStack(Material.REDSTONE_BLOCK) to 9,
        )
    ) {
        id = "REDSTONE_SINGULARITY"
        material = Material.REDSTONE_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val QUARTZ_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            ItemStack(Material.QUARTZ) to 1,
            ItemStack(Material.QUARTZ_BLOCK) to 4,
        )
    ) {
        id = "QUARTZ_SINGULARITY"
        material = Material.QUARTZ_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val COPPER_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            ItemStack(Material.COPPER_INGOT) to 1,
            ItemStack(Material.COPPER_BLOCK) to 9,
            SlimefunItems.COPPER_INGOT to 1,
        )
    ) {
        id = "COPPER_SINGULARITY"
        material = Material.COPPER_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val TIN_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.TIN_INGOT to 1,
        )
    ) {
        id = "TIN_SINGULARITY"
        material = Material.IRON_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val LEAD_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.LEAD_INGOT to 1,
        )
    ) {
        id = "LEAD_SINGULARITY"
        material = Material.IRON_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val SILVER_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.SILVER_INGOT to 1,
        )
    ) {
        id = "SILVER_SINGULARITY"
        material = Material.IRON_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val ZINC_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.ZINC_INGOT to 1,
        )
    ) {
        id = "ZINC_SINGULARITY"
        material = Material.IRON_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val ALUMINUM_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.ALUMINUM_INGOT to 1,
        )
    ) {
        id = "ALUMINUM_SINGULARITY"
        material = Material.IRON_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val MAGNESIUM_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.MAGNESIUM_INGOT to 1,
        )
    ) {
        id = "MAGNESIUM_SINGULARITY"
        material = Material.IRON_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val DIAMOND_SINGULARITY = buildSlimefunItem<Singularity>(
        500, mapOf(
            ItemStack(Material.DIAMOND) to 1,
            ItemStack(Material.DIAMOND_BLOCK) to 9,
            SlimefunItems.SYNTHETIC_DIAMOND to 1,
        )
    ) {
        id = "DIAMOND_SINGULARITY"
        material = Material.DIAMOND_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val EMERALD_SINGULARITY = buildSlimefunItem<Singularity>(
        500, mapOf(
            ItemStack(Material.EMERALD) to 1,
            ItemStack(Material.EMERALD_BLOCK) to 9,
            SlimefunItems.SYNTHETIC_EMERALD to 1,
        )
    ) {
        id = "EMERALD_SINGULARITY"
        material = Material.EMERALD_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val NETHERITE_SINGULARITY = buildSlimefunItem<Singularity>(
        200, mapOf(
            ItemStack(Material.NETHERITE_INGOT) to 1,
            ItemStack(Material.NETHERITE_BLOCK) to 9,
        )
    ) {
        id = "NETHERITE_SINGULARITY"
        material = Material.NETHERITE_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
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
        material = Material.COAL_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val FORTUNE_SINGULARITY = buildSlimefunItem<SimpleMaterial> {
        id = "FORTUNE_SINGULARITY"
        material = Material.NETHER_STAR.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            GOLD_SINGULARITY, DIAMOND_SINGULARITY, EMERALD_SINGULARITY,
            NETHERITE_SINGULARITY, ADAMANTITE,
        )
    }

    val MAGIC_SINGULARITY = buildSlimefunItem<SimpleMaterial> {
        id = "MAGIC_SINGULARITY"
        material = Material.NETHER_STAR.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            REDSTONE_SINGULARITY, LAPIS_SINGULARITY, QUARTZ_SINGULARITY,
            MAGNESIUM_SINGULARITY, MAGNONIUM,
        )
    }

    val EARTH_SINGULARITY = buildSlimefunItem<SimpleMaterial> {
        id = "EARTH_SINGULARITY"
        material = Material.NETHER_STAR.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            COMPRESSED_COBBLESTONE_4, COAL_SINGULARITY, IRON_SINGULARITY,
            COPPER_SINGULARITY, LEAD_SINGULARITY,
        )
    }

    val METAL_SINGULARITY = buildSlimefunItem<SimpleMaterial> {
        id = "METAL_SINGULARITY"
        material = Material.NETHER_STAR.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            SILVER_SINGULARITY, ALUMINUM_SINGULARITY, TIN_SINGULARITY,
            ZINC_SINGULARITY, TITANIUM,
        )
    }
    // </editor-fold>

    // <editor-fold desc="Materials" collapsed="true">
    val ENDER_ESSENCE = buildSlimefunItem<EnderEssence> {
        id = "ENDER_ESSENCE"
        material = Material.BLAZE_POWDER.convert()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.GEO_MINER
    }

    val COMPRESSED_COBBLESTONE_1 = buildSlimefunItem<SimpleMaterial> {
        id = "COMPRESSED_COBBLESTONE_1"
        material = Material.ANDESITE.convert()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = COBBLESTONE.fillRecipe()
    }

    val COMPRESSED_COBBLESTONE_2 = buildSlimefunItem<SimpleMaterial> {
        id = "COMPRESSED_COBBLESTONE_2"
        material = Material.ANDESITE.convert()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = COMPRESSED_COBBLESTONE_1.fillRecipe()
    }

    val COMPRESSED_COBBLESTONE_3 = buildSlimefunItem<SimpleMaterial> {
        id = "COMPRESSED_COBBLESTONE_3"
        material = Material.ANDESITE.convert()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = COMPRESSED_COBBLESTONE_2.fillRecipe()
    }

    val COMPRESSED_COBBLESTONE_4 = buildSlimefunItem<SimpleMaterial> {
        id = "COMPRESSED_COBBLESTONE_4"
        material = Material.STONE.convert()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = COMPRESSED_COBBLESTONE_3.fillRecipe()
    }

    val COMPRESSED_COBBLESTONE_5 = buildSlimefunItem<SimpleMaterial> {
        id = "COMPRESSED_COBBLESTONE_5"
        material = Material.STONE.convert()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = COMPRESSED_COBBLESTONE_4.fillRecipe()
    }

    val MAGSTEEL = buildSlimefunItem<SimpleMaterial> {
        id = "MAGSTEEL"
        material = Material.BRICK.convert()
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
        material = Material.IRON_INGOT.convert()
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
        material = Material.IRON_INGOT.convert()
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
        material = Material.BRICK.convert()
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
        material = Material.NETHER_BRICK.convert()
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
        material = Material.IRON_NUGGET.convert()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = IERecipeTypes.VOID_HARVESTER
    }

    val VOID_DUST = buildSlimefunItem<SimpleMaterial> {
        id = "VOID_DUST"
        material = Material.GUNPOWDER.convert()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = VOID_BIT.fillRecipe()
    }

    val VOID_INGOT = buildSlimefunItem<SimpleMaterial> {
        id = "VOID_INGOT"
        material = Material.NETHERITE_INGOT.convert()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = VOID_DUST.fillRecipe()
    }

    val VOID_BLOCK = buildSlimefunItem<VoidBlock> {
        id = "VOID_BLOCK"
        material = Material.NETHERITE_BLOCK.convert()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = VOID_INGOT.fillRecipe()
    }

    val INFINITY_INGOT = buildSlimefunItem<SimpleMaterial> {
        id = "INFINITY_INGOT"
        material = Material.IRON_INGOT.convert()
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

    // Singularity put here due to load order
    val INFINITY_SINGULARITY = buildSlimefunItem<Singularity>(
        100, mapOf(
            INFINITY_INGOT to 1,
        )
    ) {
        id = "INFINITY_SINGULARITY"
        material = Material.SMOOTH_QUARTZ.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
    }

    val MAGSTEEL_PLATE = buildSlimefunItem<SimpleMaterial> {
        id = "MAGSTEEL_PLATE"
        material = Material.NETHERITE_SCRAP.convert()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = SlimefunItems.HARDENED_METAL_INGOT surroundedBy MAGSTEEL
    }

    val MACHINE_CIRCUIT = buildSlimefunItem<SimpleMaterial> {
        id = "MACHINE_CIRCUIT"
        material = Material.GOLD_INGOT.convert()
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
        material = Material.IRON_BLOCK.convert()
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
        material = Material.PAPER.convert()
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
        material = Material.GLASS.convert()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = VOID_BLOCK surroundedBy GLASS
    }

    val INFINITY_MACHINE_CIRCUIT = buildSlimefunItem<SimpleMaterial> {
        id = "INFINITY_MACHINE_CIRCUIT"
        material = Material.DIAMOND.convert()
        itemGroup = IEItemGroups.INFINITY_DISPLAY
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            MACHINE_CIRCUIT, INFINITY_INGOT, MACHINE_CIRCUIT, MACHINE_CIRCUIT, INFINITY_INGOT, MACHINE_CIRCUIT,
            VOID_INGOT, MACHINE_CIRCUIT, VOID_INGOT, VOID_INGOT, MACHINE_CIRCUIT, VOID_INGOT,
            INFINITY_INGOT, VOID_INGOT, MACHINE_CIRCUIT, MACHINE_CIRCUIT, VOID_INGOT, INFINITY_INGOT,
            INFINITY_INGOT, VOID_INGOT, MACHINE_CIRCUIT, MACHINE_CIRCUIT, VOID_INGOT, INFINITY_INGOT,
            VOID_INGOT, MACHINE_CIRCUIT, VOID_INGOT, VOID_INGOT, MACHINE_CIRCUIT, VOID_INGOT,
            MACHINE_CIRCUIT, INFINITY_INGOT, MACHINE_CIRCUIT, MACHINE_CIRCUIT, INFINITY_INGOT, MACHINE_CIRCUIT,
        )
    }

    val INFINITY_MACHINE_CORE = buildSlimefunItem<SimpleMaterial> {
        id = "INFINITY_MACHINE_CORE"
        material = Material.DIAMOND_BLOCK.convert()
        itemGroup = IEItemGroups.INFINITY_DISPLAY
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            MACHINE_PLATE, MACHINE_CORE, INFINITY_INGOT, INFINITY_INGOT, MACHINE_CORE, MACHINE_PLATE,
            MACHINE_CORE, MACHINE_PLATE, MACHINE_CIRCUIT, MACHINE_CIRCUIT, MACHINE_PLATE, MACHINE_CORE,
            INFINITY_INGOT, MACHINE_CIRCUIT, INFINITY_INGOT, INFINITY_INGOT, MACHINE_CIRCUIT, INFINITY_INGOT,
            INFINITY_INGOT, MACHINE_CIRCUIT, INFINITY_INGOT, INFINITY_INGOT, MACHINE_CIRCUIT, INFINITY_INGOT,
            MACHINE_CORE, MACHINE_PLATE, MACHINE_CIRCUIT, MACHINE_CIRCUIT, MACHINE_PLATE, MACHINE_CORE,
            MACHINE_PLATE, MACHINE_CORE, INFINITY_INGOT, INFINITY_INGOT, MACHINE_CORE, MACHINE_PLATE,
        )
    }
    // </editor-fold>

    // <editor-fold desc="Tools" collapsed="true">
    val ENDER_FLAME = buildSlimefunItem<SimpleMaterial> {
        id = "ENDER_FLAME"
        material = Material.ENCHANTED_BOOK.convert()
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.MAGIC_WORKBENCH
        recipe = ItemStack(Material.BOOK) surroundedBy ENDER_ESSENCE

        postCreate = {
            it.addUnsafeEnchantment(Enchantment.getByName("fire_aspect")!!, 10)
        }
    }

    val VEIN_MINER_RUNE = buildSlimefunItem<SimpleMaterial> {
        id = "VEIN_MINER_RUNE"
        material = Material.DIAMOND.convert()
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
        material = Material.SANDSTONE_WALL.convert()
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
        material = Material.FISHING_ROD.convert()
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
        material = Material.FISHING_ROD.convert()
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
        material = Material.FISHING_ROD.convert()
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
        material = Material.SMOOTH_STONE.convert()
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
        material = Material.SMOOTH_STONE.convert()
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
        material = Material.SMOOTH_STONE.convert()
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
        material = Material.SMOOTH_STONE.convert()
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
        material = Material.GRASS_BLOCK.convert()
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
        material = Material.CRIMSON_NYLIUM.convert()
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
        material = Material.WARPED_NYLIUM.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            VOID_BLOCK, VIRTUAL_FARM_2, VOID_BLOCK,
            MACHINE_CORE, VIRTUAL_FARM_2, MACHINE_CORE,
            VOID_BLOCK, VIRTUAL_FARM_2, VOID_BLOCK,
        )
    }

    val VIRTUAL_FARM_4 = buildSlimefunItem<VirtualFarm>(10, 1000) {
        id = "VIRTUAL_FARM_4"
        material = Material.END_STONE.convert()
        itemGroup = IEItemGroups.INFINITY_DISPLAY
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            GLASS, GLASS, GLASS, GLASS, GLASS, GLASS,
            GLASS, null, null, null, null, GLASS,
            GLASS, null, null, null, null, GLASS,
            GLASS, GRASS_BLOCK, GRASS_BLOCK, GRASS_BLOCK, GRASS_BLOCK, GLASS,
            MACHINE_PLATE, SlimefunItems.CROP_GROWTH_ACCELERATOR_2, VIRTUAL_FARM_3, VIRTUAL_FARM_3, SlimefunItems.CROP_GROWTH_ACCELERATOR_2, MACHINE_PLATE,
            MACHINE_PLATE, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CIRCUIT, MACHINE_PLATE,
            // @formatter:on
        )
    }

    val TREE_GROWER_1 = buildSlimefunItem<TreeGrower>(600, 36) {
        id = "TREE_GROWER_1"
        material = Material.STRIPPED_OAK_WOOD.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            GLASS, GLASS, GLASS,
            MAGSTEEL, PODZOL, MAGSTEEL,
            MACHINE_CIRCUIT, VIRTUAL_FARM_1, MACHINE_CIRCUIT,
        )
    }

    val TREE_GROWER_2 = buildSlimefunItem<TreeGrower>(120, 180) {
        id = "TREE_GROWER_2"
        material = Material.STRIPPED_ACACIA_WOOD.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            SlimefunItems.HARDENED_GLASS, SlimefunItems.HARDENED_GLASS, SlimefunItems.HARDENED_GLASS,
            MAGNONIUM, TREE_GROWER_1, MAGNONIUM,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
        )
    }

    val TREE_GROWER_3 = buildSlimefunItem<TreeGrower>(60, 540) {
        id = "TREE_GROWER_3"
        material = Material.STRIPPED_CRIMSON_HYPHAE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            VOID_BLOCK, TREE_GROWER_2, VOID_BLOCK,
            MACHINE_CORE, TREE_GROWER_2, MACHINE_CORE,
            VOID_BLOCK, TREE_GROWER_2, VOID_BLOCK,
        )
    }

    val TREE_GROWER_4 = buildSlimefunItem<TreeGrower>(12, 2000) {
        id = "TREE_GROWER_4"
        material = Material.STRIPPED_WARPED_HYPHAE.convert()
        itemGroup = IEItemGroups.INFINITY_DISPLAY
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            GLASS, GLASS, GLASS, GLASS, GLASS, GLASS,
            GLASS, SlimefunItems.TREE_GROWTH_ACCELERATOR, null, null, SlimefunItems.TREE_GROWTH_ACCELERATOR, GLASS,
            GLASS, TREE_GROWER_3, null, null, TREE_GROWER_3, GLASS,
            GLASS, SlimefunItems.TREE_GROWTH_ACCELERATOR, null, null, SlimefunItems.TREE_GROWTH_ACCELERATOR, GLASS,
            MACHINE_PLATE, PODZOL, PODZOL, PODZOL, PODZOL, MACHINE_PLATE,
            MACHINE_PLATE, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CIRCUIT, MACHINE_PLATE,
            // @formatter:on
        )
    }

    val VOID_HARVESTER_1 = buildSlimefunItem<VoidHarvester>(1, 120) {
        id = "VOID_HARVESTER_1"
        material = Material.OBSIDIAN.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            TITANIUM, TITANIUM, TITANIUM,
            MACHINE_PLATE, SlimefunItems.GEO_MINER, MACHINE_PLATE,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
        )
    }

    val VOID_HARVESTER_2 = buildSlimefunItem<VoidHarvester>(8, 1200) {
        id = "VOID_HARVESTER_2"
        material = Material.OBSIDIAN.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE,
            MAGSTEEL_PLATE, VOID_HARVESTER_1, MAGSTEEL_PLATE,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
        )
    }

    val VOID_HARVESTER_3 = buildSlimefunItem<VoidHarvester>(64, 12000) {
        id = "VOID_HARVESTER_3"
        material = Material.CRYING_OBSIDIAN.convert()
        itemGroup = IEItemGroups.INFINITY_DISPLAY
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE,
            MAGNONIUM, VOID_INGOT, VOID_INGOT, VOID_INGOT, VOID_INGOT, MAGNONIUM,
            MAGNONIUM, VOID_INGOT, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CIRCUIT, VOID_INGOT, MAGNONIUM,
            MAGNONIUM, VOID_INGOT, VOID_HARVESTER_2, VOID_HARVESTER_2, VOID_INGOT, MAGNONIUM,
            MAGNONIUM, VOID_INGOT, VOID_INGOT, VOID_INGOT, VOID_INGOT, MAGNONIUM,
            MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE
        )
    }

    val STONEWORKS_FACTORY_1 = buildSlimefunItem<StoneworksFactory>(1, 200) {
        id = "STONEWORKS_FACTORY_1"
        material = Material.BLAST_FURNACE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL_PLATE, COBBLESTONE_GENERATOR_1, MAGSTEEL_PLATE,
            SlimefunItems.ELECTRIC_FURNACE, MACHINE_CIRCUIT, SlimefunItems.ELECTRIC_ORE_GRINDER,
            MAGSTEEL_PLATE, SlimefunItems.ELECTRIC_PRESS, MAGSTEEL_PLATE,
        )
    }

    val STONEWORKS_FACTORY_2 = buildSlimefunItem<StoneworksFactory>(4, 800) {
        id = "STONEWORKS_FACTORY_2"
        material = Material.BLAST_FURNACE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL_PLATE, COBBLESTONE_GENERATOR_2, MAGSTEEL_PLATE,
            SlimefunItems.ELECTRIC_FURNACE_2, STONEWORKS_FACTORY_1, SlimefunItems.ELECTRIC_ORE_GRINDER_2,
            MACHINE_CIRCUIT, SlimefunItems.ELECTRIC_PRESS_2, MACHINE_CIRCUIT,
        )
    }

    val STONEWORKS_FACTORY_3 = buildSlimefunItem<StoneworksFactory>(16, 2400) {
        id = "STONEWORKS_FACTORY_3"
        material = Material.BLAST_FURNACE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MACHINE_PLATE, COBBLESTONE_GENERATOR_3, VOID_BLOCK,
            SlimefunItems.ELECTRIC_FURNACE_3, STONEWORKS_FACTORY_2, SlimefunItems.ELECTRIC_ORE_GRINDER_3,
            VOID_BLOCK, SlimefunItems.ELECTRIC_PRESS_2, MACHINE_CIRCUIT,
        )
    }
    // </editor-fold>

    // <editor-fold desc="Generators" collapsed="true">
    // </editor-fold>
}
