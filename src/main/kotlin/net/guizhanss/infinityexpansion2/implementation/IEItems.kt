@file:Suppress("deprecation", "unused", "MemberVisibilityCanBePrivate")

package net.guizhanss.infinityexpansion2.implementation

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectionType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture
import net.guizhanss.infinityexpansion2.api.mobsim.MobDataCardProps
import net.guizhanss.infinityexpansion2.implementation.groups.IEItemGroups
import net.guizhanss.infinityexpansion2.implementation.items.food.CosmicMeatballs
import net.guizhanss.infinityexpansion2.implementation.items.food.UltimateStew
import net.guizhanss.infinityexpansion2.implementation.items.gear.InfinityArmor
import net.guizhanss.infinityexpansion2.implementation.items.gear.InfinityBoots
import net.guizhanss.infinityexpansion2.implementation.items.gear.InfinityBow
import net.guizhanss.infinityexpansion2.implementation.items.gear.InfinityTool
import net.guizhanss.infinityexpansion2.implementation.items.generators.EnergyGenerator
import net.guizhanss.infinityexpansion2.implementation.items.generators.GeneratorType
import net.guizhanss.infinityexpansion2.implementation.items.generators.InfinityReactor
import net.guizhanss.infinityexpansion2.implementation.items.machines.AdvancedAnvil
import net.guizhanss.infinityexpansion2.implementation.items.machines.CobblePress
import net.guizhanss.infinityexpansion2.implementation.items.machines.Decompressor
import net.guizhanss.infinityexpansion2.implementation.items.machines.DustExtractor
import net.guizhanss.infinityexpansion2.implementation.items.machines.ExtremeFreezer
import net.guizhanss.infinityexpansion2.implementation.items.machines.GearTransformer
import net.guizhanss.infinityexpansion2.implementation.items.machines.GeoQuarry
import net.guizhanss.infinityexpansion2.implementation.items.machines.InfinityWorkbench
import net.guizhanss.infinityexpansion2.implementation.items.machines.IngotFormer
import net.guizhanss.infinityexpansion2.implementation.items.machines.MaterialGenerator
import net.guizhanss.infinityexpansion2.implementation.items.machines.Quarry
import net.guizhanss.infinityexpansion2.implementation.items.machines.ResourceSynthesizer
import net.guizhanss.infinityexpansion2.implementation.items.machines.SingularityConstructor
import net.guizhanss.infinityexpansion2.implementation.items.machines.StoneworksFactory
import net.guizhanss.infinityexpansion2.implementation.items.machines.TreeGrower
import net.guizhanss.infinityexpansion2.implementation.items.machines.UraniumExtractor
import net.guizhanss.infinityexpansion2.implementation.items.machines.VirtualFarm
import net.guizhanss.infinityexpansion2.implementation.items.machines.VoidHarvester
import net.guizhanss.infinityexpansion2.implementation.items.materials.EnderEssence
import net.guizhanss.infinityexpansion2.implementation.items.materials.SimpleMaterial
import net.guizhanss.infinityexpansion2.implementation.items.materials.Singularity
import net.guizhanss.infinityexpansion2.implementation.items.materials.VoidBlock
import net.guizhanss.infinityexpansion2.implementation.items.materials.VoidGlass
import net.guizhanss.infinityexpansion2.implementation.items.mobsim.MobDataCard
import net.guizhanss.infinityexpansion2.implementation.items.mobsim.MobDataInfuser
import net.guizhanss.infinityexpansion2.implementation.items.mobsim.MobSimulationChamber
import net.guizhanss.infinityexpansion2.implementation.items.sfextension.AutoDisenchanter
import net.guizhanss.infinityexpansion2.implementation.items.sfextension.AutoEnchanter
import net.guizhanss.infinityexpansion2.implementation.items.sfextension.Capacitor
import net.guizhanss.infinityexpansion2.implementation.items.sfextension.ChargingBench
import net.guizhanss.infinityexpansion2.implementation.items.sfextension.GeoMiner
import net.guizhanss.infinityexpansion2.implementation.items.sfextension.NetherStarReactor
import net.guizhanss.infinityexpansion2.implementation.items.sfextension.Smeltery
import net.guizhanss.infinityexpansion2.implementation.items.tools.InfinityMatrix
import net.guizhanss.infinityexpansion2.implementation.items.tools.Oscillator
import net.guizhanss.infinityexpansion2.implementation.items.tools.Strainer
import net.guizhanss.infinityexpansion2.implementation.items.tools.StrainerBase
import net.guizhanss.infinityexpansion2.implementation.items.tools.VeinMinerRune
import net.guizhanss.infinityexpansion2.implementation.recipes.IERecipeTypes
import net.guizhanss.infinityexpansion2.utils.bukkitext.buildHiddenPotionEffect
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import net.guizhanss.infinityexpansion2.utils.emptyRecipe
import net.guizhanss.infinityexpansion2.utils.fillRecipe
import net.guizhanss.infinityexpansion2.utils.items.applyInfinityGearEnchantment
import net.guizhanss.infinityexpansion2.utils.items.buildSlimefunItem
import net.guizhanss.infinityexpansion2.utils.items.convert
import net.guizhanss.infinityexpansion2.utils.surroundedBy
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

/**
 * Stores almost all the items in Infinity Expansion 2, also responsible for registering them.
 */
object IEItems {

    //<editor-fold desc="Singularities" defaultstate="collapsed">
    val IRON_SINGULARITY = buildSlimefunItem<Singularity>(
        2000, mapOf(
            Material.IRON_INGOT.toItem() to 1,
            Material.IRON_BLOCK.toItem() to 9,
        )
    ) {
        id = "IRON_SINGULARITY"
        material = Material.IRON_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val GOLD_SINGULARITY = buildSlimefunItem<Singularity>(
        2000, mapOf(
            Material.GOLD_INGOT.toItem() to 1,
            Material.GOLD_BLOCK.toItem() to 9,
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
            Material.LAPIS_LAZULI.toItem() to 1,
            Material.LAPIS_BLOCK.toItem() to 9,
        )
    ) {
        id = "LAPIS_SINGULARITY"
        material = Material.LAPIS_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val REDSTONE_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            Material.REDSTONE.toItem() to 1,
            Material.REDSTONE_BLOCK.toItem() to 9,
        )
    ) {
        id = "REDSTONE_SINGULARITY"
        material = Material.REDSTONE_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val QUARTZ_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            Material.QUARTZ.toItem() to 1,
            Material.QUARTZ_BLOCK.toItem() to 4,
        )
    ) {
        id = "QUARTZ_SINGULARITY"
        material = Material.QUARTZ_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val COPPER_SINGULARITY = buildSlimefunItem<Singularity>(
        3000, mapOf(
            Material.COPPER_INGOT.toItem() to 1,
            Material.COPPER_BLOCK.toItem() to 9,
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
            Material.DIAMOND.toItem() to 1,
            Material.DIAMOND_BLOCK.toItem() to 9,
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
            Material.EMERALD.toItem() to 1,
            Material.EMERALD_BLOCK.toItem() to 9,
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
            Material.NETHERITE_INGOT.toItem() to 1,
            Material.NETHERITE_BLOCK.toItem() to 9,
        )
    ) {
        id = "NETHERITE_SINGULARITY"
        material = Material.NETHERITE_BLOCK.convert()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val COAL_SINGULARITY = buildSlimefunItem<Singularity>(
        1500, mapOf(
            Material.COAL.toItem() to 1,
            Material.COAL_BLOCK.toItem() to 9,
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
    //</editor-fold>

    //<editor-fold desc="Materials" defaultstate="collapsed">
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
        recipe = Material.COBBLESTONE.toItem().fillRecipe()
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

    // singularities here due to load order
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

    val INFINITY_INGOT = buildSlimefunItem<SimpleMaterial> {
        id = "INFINITY_INGOT"
        material = Material.IRON_INGOT.convert()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            // @formatter:off
            EARTH_SINGULARITY, MYTHRIL, FORTUNE_SINGULARITY,
            MAGIC_SINGULARITY, VOID_INGOT, METAL_SINGULARITY,
            // @formatter:on
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
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
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

    val VOID_GLASS = buildSlimefunItem<VoidGlass>(16) {
        id = "VOID_GLASS"
        material = Material.GLASS.convert()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = VOID_BLOCK surroundedBy Material.GLASS.toItem()
    }

    val INFINITY_MACHINE_CIRCUIT = buildSlimefunItem<SimpleMaterial> {
        id = "INFINITY_MACHINE_CIRCUIT"
        material = Material.DIAMOND.convert()
        itemGroup = IEItemGroups.MATERIALS
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
        itemGroup = IEItemGroups.MATERIALS
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

    val OSCILLATOR_FRAME = buildSlimefunItem<SimpleMaterial> {
        id = "OSCILLATOR_FRAME"
        material = Material.IRON_BARS.convert()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MACHINE_PLATE, SlimefunItems.BLISTERING_INGOT_3, MACHINE_PLATE,
            SlimefunItems.BLISTERING_INGOT_3, null, SlimefunItems.BLISTERING_INGOT_3,
            MACHINE_PLATE, SlimefunItems.BLISTERING_INGOT_3, MACHINE_PLATE,
        )
    }
    //</editor-fold>

    //<editor-fold desc="Slimefun Expansion" defaultstate="collapsed">
    val VOID_CAPACITOR = buildSlimefunItem<Capacitor>(16_000_000) {
        id = "VOID_CAPACITOR"
        material = HeadTexture.CAPACITOR_25.convert()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            VOID_INGOT,
            REDSTONE_SINGULARITY,
            VOID_INGOT,
            VOID_INGOT,
            SlimefunItems.ENERGIZED_CAPACITOR,
            VOID_INGOT,
            VOID_INGOT,
            REDSTONE_SINGULARITY,
            VOID_INGOT
        )
    }

    val INFINITY_CAPACITOR = buildSlimefunItem<Capacitor>(64_000_000) {
        id = "INFINITY_CAPACITOR"
        material = HeadTexture.CAPACITOR_25.convert()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, INFINITY_INGOT, VOID_INGOT, VOID_INGOT, INFINITY_INGOT, null,
            null, INFINITY_INGOT, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CIRCUIT, INFINITY_INGOT, null,
            null, INFINITY_INGOT, SlimefunItems.ENERGIZED_CAPACITOR, SlimefunItems.ENERGIZED_CAPACITOR, INFINITY_INGOT, null,
            null, INFINITY_INGOT, SlimefunItems.ENERGIZED_CAPACITOR, SlimefunItems.ENERGIZED_CAPACITOR, INFINITY_INGOT, null,
            null, INFINITY_INGOT, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CIRCUIT, INFINITY_INGOT, null,
            null, INFINITY_INGOT, VOID_INGOT, VOID_INGOT, INFINITY_INGOT, null
            // @formatter:on
        )
    }

    val ADVANCED_ENCHANTER = buildSlimefunItem<AutoEnchanter>(5, 180) {
        id = "ADVANCED_ENCHANTER"
        material = Material.ENCHANTING_TABLE.convert()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL,
            MAGSTEEL,
            MAGSTEEL,
            MAGSTEEL_PLATE,
            SlimefunItems.AUTO_ENCHANTER,
            MAGSTEEL_PLATE,
            MACHINE_CIRCUIT,
            MACHINE_CORE,
            MACHINE_CIRCUIT
        )
    }

    val INFINITY_ENCHANTER = buildSlimefunItem<AutoEnchanter>(75, 12_000) {
        id = "INFINITY_ENCHANTER"
        material = Material.ENCHANTING_TABLE.convert()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, null, null, null, null, null,
            VOID_INGOT, null, null, null, null, VOID_INGOT,
            VOID_INGOT, VOID_INGOT, ADVANCED_ENCHANTER, ADVANCED_ENCHANTER, VOID_INGOT, VOID_INGOT,
            MACHINE_PLATE, VOID_INGOT, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CIRCUIT, VOID_INGOT, MACHINE_PLATE,
            MACHINE_PLATE, VOID_INGOT, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CORE, VOID_INGOT, MACHINE_PLATE,
            MACHINE_PLATE, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, MACHINE_PLATE
            // @formatter:on
        )
    }

    val ADVANCED_DISENCHANTER = buildSlimefunItem<AutoDisenchanter>(5, 180) {
        id = "ADVANCED_DISENCHANTER"
        material = Material.ENCHANTING_TABLE.convert()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL,
            MAGSTEEL,
            MAGSTEEL,
            MAGSTEEL_PLATE,
            SlimefunItems.AUTO_DISENCHANTER,
            MAGSTEEL_PLATE,
            MACHINE_CIRCUIT,
            MACHINE_CORE,
            MACHINE_CIRCUIT
        )
    }

    val INFINITY_DISENCHANTER = buildSlimefunItem<AutoDisenchanter>(90, 12_000) {
        id = "INFINITY_DISENCHANTER"
        material = Material.ENCHANTING_TABLE.convert()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, null, null, null, null, null,
            VOID_INGOT, null, null, null, null, VOID_INGOT,
            VOID_INGOT, VOID_INGOT, ADVANCED_DISENCHANTER, ADVANCED_DISENCHANTER, VOID_INGOT, VOID_INGOT,
            MACHINE_PLATE, VOID_INGOT, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CIRCUIT, VOID_INGOT, MACHINE_PLATE,
            MACHINE_PLATE, VOID_INGOT, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CORE, VOID_INGOT, MACHINE_PLATE,
            MACHINE_PLATE, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, MACHINE_PLATE
            // @formatter:on
        )
    }

    val ADVANCED_CHARGER = buildSlimefunItem<ChargingBench>(30, 180) {
        id = "ADVANCED_CHARGER"
        material = Material.HONEYCOMB_BLOCK.convert()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL_PLATE, MACHINE_CIRCUIT, MAGSTEEL_PLATE,
            MACHINE_CIRCUIT, SlimefunItems.CHARGING_BENCH, MACHINE_CIRCUIT,
            MAGSTEEL_PLATE, MACHINE_CORE, MAGSTEEL_PLATE,
        )
    }

    val INFINITY_CHARGER = buildSlimefunItem<ChargingBench>(6000, 60_000) {
        id = "INFINITY_CHARGER"
        material = Material.SEA_LANTERN.convert()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, null, null, null, null, null,
            VOID_INGOT, MACHINE_CIRCUIT, MACHINE_CIRCUIT, MACHINE_CIRCUIT, MACHINE_CIRCUIT, VOID_INGOT,
            VOID_INGOT, MACHINE_CIRCUIT, ADVANCED_CHARGER, ADVANCED_CHARGER, MACHINE_CIRCUIT, VOID_INGOT,
            VOID_INGOT, MACHINE_CIRCUIT, ADVANCED_CHARGER, ADVANCED_CHARGER, MACHINE_CIRCUIT, VOID_INGOT,
            VOID_INGOT, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CIRCUIT, VOID_INGOT,
            INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT
            // @formatter:on
        )
    }

    val ADVANCED_GEO_MINER = buildSlimefunItem<GeoMiner>(4, 120) {
        id = "ADVANCED_GEO_MINER"
        material = HeadTexture.GEO_MINER.convert()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL_PLATE,
            MAGSTEEL_PLATE,
            MAGSTEEL_PLATE,
            SlimefunItems.COBALT_PICKAXE,
            SlimefunItems.GEO_MINER,
            SlimefunItems.COBALT_PICKAXE,
            MACHINE_CIRCUIT,
            MACHINE_CORE,
            MACHINE_CIRCUIT
        )
    }

    val ADVANCED_SMELTERY = buildSlimefunItem<Smeltery>(24, 240) {
        id = "ADVANCED_SMELTERY"
        material = Material.FURNACE.convert()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            SlimefunItems.ELECTRIC_SMELTERY_2,
            SlimefunItems.ELECTRIC_SMELTERY_2,
            SlimefunItems.ELECTRIC_SMELTERY_2,
            SlimefunItems.ELECTRIC_SMELTERY_2,
            SlimefunItems.ELECTRIC_SMELTERY_2,
            SlimefunItems.ELECTRIC_SMELTERY_2,
            MACHINE_CIRCUIT,
            MACHINE_CORE,
            MACHINE_CIRCUIT
        )
    }

    val VOID_SMELTERY = buildSlimefunItem<Smeltery>(72, 1200) {
        id = "VOID_SMELTERY"
        material = Material.FURNACE.convert()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            VOID_BLOCK,
            MACHINE_CORE,
            VOID_BLOCK,
            MACHINE_CIRCUIT,
            ADVANCED_SMELTERY,
            MACHINE_CIRCUIT,
            VOID_BLOCK,
            ADVANCED_SMELTERY,
            VOID_BLOCK
        )
    }

    val ADVANCED_NETHER_STAR_REACTOR = buildSlimefunItem<NetherStarReactor>(1800, 90000) {
        id = "ADVANCED_NETHER_STAR_REACTOR"
        material = HeadTexture.NETHER_STAR_REACTOR.convert()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_GLASS,
            MACHINE_CIRCUIT, SlimefunItems.NETHER_STAR_REACTOR, MACHINE_CIRCUIT,
            SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_OBSIDIAN,
            // @formatter:on
        )
    }
    //</editor-fold>

    //<editor-fold desc="Food" defaultstate="collapsed">
    val COSMIC_MEATBALLS = buildSlimefunItem<CosmicMeatballs> {
        id = "COSMIC_MEATBALLS"
        material = Material.COOKED_BEEF.convert()
        itemGroup = IEItemGroups.FOOD
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, null, null, null, null, null,
            null, Material.CHICKEN.toItem(), Material.BEEF.toItem(), Material.BEEF.toItem(), Material.RABBIT.toItem(), null,
            null, Material.CHICKEN.toItem(), VOID_DUST, Material.COD.toItem(), Material.RABBIT.toItem(), null,
            null, Material.MUTTON.toItem(), Material.COD.toItem(), null, Material.SALMON.toItem(), null,
            null, Material.MUTTON.toItem(), Material.PORKCHOP.toItem(), Material.PORKCHOP.toItem(), Material.SALMON.toItem(), null,
            null, null, null, null, null, null,
            // @formatter:on
        )
    }

    val ULTIMATE_STEW = buildSlimefunItem<UltimateStew> {
        id = "ULTIMATE_STEW"
        material = Material.SUSPICIOUS_STEW.convert()
        itemGroup = IEItemGroups.FOOD
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, null, null, null, null, null,
            null, Material.WHEAT.toItem(), Material.POTATO.toItem(), Material.POTATO.toItem(), Material.CACTUS.toItem(), null,
            null, Material.WHEAT.toItem(), VOID_DUST, Material.NETHER_WART.toItem(), Material.CACTUS.toItem(), null,
            null, Material.CARROT.toItem(), Material.NETHER_WART.toItem(), null, Material.BROWN_MUSHROOM.toItem(), null,
            null, Material.CARROT.toItem(), Material.RED_MUSHROOM.toItem(), Material.RED_MUSHROOM.toItem(), Material.BROWN_MUSHROOM.toItem(), null,
            null, null, null, null, null, null,
            // @formatter:on
        )
    }
    //</editor-fold>

    //<editor-fold desc="Tools" defaultstate="collapsed">
    val ENDER_FLAME = buildSlimefunItem<SimpleMaterial> {
        id = "ENDER_FLAME"
        material = Material.ENCHANTED_BOOK.convert()
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.MAGIC_WORKBENCH
        recipe = Material.BOOK.toItem() surroundedBy ENDER_ESSENCE

        postCreate = {
            it.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 10)
        }
    }

    val VEIN_MINER_RUNE = buildSlimefunItem<VeinMinerRune> {
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
            // @formatter:off
            Material.STICK.toItem(), Material.STRING.toItem(), Material.STICK.toItem(),
            Material.STICK.toItem(), Material.STRING.toItem(), Material.STICK.toItem(),
            MAGSTEEL, MAGSTEEL, MAGSTEEL,
            // @formatter:on
        )
    }

    val STRAINER_1 = buildSlimefunItem<Strainer>(30) {
        id = "STRAINER_1"
        material = Material.FISHING_ROD.convert()
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            Material.STICK.toItem(), Material.STRING.toItem(), Material.STICK.toItem(),
            Material.STRING.toItem(), Material.STICK.toItem(), Material.STRING.toItem(),
            Material.STICK.toItem(), Material.STRING.toItem(), Material.STICK.toItem(),
            // @formatter:on
        )
    }

    val STRAINER_2 = buildSlimefunItem<Strainer>(60) {
        id = "STRAINER_2"
        material = Material.FISHING_ROD.convert()
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            MAGSTEEL, Material.STRING.toItem(), MAGSTEEL,
            Material.STRING.toItem(), STRAINER_1, Material.STRING.toItem(),
            MAGSTEEL, Material.STRING.toItem(), MAGSTEEL,
            // @formatter:on
        )
    }

    val STRAINER_3 = buildSlimefunItem<Strainer>(90) {
        id = "STRAINER_3"
        material = Material.FISHING_ROD.convert()
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            SlimefunItems.REINFORCED_ALLOY_INGOT, Material.STRING.toItem(), SlimefunItems.REINFORCED_ALLOY_INGOT,
            Material.STRING.toItem(), STRAINER_2, Material.STRING.toItem(),
            SlimefunItems.REINFORCED_ALLOY_INGOT, Material.STRING.toItem(), SlimefunItems.REINFORCED_ALLOY_INGOT,
            // @formatter:on
        )
    }

    val INFINITY_PICKAXE = buildSlimefunItem<InfinityTool> {
        id = "INFINITY_PICKAXE"
        material = Material.NETHERITE_PICKAXE.convert()
        itemGroup = IEItemGroups.TOOLS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, null,
            null, null, null, INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT,
            null, null, null, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT,
            null, null, VOID_INGOT, null, null, INFINITY_INGOT,
            null, VOID_INGOT, null, null, null, VOID_INGOT,
            VOID_INGOT, null, null, null, null, null,
            // @formatter:on
        )
        postCreate = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_AXE = buildSlimefunItem<InfinityTool> {
        id = "INFINITY_AXE"
        material = Material.NETHERITE_AXE.convert()
        itemGroup = IEItemGroups.TOOLS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT, null, null,
            VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT, null,
            null, INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT,
            null, null, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT,
            null, VOID_INGOT, null, INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT,
            VOID_INGOT, null, null, null, VOID_INGOT, null
            // @formatter:on
        )
        postCreate = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_SHOVEL = buildSlimefunItem<InfinityTool> {
        id = "INFINITY_SHOVEL"
        material = Material.NETHERITE_SHOVEL.convert()
        itemGroup = IEItemGroups.TOOLS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, null, null, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT,
            null, null, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT,
            null, null, INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT,
            null, null, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT, null,
            null, VOID_INGOT, null, null, null, null,
            VOID_INGOT, null, null, null, null, null
            // @formatter:on
        )
        postCreate = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_HOE = buildSlimefunItem<InfinityTool> {
        id = "INFINITY_HOE"
        material = Material.NETHERITE_HOE.convert()
        itemGroup = IEItemGroups.TOOLS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT,
            null, null, INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT,
            null, null, null, VOID_INGOT, null, null,
            null, null, VOID_INGOT, null, null, null,
            null, VOID_INGOT, null, null, null, null,
            VOID_INGOT, null, null, null, null, null,
            // @formatter:on
        )
        postCreate = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_MATRIX = buildSlimefunItem<InfinityMatrix> {
        id = "INFINITY_MATRIX"
        material = Material.NETHER_STAR.convert()
        itemGroup = IEItemGroups.TOOLS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            INFINITY_INGOT, null, INFINITY_INGOT, INFINITY_INGOT, null, INFINITY_INGOT,
            INFINITY_INGOT, VOID_INGOT, VOID_INGOT, VOID_INGOT, VOID_INGOT, INFINITY_INGOT,
            VOID_INGOT, VOID_INGOT, Material.ELYTRA.toItem(), Material.ELYTRA.toItem(), VOID_INGOT, VOID_INGOT,
            VOID_INGOT, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT, VOID_INGOT,
            INFINITY_INGOT, VOID_INGOT, VOID_INGOT, VOID_INGOT, VOID_INGOT, INFINITY_INGOT,
            INFINITY_INGOT, null, INFINITY_INGOT, INFINITY_INGOT, null, INFINITY_INGOT
            // @formatter:on
        )
    }
    //</editor-fold>

    //<editor-fold desc="Gears" defaultstate="collapsed">
    val INFINITY_HELMET = buildSlimefunItem<InfinityArmor>(
        arrayOf(
            buildHiddenPotionEffect("night_vision", 600, 0),
            buildHiddenPotionEffect("conduit_power", 600, 0),
        ),
        arrayOf(ProtectionType.FLYING_INTO_WALL),
    ) {
        id = "INFINITY_HELMET"
        material = Material.NETHERITE_HELMET.convert()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, null,
            INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT,
            INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT,
            null, INFINITY_INGOT, null, null, INFINITY_INGOT, null,
            null, null, null, null, null, null,
            null, null, null, null, null, null,
            // @formatter:on
        )
        postCreate = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINIYT_CHESTPLATE = buildSlimefunItem<InfinityArmor>(
        arrayOf(
            buildHiddenPotionEffect("night_vision", 600, 0),
            buildHiddenPotionEffect("conduit_power", 600, 0),
        ),
        arrayOf(ProtectionType.BEES),
    ) {
        id = "INFINITY_CHESTPLATE"
        material = Material.NETHERITE_CHESTPLATE.convert()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, INFINITY_INGOT, null, null, INFINITY_INGOT, null,
            INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT,
            VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT,
            VOID_INGOT, INFINITY_INGOT, VOID_INGOT, VOID_INGOT, INFINITY_INGOT, VOID_INGOT,
            null, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, null,
            null, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, null,
            // @formatter:on
        )
        postCreate = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_LEGGINGS = buildSlimefunItem<InfinityArmor>(
        arrayOf(
            buildHiddenPotionEffect("haste", 600, 2),
            buildHiddenPotionEffect("regeneration", 600, 0),
            buildHiddenPotionEffect("saturation", 600, 0),
        ),
        arrayOf(ProtectionType.RADIATION),
    ) {
        id = "INFINITY_LEGGINGS"
        material = Material.NETHERITE_LEGGINGS.convert()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, null,
            INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT,
            VOID_INGOT, INFINITY_INGOT, null, null, INFINITY_INGOT, VOID_INGOT,
            VOID_INGOT, INFINITY_INGOT, null, null, INFINITY_INGOT, VOID_INGOT,
            VOID_INGOT, INFINITY_INGOT, null, null, INFINITY_INGOT, VOID_INGOT,
            null, INFINITY_INGOT, null, null, INFINITY_INGOT, null,
        )
        postCreate = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_BOOTS = buildSlimefunItem<InfinityBoots>(
        arrayOf(
            buildHiddenPotionEffect("speed", 600, 2),
            buildHiddenPotionEffect("dolphins_grace", 600, 0),
        ),
        arrayOf<ProtectionType>()
    ) {
        id = "INFINITY_BOOTS"
        material = Material.NETHERITE_BOOTS.convert()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, null, null, null, null, null,
            INFINITY_INGOT, INFINITY_INGOT, null, null, INFINITY_INGOT, INFINITY_INGOT,
            INFINITY_INGOT, INFINITY_INGOT, null, null, INFINITY_INGOT, INFINITY_INGOT,
            VOID_INGOT, VOID_INGOT, null, null, VOID_INGOT, VOID_INGOT,
            INFINITY_INGOT, INFINITY_INGOT, null, null, INFINITY_INGOT, INFINITY_INGOT,
            INFINITY_INGOT, INFINITY_INGOT, null, null, INFINITY_INGOT, INFINITY_INGOT,
        )
        postCreate = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_SHIELD = buildSlimefunItem<InfinityTool> {
        id = "INFINITY_SHIELD"
        material = Material.SHIELD.convert()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            INFINITY_INGOT, INFINITY_INGOT, null, null, INFINITY_INGOT, INFINITY_INGOT,
            INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT,
            INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT,
            INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT,
            null, INFINITY_INGOT, VOID_INGOT, VOID_INGOT, INFINITY_INGOT, null,
            null, INFINITY_INGOT, VOID_INGOT, VOID_INGOT, INFINITY_INGOT, null,
            // @formatter:on
        )
        postCreate = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_SWORD = buildSlimefunItem<InfinityTool> {
        id = "INFINITY_SWORD"
        material = Material.NETHERITE_SWORD.convert()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, null, null, null, INFINITY_INGOT, INFINITY_INGOT,
            null, null, null, INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT,
            null, null, INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT, null,
            INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT, null, null,
            null, VOID_INGOT, INFINITY_INGOT, null, null, null,
            VOID_INGOT, null, INFINITY_INGOT, null, null, null
            // @formatter:on
        )
        postCreate = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_BOW = buildSlimefunItem<InfinityBow> {
        id = "INFINITY_BOW"
        material = Material.BOW.convert()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT, null, null,
            INFINITY_INGOT, null, INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT, null,
            VOID_INGOT, null, null, ENDER_FLAME, INFINITY_INGOT, VOID_INGOT,
            null, VOID_INGOT, null, null, INFINITY_INGOT, INFINITY_INGOT,
            null, null, VOID_INGOT, null, null, INFINITY_INGOT,
            null, null, null, VOID_INGOT, INFINITY_INGOT, null
            // @formatter:on
        )
        postCreate = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_CROSSBOW = buildSlimefunItem<InfinityBow> {
        id = "INFINITY_CROSSBOW"
        material = Material.CROSSBOW.convert()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, null,
            VOID_INGOT, INFINITY_INGOT, VOID_INGOT, null, null, INFINITY_INGOT,
            INFINITY_INGOT, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT, null, VOID_INGOT,
            INFINITY_INGOT, null, INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT, null,
            INFINITY_INGOT, null, null, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT,
            null, INFINITY_INGOT, VOID_INGOT, null, INFINITY_INGOT, INFINITY_INGOT,
            // @formatter:on
        )
        postCreate = {
            applyInfinityGearEnchantment(it)
        }
    }
    //</editor-fold>

    //<editor-fold desc="Machines" defaultstate="collapsed">
    val COBBLESTONE_GENERATOR = buildSlimefunItem<MaterialGenerator>(Material.COBBLESTONE, 1, 24) {
        id = "COBBLESTONE_GENERATOR"
        material = Material.SMOOTH_STONE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            MAGSTEEL, Material.DIAMOND_PICKAXE.toItem(), MAGSTEEL,
            Material.WATER_BUCKET.toItem(), COMPRESSED_COBBLESTONE_2, Material.LAVA_BUCKET.toItem(),
            MAGSTEEL, MACHINE_CIRCUIT, MAGSTEEL,
            // @formatter:on
        )
    }

    val COBBLESTONE_GENERATOR_2 = buildSlimefunItem<MaterialGenerator>(Material.COBBLESTONE, 4, 120) {
        id = "COBBLESTONE_GENERATOR_2"
        material = Material.SMOOTH_STONE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            MAGSTEEL, COBBLESTONE_GENERATOR, MAGSTEEL,
            Material.WATER_BUCKET.toItem(), COMPRESSED_COBBLESTONE_3, Material.LAVA_BUCKET.toItem(),
            MACHINE_CIRCUIT, COBBLESTONE_GENERATOR, MACHINE_CIRCUIT,
            // @formatter:on
        )
    }

    val COBBLESTONE_GENERATOR_3 = buildSlimefunItem<MaterialGenerator>(Material.COBBLESTONE, 16, 360) {
        id = "COBBLESTONE_GENERATOR_3"
        material = Material.SMOOTH_STONE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            VOID_BLOCK, COBBLESTONE_GENERATOR_2, MACHINE_PLATE,
            Material.WATER_BUCKET.toItem(), COMPRESSED_COBBLESTONE_4, Material.LAVA_BUCKET.toItem(),
            MACHINE_CIRCUIT, COBBLESTONE_GENERATOR_2, VOID_BLOCK,
            // @formatter:on
        )
    }

    val COBBLESTONE_GENERATOR_4 = buildSlimefunItem<MaterialGenerator>(Material.COBBLESTONE, 64, 800) {
        id = "COBBLESTONE_GENERATOR_4"
        material = Material.SMOOTH_STONE.convert()
        itemGroup = IEItemGroups.MACHINES
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

    val VIRTUAL_FARM = buildSlimefunItem<VirtualFarm>(18, 300) {
        id = "VIRTUAL_FARM"
        material = Material.GRASS_BLOCK.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            Material.GLASS.toItem(), Material.GLASS.toItem(), Material.GLASS.toItem(),
            MAGSTEEL, Material.DIAMOND_HOE.toItem(), MAGSTEEL,
            MACHINE_CIRCUIT, Material.GRASS_BLOCK.toItem(), MACHINE_CIRCUIT,
            // @formatter:on
        )
    }

    val VIRTUAL_FARM_2 = buildSlimefunItem<VirtualFarm>(90, 60) {
        id = "VIRTUAL_FARM_2"
        material = Material.CRIMSON_NYLIUM.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            SlimefunItems.HARDENED_GLASS, SlimefunItems.HARDENED_GLASS, SlimefunItems.HARDENED_GLASS,
            MAGNONIUM, VIRTUAL_FARM, MAGNONIUM,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
            // @formatter:on
        )
    }

    val VIRTUAL_FARM_3 = buildSlimefunItem<VirtualFarm>(270, 30) {
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

    val VIRTUAL_FARM_4 = buildSlimefunItem<VirtualFarm>(1000, 10) {
        id = "VIRTUAL_FARM_4"
        material = Material.END_STONE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            Material.GLASS.toItem(), Material.GLASS.toItem(), Material.GLASS.toItem(), Material.GLASS.toItem(), Material.GLASS.toItem(), Material.GLASS.toItem(),
            Material.GLASS.toItem(), null, null, null, null, Material.GLASS.toItem(),
            Material.GLASS.toItem(), null, null, null, null, Material.GLASS.toItem(),
            Material.GLASS.toItem(), Material.GRASS_BLOCK.toItem(), Material.GRASS_BLOCK.toItem(), Material.GRASS_BLOCK.toItem(), Material.GRASS_BLOCK.toItem(), Material.GLASS.toItem(),
            MACHINE_PLATE, SlimefunItems.CROP_GROWTH_ACCELERATOR_2, VIRTUAL_FARM_3, VIRTUAL_FARM_3, SlimefunItems.CROP_GROWTH_ACCELERATOR_2, MACHINE_PLATE,
            MACHINE_PLATE, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CIRCUIT, MACHINE_PLATE,
            // @formatter:on
        )
    }

    val TREE_GROWER = buildSlimefunItem<TreeGrower>(36, 600) {
        id = "TREE_GROWER"
        material = Material.STRIPPED_OAK_WOOD.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            Material.GLASS.toItem(), Material.GLASS.toItem(), Material.GLASS.toItem(),
            MAGSTEEL, Material.PODZOL.toItem(), MAGSTEEL,
            MACHINE_CIRCUIT, VIRTUAL_FARM, MACHINE_CIRCUIT,
            // @formatter:on
        )
    }

    val TREE_GROWER_2 = buildSlimefunItem<TreeGrower>(180, 120) {
        id = "TREE_GROWER_2"
        material = Material.STRIPPED_ACACIA_WOOD.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            SlimefunItems.HARDENED_GLASS, SlimefunItems.HARDENED_GLASS, SlimefunItems.HARDENED_GLASS,
            MAGNONIUM, TREE_GROWER, MAGNONIUM,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
        )
    }

    val TREE_GROWER_3 = buildSlimefunItem<TreeGrower>(540, 60) {
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

    val TREE_GROWER_4 = buildSlimefunItem<TreeGrower>(2000, 12) {
        id = "TREE_GROWER_4"
        material = Material.STRIPPED_WARPED_HYPHAE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            Material.GLASS.toItem(), Material.GLASS.toItem(), Material.GLASS.toItem(), Material.GLASS.toItem(), Material.GLASS.toItem(), Material.GLASS.toItem(),
            Material.GLASS.toItem(), SlimefunItems.TREE_GROWTH_ACCELERATOR, null, null, SlimefunItems.TREE_GROWTH_ACCELERATOR, Material.GLASS.toItem(),
            Material.GLASS.toItem(), TREE_GROWER_3, null, null, TREE_GROWER_3, Material.GLASS.toItem(),
            Material.GLASS.toItem(), SlimefunItems.TREE_GROWTH_ACCELERATOR, null, null, SlimefunItems.TREE_GROWTH_ACCELERATOR, Material.GLASS.toItem(),
            MACHINE_PLATE, Material.PODZOL.toItem(), Material.PODZOL.toItem(), Material.PODZOL.toItem(), Material.PODZOL.toItem(), MACHINE_PLATE,
            MACHINE_PLATE, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CIRCUIT, MACHINE_PLATE,
            // @formatter:on
        )
    }

    val VOID_HARVESTER = buildSlimefunItem<VoidHarvester>(1, 120) {
        id = "VOID_HARVESTER"
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
            MAGSTEEL_PLATE, VOID_HARVESTER, MAGSTEEL_PLATE,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
        )
    }

    val VOID_HARVESTER_3 = buildSlimefunItem<VoidHarvester>(64, 12000) {
        id = "VOID_HARVESTER_3"
        material = Material.CRYING_OBSIDIAN.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE,
            MAGNONIUM, VOID_INGOT, VOID_INGOT, VOID_INGOT, VOID_INGOT, MAGNONIUM,
            MAGNONIUM, VOID_INGOT, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CIRCUIT, VOID_INGOT, MAGNONIUM,
            MAGNONIUM, VOID_INGOT, VOID_HARVESTER_2, VOID_HARVESTER_2, VOID_INGOT, MAGNONIUM,
            MAGNONIUM, VOID_INGOT, VOID_INGOT, VOID_INGOT, VOID_INGOT, MAGNONIUM,
            MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE,
            // @formatter:on
        )
    }

    val STONEWORKS_FACTORY = buildSlimefunItem<StoneworksFactory>(1, 200) {
        id = "STONEWORKS_FACTORY"
        material = Material.BLAST_FURNACE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL_PLATE, COBBLESTONE_GENERATOR, MAGSTEEL_PLATE,
            SlimefunItems.ELECTRIC_FURNACE, MACHINE_CIRCUIT, SlimefunItems.ELECTRIC_ORE_GRINDER,
            MAGSTEEL_PLATE, SlimefunItems.ELECTRIC_PRESS, MAGSTEEL_PLATE,
        )
    }

    val STONEWORKS_FACTORY_2 = buildSlimefunItem<StoneworksFactory>(4, 600) {
        id = "STONEWORKS_FACTORY_2"
        material = Material.BLAST_FURNACE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL_PLATE, COBBLESTONE_GENERATOR_2, MAGSTEEL_PLATE,
            SlimefunItems.ELECTRIC_FURNACE_2, STONEWORKS_FACTORY, SlimefunItems.ELECTRIC_ORE_GRINDER_2,
            MACHINE_CIRCUIT, SlimefunItems.ELECTRIC_PRESS_2, MACHINE_CIRCUIT,
        )
    }

    val STONEWORKS_FACTORY_3 = buildSlimefunItem<StoneworksFactory>(16, 1800) {
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

    val STONEWORKS_FACTORY_4 = buildSlimefunItem<StoneworksFactory>(64, 5400) {
        id = "STONEWORKS_FACTORY_4"
        material = Material.BLAST_FURNACE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            INFINITY_INGOT, VOID_INGOT, null, null, VOID_INGOT, INFINITY_INGOT,
            null, SlimefunItems.ELECTRIC_FURNACE_3, STONEWORKS_FACTORY_3, STONEWORKS_FACTORY_3, SlimefunItems.ELECTRIC_FURNACE_3, null,
            SlimefunItems.ELECTRIC_ORE_GRINDER_3, VOID_INGOT, COBBLESTONE_GENERATOR_4, COBBLESTONE_GENERATOR_4, VOID_INGOT, SlimefunItems.ELECTRIC_ORE_GRINDER_3,
            SlimefunItems.ELECTRIC_PRESS_2, VOID_INGOT, MACHINE_CIRCUIT, MACHINE_CIRCUIT, VOID_INGOT, SlimefunItems.ELECTRIC_PRESS_2,
            MACHINE_PLATE, MACHINE_CIRCUIT, MACHINE_CIRCUIT, MACHINE_CIRCUIT, MACHINE_CIRCUIT, MACHINE_PLATE,
            INFINITY_INGOT, VOID_INGOT, null, null, VOID_INGOT, INFINITY_INGOT,
            // @formatter:on
        )
    }

    val SINGULARITY_CONSTRUCTOR = buildSlimefunItem<SingularityConstructor>(1, 120) {
        id = "SINGULARITY_CONSTRUCTOR"
        material = Material.QUARTZ_BRICKS.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL, MAGSTEEL, MAGSTEEL,
            MACHINE_PLATE, SlimefunItems.CARBON_PRESS_3, MACHINE_PLATE,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
        )
    }

    val INFINITY_SINGULARITY_CONSTRUCTOR = buildSlimefunItem<SingularityConstructor>(64, 1200) {
        id = "INFINITY_SINGULARITY_CONSTRUCTOR"
        material = Material.CHISELED_QUARTZ_BLOCK.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, null,
            null, VOID_INGOT, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CIRCUIT, VOID_INGOT, null,
            null, VOID_INGOT, SINGULARITY_CONSTRUCTOR, SINGULARITY_CONSTRUCTOR, VOID_INGOT, null,
            null, VOID_INGOT, SINGULARITY_CONSTRUCTOR, SINGULARITY_CONSTRUCTOR, VOID_INGOT, null,
            null, INFINITY_INGOT, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CORE, INFINITY_INGOT, null,
            INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT,
            // @formatter:on
        )
    }

    val RESOURCE_SYNTHESIZER = buildSlimefunItem<ResourceSynthesizer>(1_000_000) {
        id = "RESOURCE_SYNTHESIZER"
        material = Material.LODESTONE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            ADAMANTITE, ADAMANTITE, ADAMANTITE,
            MACHINE_PLATE, SlimefunItems.REINFORCED_FURNACE, MACHINE_PLATE,
            MACHINE_PLATE, MACHINE_CORE, MACHINE_PLATE,
            // @formatter:on
        )
    }

    val OBSIDIAN_GENERATOR = buildSlimefunItem<MaterialGenerator>(Material.OBSIDIAN, 1, 240) {
        id = "OBSIDIAN_GENERATOR"
        material = Material.SMOOTH_STONE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            SlimefunItems.FLUID_PUMP, SlimefunItems.PROGRAMMABLE_ANDROID_MINER, SlimefunItems.FLUID_PUMP,
            Material.DISPENSER.toItem(), VOID_INGOT, Material.DISPENSER.toItem(),
            MACHINE_CIRCUIT, COBBLESTONE_GENERATOR_2, MACHINE_CIRCUIT,
        )
    }

    val EXTREME_FREEZER = buildSlimefunItem<ExtremeFreezer>(90) {
        id = "EXTREME_FREEZER"
        material = Material.LIGHT_BLUE_CONCRETE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            SlimefunItems.FREEZER_2, SlimefunItems.FREEZER_2, SlimefunItems.FREEZER_2,
            Material.WATER_BUCKET.toItem(), SlimefunItems.FLUID_PUMP, Material.WATER_BUCKET.toItem(),
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
        )
    }

    val DUST_EXTRACTOR = buildSlimefunItem<DustExtractor>(200, 4, 2) {
        id = "DUST_EXTRACTOR"
        material = Material.FURNACE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            SlimefunItems.ELECTRIC_ORE_GRINDER_2, SlimefunItems.ELECTRIC_GOLD_PAN_3, SlimefunItems.ELECTRIC_DUST_WASHER_3,
            SlimefunItems.ELECTRIC_ORE_GRINDER_2, SlimefunItems.ELECTRIC_GOLD_PAN_3, SlimefunItems.ELECTRIC_DUST_WASHER_3,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
            // @formatter:on
        )
    }

    val DUST_EXTRACTOR_2 = buildSlimefunItem<DustExtractor>(800, 8, 4) {
        id = "DUST_EXTRACTOR_2"
        material = Material.FURNACE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            MAGSTEEL_PLATE, DUST_EXTRACTOR, MAGSTEEL_PLATE,
            MAGSTEEL_PLATE, DUST_EXTRACTOR, MAGSTEEL_PLATE,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
            // @formatter:on
        )
    }

    val DUST_EXTRACTOR_3 = buildSlimefunItem<DustExtractor>(2400, 32, 32) {
        id = "DUST_EXTRACTOR_3"
        material = Material.FURNACE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            VOID_BLOCK, DUST_EXTRACTOR_2, VOID_BLOCK,
            MACHINE_PLATE, DUST_EXTRACTOR_2, MACHINE_PLATE,
            MACHINE_PLATE, MACHINE_CORE, MACHINE_PLATE,
            // @formatter:on
        )
    }

    val DUST_EXTRACTOR_4 = buildSlimefunItem<DustExtractor>(7200, 64, 64) {
        id = "DUST_EXTRACTOR_4"
        material = Material.FURNACE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            VOID_BLOCK, null, null, null, null, VOID_BLOCK,
            VOID_INGOT, INFINITY_INGOT, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CIRCUIT, INFINITY_INGOT, VOID_INGOT,
            VOID_INGOT, INFINITY_INGOT, null, null, INFINITY_INGOT, VOID_INGOT,
            VOID_INGOT, INFINITY_INGOT, DUST_EXTRACTOR_3, DUST_EXTRACTOR_3, INFINITY_INGOT, VOID_INGOT,
            VOID_INGOT, INFINITY_INGOT, null, null, INFINITY_INGOT, VOID_INGOT,
            VOID_BLOCK, null, null, null, null, VOID_BLOCK,
            // @formatter:on
        )
    }

    val INGOT_FORMER = buildSlimefunItem<IngotFormer>(200, 8, 4) {
        id = "INGOT_FORMER"
        material = Material.BLAST_FURNACE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            SlimefunItems.ELECTRIC_INGOT_FACTORY_2, SlimefunItems.ELECTRIC_INGOT_FACTORY_2, SlimefunItems.ELECTRIC_INGOT_FACTORY_2,
            SlimefunItems.ELECTRIC_INGOT_FACTORY_2, SlimefunItems.ELECTRIC_INGOT_FACTORY_2, SlimefunItems.ELECTRIC_INGOT_FACTORY_2,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
            // @formatter:on
        )
    }

    val INGOT_FORMER_2 = buildSlimefunItem<IngotFormer>(800, 16, 8) {
        id = "INGOT_FORMER_2"
        material = Material.BLAST_FURNACE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            MAGSTEEL_PLATE, INGOT_FORMER, MAGSTEEL_PLATE,
            MAGSTEEL_PLATE, INGOT_FORMER, MAGSTEEL_PLATE,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
            // @formatter:on
        )
    }

    val INGOT_FORMER_3 = buildSlimefunItem<IngotFormer>(2400, 32, 16) {
        id = "INGOT_FORMER_3"
        material = Material.BLAST_FURNACE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            VOID_BLOCK, INGOT_FORMER_2, VOID_BLOCK,
            MACHINE_PLATE, INGOT_FORMER_2, MACHINE_PLATE,
            MACHINE_PLATE, MACHINE_CORE, MACHINE_PLATE,
            // @formatter:on
        )
    }

    val INGOT_FORMER_4 = buildSlimefunItem<IngotFormer>(7200, 64, 32) {
        id = "INGOT_FORMER_4"
        material = Material.BLAST_FURNACE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            VOID_BLOCK, null, null, null, null, VOID_BLOCK,
            VOID_INGOT, INFINITY_INGOT, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CIRCUIT, INFINITY_INGOT, VOID_INGOT,
            VOID_INGOT, INFINITY_INGOT, null, null, INFINITY_INGOT, VOID_INGOT,
            VOID_INGOT, INFINITY_INGOT, INGOT_FORMER_3, INGOT_FORMER_3, INFINITY_INGOT, VOID_INGOT,
            VOID_INGOT, INFINITY_INGOT, null, null, INFINITY_INGOT, VOID_INGOT,
            VOID_BLOCK, null, null, null, null, VOID_BLOCK,
            // @formatter:on
        )
    }

    val URANIUM_EXTRACTOR = buildSlimefunItem<UraniumExtractor>(200, 4, 1) {
        id = "URANIUM_EXTRACTOR"
        material = Material.LIME_CONCRETE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            SlimefunItems.ELECTRIC_ORE_GRINDER_2, SlimefunItems.ELECTRIC_ORE_GRINDER_2, SlimefunItems.ELECTRIC_ORE_GRINDER_2,
            SlimefunItems.ELECTRIC_GOLD_PAN_3, SlimefunItems.ELECTRIC_DUST_WASHER_3, SlimefunItems.ENHANCED_AUTO_CRAFTER,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
            // @formatter:on
        )
    }

    val COBBLE_PRESS = buildSlimefunItem<CobblePress>(200) {
        id = "COBBLE_PRESS"
        material = Material.SMOOTH_STONE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            MACHINE_PLATE, COMPRESSED_COBBLESTONE_3, MACHINE_PLATE,
            SlimefunItems.ELECTRIC_PRESS_2, SlimefunItems.ELECTRIC_PRESS_2, SlimefunItems.ELECTRIC_PRESS_2,
            MACHINE_PLATE, COMPRESSED_COBBLESTONE_3, MACHINE_PLATE,
            // @formatter:on
        )
    }

    val DECOMPRESSOR = buildSlimefunItem<Decompressor>(60) {
        id = "DECOMPRESSOR"
        material = Material.TARGET.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            MAGSTEEL_PLATE, MAGSTEEL_PLATE, MAGSTEEL_PLATE,
            Material.STICKY_PISTON.toItem(), SlimefunItems.ELECTRIC_PRESS_2, Material.STICKY_PISTON.toItem(),
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
            // @formatter:on
        )
    }

    val QUARRY = buildSlimefunItem<Quarry>(300, 1, 0.15) {
        id = "QUARRY"
        material = Material.CHISELED_SANDSTONE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL_PLATE, SlimefunItems.CARBONADO_EDGED_CAPACITOR, MAGSTEEL_PLATE,
            Material.IRON_PICKAXE.toItem(), SlimefunItems.GEO_MINER, Material.IRON_PICKAXE.toItem(),
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
        )
    }

    val QUARRY_2 = buildSlimefunItem<Quarry>(900, 2, 0.25) {
        id = "QUARRY_2"
        material = Material.CHISELED_RED_SANDSTONE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MACHINE_PLATE, SlimefunItems.ENERGIZED_CAPACITOR, MACHINE_PLATE,
            Material.DIAMOND_PICKAXE.toItem(), QUARRY, Material.DIAMOND_PICKAXE.toItem(),
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
        )
    }

    val QUARRY_3 = buildSlimefunItem<Quarry>(3600, 4, 0.5) {
        id = "QUARRY_3"
        material = Material.CHISELED_NETHER_BRICKS.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            VOID_INGOT, VOID_CAPACITOR, VOID_INGOT,
            Material.NETHERITE_PICKAXE.toItem(), QUARRY_2, Material.NETHERITE_PICKAXE.toItem(),
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
        )
    }

    val QUARRY_4 = buildSlimefunItem<Quarry>(36000, 64, 1.0) {
        id = "QUARRY_4"
        material = Material.CHISELED_POLISHED_BLACKSTONE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, null,
            MACHINE_PLATE, INFINITY_PICKAXE, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CIRCUIT, INFINITY_PICKAXE, MACHINE_PLATE,
            MACHINE_PLATE, QUARRY_3, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CORE, QUARRY_3, MACHINE_PLATE,
            VOID_INGOT, null, INFINITY_INGOT, INFINITY_INGOT, null, VOID_INGOT,
            VOID_INGOT, null, INFINITY_INGOT, INFINITY_INGOT, null, VOID_INGOT,
            VOID_INGOT, null, INFINITY_INGOT, INFINITY_INGOT, null, VOID_INGOT,
            // @formatter:on
        )
    }

    val GEO_QUARRY = buildSlimefunItem<GeoQuarry>(450, 400, 1) {
        id = "GEO_QUARRY"
        material = Material.QUARTZ_BRICKS.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MACHINE_PLATE, VOID_INGOT, MACHINE_PLATE,
            VOID_INGOT, ADVANCED_GEO_MINER, VOID_INGOT,
            MACHINE_PLATE, VOID_INGOT, MACHINE_PLATE,
        )
    }

    val GEO_QUARRY_2 = buildSlimefunItem<GeoQuarry>(90_000, 120, 4) {
        id = "GEO_QUARRY_2"
        material = Material.QUARTZ_BRICKS.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            INFINITY_INGOT, null, null, null, null, INFINITY_INGOT,
            null, VOID_INGOT, VOID_INGOT, VOID_INGOT, VOID_INGOT, null,
            INFINITY_MACHINE_CIRCUIT, VOID_INGOT, GEO_QUARRY, GEO_QUARRY, VOID_INGOT, INFINITY_MACHINE_CIRCUIT,
            INFINITY_MACHINE_CIRCUIT, VOID_INGOT, GEO_QUARRY, GEO_QUARRY, VOID_INGOT, INFINITY_MACHINE_CIRCUIT,
            null, VOID_INGOT, VOID_INGOT, VOID_INGOT, VOID_INGOT, null,
            INFINITY_INGOT, null, null, null, null, INFINITY_INGOT,
            // @formatter:on
        )
    }

    val GEAR_TRANSFORMER = buildSlimefunItem<GearTransformer>(12_000) {
        id = "GEAR_TRANSFORMER"
        material = Material.EMERALD_BLOCK.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            MAGSTEEL_PLATE, MACHINE_CIRCUIT, MAGSTEEL_PLATE,
            MACHINE_CIRCUIT, Material.SMITHING_TABLE.toItem(), MACHINE_CIRCUIT,
            MAGSTEEL_PLATE, MACHINE_CIRCUIT, MAGSTEEL_PLATE,
            // @formatter:on
        )
    }

    val ADVANCED_ANVIL = buildSlimefunItem<AdvancedAnvil>(100_000) {
        id = "ADVANCED_ANVIL"
        material = Material.SMITHING_TABLE.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE,
            MACHINE_PLATE, Material.ANVIL.toItem(), MACHINE_PLATE,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT
            // @formatter:on
        )
    }

    val INFINITY_WORKBENCH = buildSlimefunItem<InfinityWorkbench>(10_000_000) {
        id = "INFINITY_WORKBENCH"
        material = Material.RESPAWN_ANCHOR.convert()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            VOID_BLOCK, MACHINE_PLATE, VOID_BLOCK,
            SlimefunItems.ENERGIZED_CAPACITOR, Material.CRAFTING_TABLE.toItem(), SlimefunItems.ENERGIZED_CAPACITOR,
            VOID_BLOCK, MACHINE_PLATE, VOID_BLOCK,
            // @formatter:on
        )
    }
    //</editor-fold>

    //<editor-fold desc="Generators" defaultstate="collapsed">
    val HYDRO_GENERATOR = buildSlimefunItem<EnergyGenerator>(GeneratorType.HYDROELECTRIC, 5) {
        id = "HYDRO_GENERATOR"
        material = Material.PRISMARINE_WALL.convert()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            // @formatter:off
            MAGSTEEL, MACHINE_CIRCUIT, MAGSTEEL,
            Material.BUCKET.toItem(), SlimefunItems.ELECTRO_MAGNET, Material.BUCKET.toItem(),
            MAGSTEEL, MACHINE_CIRCUIT, MAGSTEEL,
            // @formatter:on
        )
    }

    val HYDRO_GENERATOR_2 = buildSlimefunItem<EnergyGenerator>(GeneratorType.HYDROELECTRIC, 45) {
        id = "HYDRO_GENERATOR_2"
        material = Material.END_STONE_BRICK_WALL.convert()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            HYDRO_GENERATOR, MACHINE_CIRCUIT, HYDRO_GENERATOR,
            MAGSTEEL_PLATE, MACHINE_CORE, MAGSTEEL_PLATE,
            HYDRO_GENERATOR, MACHINE_CIRCUIT, HYDRO_GENERATOR,
        )
    }

    val GEOTHERMAL_GENERATOR = buildSlimefunItem<EnergyGenerator>(GeneratorType.GEOTHERMAL, 35) {
        id = "GEOTHERMAL_GENERATOR"
        material = Material.MAGMA_BLOCK.convert()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL_PLATE, MAGSTEEL_PLATE, MAGSTEEL_PLATE,
            SlimefunItems.LAVA_GENERATOR_2, SlimefunItems.LAVA_GENERATOR_2, SlimefunItems.LAVA_GENERATOR_2,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
        )
    }

    val GEOTHERMAL_GENERATOR_2 = buildSlimefunItem<EnergyGenerator>(GeneratorType.GEOTHERMAL, 210) {
        id = "GEOTHERMAL_GENERATOR_2"
        material = Material.SHROOMLIGHT.convert()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            GEOTHERMAL_GENERATOR, MACHINE_CIRCUIT, GEOTHERMAL_GENERATOR,
            MAGSTEEL_PLATE, MACHINE_CORE, MAGSTEEL_PLATE,
            GEOTHERMAL_GENERATOR, MACHINE_CIRCUIT, GEOTHERMAL_GENERATOR,
        )
    }

    val SOLAR_PANEL = buildSlimefunItem<EnergyGenerator>(GeneratorType.SOLAR, 10) {
        id = "SOLAR_PANEL"
        material = Material.BLUE_GLAZED_TERRACOTTA.convert()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL, MAGSTEEL_PLATE, MAGSTEEL,
            SlimefunItems.SOLAR_PANEL, SlimefunItems.SOLAR_PANEL, SlimefunItems.SOLAR_PANEL,
            MACHINE_CIRCUIT, MACHINE_CIRCUIT, MACHINE_CIRCUIT,
        )
    }

    val SOLAR_PANEL_2 = buildSlimefunItem<EnergyGenerator>(GeneratorType.SOLAR, 150) {
        id = "SOLAR_PANEL_2"
        material = Material.RED_GLAZED_TERRACOTTA.convert()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            SOLAR_PANEL, SOLAR_PANEL, SOLAR_PANEL,
            TITANIUM, SlimefunItems.SOLAR_GENERATOR_4, TITANIUM,
            MACHINE_CIRCUIT, MACHINE_CIRCUIT, MACHINE_CIRCUIT,
        )
    }

    val SOLAR_PANEL_3 = buildSlimefunItem<EnergyGenerator>(GeneratorType.SOLAR, 750) {
        id = "SOLAR_PANEL_3"
        material = Material.YELLOW_GLAZED_TERRACOTTA.convert()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE,
            SOLAR_PANEL_2, SOLAR_PANEL_2, SOLAR_PANEL_2,
            MACHINE_CIRCUIT, MACHINE_CORE, MACHINE_CIRCUIT,
        )
    }

    val VOID_PANEL = buildSlimefunItem<EnergyGenerator>(GeneratorType.LUNAR, 3_000) {
        id = "VOID_PANEL"
        material = Material.LIGHT_GRAY_GLAZED_TERRACOTTA.convert()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            VOID_INGOT, VOID_INGOT, VOID_INGOT,
            SOLAR_PANEL_3, SOLAR_PANEL_3, SOLAR_PANEL_3,
            MAGNONIUM, MAGNONIUM, MAGNONIUM,
        )
    }

    val INFINITY_PANEL = buildSlimefunItem<EnergyGenerator>(GeneratorType.INFINITY, 60_000) {
        id = "INFINITY_PANEL"
        material = Material.LIGHT_BLUE_GLAZED_TERRACOTTA.convert()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            SOLAR_PANEL_3, SOLAR_PANEL_3, SOLAR_PANEL_3, SOLAR_PANEL_3, SOLAR_PANEL_3, SOLAR_PANEL_3,
            SOLAR_PANEL_3, SOLAR_PANEL_3, SOLAR_PANEL_3, SOLAR_PANEL_3, SOLAR_PANEL_3, SOLAR_PANEL_3,
            INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT,
            INFINITY_INGOT, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CIRCUIT, INFINITY_INGOT,
            INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT,
            VOID_PANEL, VOID_PANEL, VOID_PANEL, VOID_PANEL, VOID_PANEL, VOID_PANEL,
            // @formatter:on
        )
    }

    val INFINITY_REACTOR = buildSlimefunItem<InfinityReactor>(120_000) {
        id = "INFINITY_REACTOR"
        material = Material.BEACON.convert()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = arrayOf(
            // @formatter:off
            null, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, INFINITY_INGOT, null,
            INFINITY_INGOT, INFINITY_INGOT, VOID_INGOT, VOID_INGOT, INFINITY_INGOT, INFINITY_INGOT,
            INFINITY_INGOT, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, INFINITY_INGOT,
            INFINITY_INGOT, MACHINE_PLATE, ADVANCED_NETHER_STAR_REACTOR, ADVANCED_NETHER_STAR_REACTOR, MACHINE_PLATE, INFINITY_INGOT,
            INFINITY_INGOT, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, MACHINE_PLATE, INFINITY_INGOT,
            INFINITY_INGOT, INFINITY_MACHINE_CIRCUIT, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CORE, INFINITY_MACHINE_CIRCUIT, INFINITY_INGOT,
            // @formatter:on
        )
    }
    //</editor-fold>

    //<editor-fold desc="Mob Simulation" defaultstate="collapsed">
    val MOB_SIMULATION_CHAMBER = buildSlimefunItem<MobSimulationChamber>(150) {
        id = "MOB_SIMULATION_CHAMBER"
        material = Material.GILDED_BLACKSTONE.convert()
        itemGroup = IEItemGroups.MOB_SIMULATION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL_PLATE, MACHINE_PLATE, MAGSTEEL_PLATE,
            MACHINE_CIRCUIT, SlimefunItems.PROGRAMMABLE_ANDROID_BUTCHER, MACHINE_CIRCUIT,
            MAGSTEEL_PLATE, MACHINE_PLATE, MAGSTEEL_PLATE,
        )
    }

    val MOB_DATA_INFUSER = buildSlimefunItem<MobDataInfuser>(20_000) {
        id = "MOB_DATA_INFUSER"
        material = Material.LODESTONE.convert()
        itemGroup = IEItemGroups.MOB_SIMULATION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MACHINE_CIRCUIT, SlimefunItems.REINFORCED_ALLOY_INGOT, MACHINE_CIRCUIT,
            SlimefunItems.REINFORCED_ALLOY_INGOT, MACHINE_CORE, SlimefunItems.REINFORCED_ALLOY_INGOT,
            MACHINE_CIRCUIT, SlimefunItems.REINFORCED_ALLOY_INGOT, MACHINE_CIRCUIT
        )
    }

    val MOB_DATA_CARD_EMPTY = buildSlimefunItem<SimpleMaterial> {
        id = "MOB_DATA_CARD_EMPTY"
        material = Material.CHAINMAIL_CHESTPLATE.convert()
        itemGroup = IEItemGroups.MOB_SIMULATION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            SlimefunItems.MAGNESIUM_INGOT, MACHINE_CIRCUIT, SlimefunItems.MAGNESIUM_INGOT,
            SlimefunItems.SYNTHETIC_SAPPHIRE, SlimefunItems.SYNTHETIC_DIAMOND, SlimefunItems.SYNTHETIC_EMERALD,
            SlimefunItems.MAGNESIUM_INGOT, MACHINE_CIRCUIT, SlimefunItems.MAGNESIUM_INGOT,
        )
    }
    //</editor-fold>

    //<editor-fold desc="Hidden" defaultstate="collapsed">
    val OSCILLATOR = buildSlimefunItem<Oscillator>(Material.AIR.toItem()) {
        id = "OSCILLATOR"
        material = Material.REDSTONE_TORCH.convert()
        itemGroup = IEItemGroups.HIDDEN
        recipeType = RecipeType.NULL
        recipe = emptyRecipe()
    }

    // register this to ensure the recipe output items are recognized as sf items
    val MOB_DATA_CARD = buildSlimefunItem<MobDataCard>(MobDataCardProps.EMPTY) {
        id = "MOB_DATA_CARD"
        material = Material.LEATHER_CHESTPLATE.convert()
        itemGroup = IEItemGroups.HIDDEN
        recipeType = RecipeType.NULL
        recipe = emptyRecipe()
    }
    //</editor-fold>
}
