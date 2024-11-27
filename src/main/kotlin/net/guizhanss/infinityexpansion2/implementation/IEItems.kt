@file:Suppress("kotlin:S1192", "unused", "MemberVisibilityCanBePrivate")

package net.guizhanss.infinityexpansion2.implementation

import io.github.seggan.sf4k.item.builder.ItemRegistry
import io.github.seggan.sf4k.item.builder.asMaterialType
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectionType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture
import net.guizhanss.infinityexpansion2.InfinityExpansion2
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
import net.guizhanss.infinityexpansion2.implementation.items.generators.InfinitySingularityReactor
import net.guizhanss.infinityexpansion2.implementation.items.machines.AdvancedAnvil
import net.guizhanss.infinityexpansion2.implementation.items.machines.CobblePress
import net.guizhanss.infinityexpansion2.implementation.items.machines.Decompressor
import net.guizhanss.infinityexpansion2.implementation.items.machines.DustExtractor
import net.guizhanss.infinityexpansion2.implementation.items.machines.ExtremeFreezer
import net.guizhanss.infinityexpansion2.implementation.items.machines.FlowerGrower
import net.guizhanss.infinityexpansion2.implementation.items.machines.GearTransformer
import net.guizhanss.infinityexpansion2.implementation.items.machines.GeoQuarry
import net.guizhanss.infinityexpansion2.implementation.items.machines.InfinityWorkbench
import net.guizhanss.infinityexpansion2.implementation.items.machines.IngotFormer
import net.guizhanss.infinityexpansion2.implementation.items.machines.MaterialGenerator
import net.guizhanss.infinityexpansion2.implementation.items.machines.Quarry
import net.guizhanss.infinityexpansion2.implementation.items.machines.ResourceSynthesizer
import net.guizhanss.infinityexpansion2.implementation.items.machines.SingularityConstructor
import net.guizhanss.infinityexpansion2.implementation.items.machines.SmithingTemplateRandomizer
import net.guizhanss.infinityexpansion2.implementation.items.machines.StoneworksFactory
import net.guizhanss.infinityexpansion2.implementation.items.machines.TreeGrower
import net.guizhanss.infinityexpansion2.implementation.items.machines.UraniumExtractor
import net.guizhanss.infinityexpansion2.implementation.items.machines.UraniumIngotExtractor
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
import net.guizhanss.infinityexpansion2.utils.items.applyInfinityGearEnchantment
import net.guizhanss.infinityexpansion2.utils.items.builder.asMaterialType
import net.guizhanss.infinityexpansion2.utils.items.builder.buildSlimefunItem
import net.guizhanss.infinityexpansion2.utils.items.builder.recipes.buildRecipe
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

/**
 * Stores almost all the items in Infinity Expansion 2, also responsible for registering them.
 */
object IEItems : ItemRegistry(InfinityExpansion2.instance, InfinityExpansion2.localization.idPrefix) {

    //<editor-fold desc="Singularities" defaultstate="collapsed">
    val IRON_SINGULARITY by buildSlimefunItem<Singularity>(
        2000, mapOf(
            Material.IRON_INGOT.toItem() to 1,
            Material.IRON_BLOCK.toItem() to 9,
        )
    ) {
        material = Material.IRON_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val GOLD_SINGULARITY by buildSlimefunItem<Singularity>(
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
        material = Material.GOLD_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val LAPIS_SINGULARITY by buildSlimefunItem<Singularity>(
        1500, mapOf(
            Material.LAPIS_LAZULI.toItem() to 1,
            Material.LAPIS_BLOCK.toItem() to 9,
        )
    ) {
        material = Material.LAPIS_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val REDSTONE_SINGULARITY by buildSlimefunItem<Singularity>(
        3000, mapOf(
            Material.REDSTONE.toItem() to 1,
            Material.REDSTONE_BLOCK.toItem() to 9,
        )
    ) {
        material = Material.REDSTONE_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val QUARTZ_SINGULARITY by buildSlimefunItem<Singularity>(
        3000, mapOf(
            Material.QUARTZ.toItem() to 1,
            Material.QUARTZ_BLOCK.toItem() to 4,
        )
    ) {
        material = Material.QUARTZ_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val COPPER_SINGULARITY by buildSlimefunItem<Singularity>(
        3000, mapOf(
            Material.COPPER_INGOT.toItem() to 1,
            Material.COPPER_BLOCK.toItem() to 9,
            SlimefunItems.COPPER_INGOT to 1,
        )
    ) {
        material = Material.COPPER_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val TIN_SINGULARITY by buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.TIN_INGOT to 1,
        )
    ) {
        material = Material.IRON_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val LEAD_SINGULARITY by buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.LEAD_INGOT to 1,
        )
    ) {
        material = Material.IRON_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val SILVER_SINGULARITY by buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.SILVER_INGOT to 1,
        )
    ) {
        material = Material.IRON_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val ZINC_SINGULARITY by buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.ZINC_INGOT to 1,
        )
    ) {
        material = Material.IRON_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val ALUMINUM_SINGULARITY by buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.ALUMINUM_INGOT to 1,
        )
    ) {
        material = Material.IRON_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val MAGNESIUM_SINGULARITY by buildSlimefunItem<Singularity>(
        3000, mapOf(
            SlimefunItems.MAGNESIUM_INGOT to 1,
        )
    ) {
        material = Material.IRON_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val DIAMOND_SINGULARITY by buildSlimefunItem<Singularity>(
        500, mapOf(
            Material.DIAMOND.toItem() to 1,
            Material.DIAMOND_BLOCK.toItem() to 9,
            SlimefunItems.SYNTHETIC_DIAMOND to 1,
        )
    ) {
        material = Material.DIAMOND_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val EMERALD_SINGULARITY by buildSlimefunItem<Singularity>(
        500, mapOf(
            Material.EMERALD.toItem() to 1,
            Material.EMERALD_BLOCK.toItem() to 9,
            SlimefunItems.SYNTHETIC_EMERALD to 1,
        )
    ) {
        material = Material.EMERALD_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val NETHERITE_SINGULARITY by buildSlimefunItem<Singularity>(
        200, mapOf(
            Material.NETHERITE_INGOT.toItem() to 1,
            Material.NETHERITE_BLOCK.toItem() to 9,
        )
    ) {
        material = Material.NETHERITE_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val COAL_SINGULARITY by buildSlimefunItem<Singularity>(
        1500, mapOf(
            Material.COAL.toItem() to 1,
            Material.COAL_BLOCK.toItem() to 9,
            SlimefunItems.CARBON to 8,
            SlimefunItems.COMPRESSED_CARBON to 32,
            SlimefunItems.CARBON_CHUNK to 256,
        )
    ) {
        material = Material.COAL_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }
    //</editor-fold>

    //<editor-fold desc="Materials" defaultstate="collapsed">
    val ENDER_ESSENCE by buildSlimefunItem<EnderEssence> {
        material = Material.BLAZE_POWDER.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.GEO_MINER
    }

    val COMPRESSED_COBBLESTONE_1 by buildSlimefunItem<SimpleMaterial> {
        material = Material.ANDESITE.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"xxx"
            +"xxx"
            +"xxx"
            'x' means Material.COBBLESTONE.toItem()
        }
    }

    val COMPRESSED_COBBLESTONE_2 by buildSlimefunItem<SimpleMaterial> {
        material = Material.ANDESITE.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"xxx"
            +"xxx"
            +"xxx"
            'x' means COMPRESSED_COBBLESTONE_1
        }
    }

    val COMPRESSED_COBBLESTONE_3 by buildSlimefunItem<SimpleMaterial> {
        material = Material.ANDESITE.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"xxx"
            +"xxx"
            +"xxx"
            'x' means COMPRESSED_COBBLESTONE_2
        }
    }

    val COMPRESSED_COBBLESTONE_4 by buildSlimefunItem<SimpleMaterial> {
        material = Material.STONE.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"xxx"
            +"xxx"
            +"xxx"
            'x' means COMPRESSED_COBBLESTONE_3
        }
    }

    val COMPRESSED_COBBLESTONE_5 by buildSlimefunItem<SimpleMaterial> {
        material = Material.STONE.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"xxx"
            +"xxx"
            +"xxx"
            'x' means COMPRESSED_COBBLESTONE_4
        }
    }

    val MAGSTEEL by buildSlimefunItem<SimpleMaterial> {
        material = Material.BRICK.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.SMELTERY
        recipe = buildRecipe {
            +"MSm"
            +"   "
            +"   "
            'M' means SlimefunItems.MAGNESIUM_INGOT
            'S' means SlimefunItems.STEEL_INGOT
            'm' means SlimefunItems.MAGNESIUM_DUST
        }
    }

    val TITANIUM by buildSlimefunItem<SimpleMaterial> {
        material = Material.IRON_INGOT.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.SMELTERY
        recipe = buildRecipe {
            +"RDH"
            +"   "
            +"   "
            'R' means SlimefunItems.REINFORCED_ALLOY_INGOT
            'D' means SlimefunItems.DAMASCUS_STEEL_INGOT
            'H' means SlimefunItems.HARDENED_METAL_INGOT
        }
    }

    val MYTHRIL by buildSlimefunItem<SimpleMaterial> {
        material = Material.IRON_INGOT.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.SMELTERY
        recipe = buildRecipe {
            +"MIm"
            +"   "
            +"   "
            'M' means SlimefunItems.MAGNESIUM_INGOT
            'I' means IRON_SINGULARITY
            'm' means SlimefunItems.MAGNESIUM_DUST
        }
    }

    val ADAMANTITE by buildSlimefunItem<SimpleMaterial> {
        material = Material.BRICK.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.SMELTERY
        recipe = buildRecipe {
            +"RDM"
            +"   "
            +"   "
            'R' means SlimefunItems.REDSTONE_ALLOY
            'D' means DIAMOND_SINGULARITY
            'M' means MAGSTEEL
        }
    }

    val MAGNONIUM by buildSlimefunItem<SimpleMaterial> {
        material = Material.NETHER_BRICK.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            MAGSTEEL,
            MAGNESIUM_SINGULARITY,
            ENDER_ESSENCE,
        )
        recipe = buildRecipe {
            +"MSE"
            +"   "
            +"   "
            'M' means MAGSTEEL
            'S' means MAGNESIUM_SINGULARITY
            'E' means ENDER_ESSENCE
        }
    }

    val VOID_BIT by buildSlimefunItem<SimpleMaterial> {
        material = Material.IRON_NUGGET.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = IERecipeTypes.VOID_HARVESTER
    }

    val VOID_DUST by buildSlimefunItem<SimpleMaterial> {
        material = Material.GUNPOWDER.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"VVV"
            +"VVV"
            +"VVV"
            'V' means VOID_BIT
        }
    }

    val VOID_INGOT by buildSlimefunItem<SimpleMaterial> {
        material = Material.NETHERITE_INGOT.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"VVV"
            +"VVV"
            +"VVV"
            'V' means VOID_DUST
        }
    }

    val VOID_BLOCK by buildSlimefunItem<VoidBlock> {
        material = Material.NETHERITE_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"VVV"
            +"VVV"
            +"VVV"
            'V' means VOID_INGOT
        }
    }

    // singularities here due to load order
    val FORTUNE_SINGULARITY by buildSlimefunItem<SimpleMaterial> {
        material = Material.NETHER_STAR.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = RecipeType.SMELTERY
        recipe = buildRecipe {
            +"GDE"
            +"NA "
            +"   "
            'G' means GOLD_SINGULARITY
            'D' means DIAMOND_SINGULARITY
            'E' means EMERALD_SINGULARITY
            'N' means NETHERITE_SINGULARITY
            'A' means ADAMANTITE
        }
    }

    val MAGIC_SINGULARITY by buildSlimefunItem<SimpleMaterial> {
        material = Material.NETHER_STAR.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            REDSTONE_SINGULARITY, LAPIS_SINGULARITY, QUARTZ_SINGULARITY,
            MAGNESIUM_SINGULARITY, MAGNONIUM,
        )
        recipe = buildRecipe {
            +"RLQ"
            +"MG "
            +"   "
            'R' means REDSTONE_SINGULARITY
            'L' means LAPIS_SINGULARITY
            'Q' means QUARTZ_SINGULARITY
            'M' means MAGNESIUM_SINGULARITY
            'G' means MAGNONIUM
        }
    }

    val EARTH_SINGULARITY by buildSlimefunItem<SimpleMaterial> {
        material = Material.NETHER_STAR.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            COMPRESSED_COBBLESTONE_4, COAL_SINGULARITY, IRON_SINGULARITY,
            COPPER_SINGULARITY, LEAD_SINGULARITY,
        )
        recipe = buildRecipe {
            +"XCI"
            +"OL "
            +"   "
            'X' means COMPRESSED_COBBLESTONE_4
            'C' means COAL_SINGULARITY
            'I' means IRON_SINGULARITY
            'O' means COPPER_SINGULARITY
            'L' means LEAD_SINGULARITY
        }
    }

    val METAL_SINGULARITY by buildSlimefunItem<SimpleMaterial> {
        material = Material.NETHER_STAR.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = RecipeType.SMELTERY
        recipe = buildRecipe {
            +"SAT"
            +"ZI "
            +"   "
            'S' means SILVER_SINGULARITY
            'A' means ALUMINUM_SINGULARITY
            'T' means TIN_SINGULARITY
            'Z' means ZINC_SINGULARITY
            'I' means TITANIUM
        }
    }

    val INFINITY_INGOT by buildSlimefunItem<SimpleMaterial> {
        material = Material.IRON_INGOT.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.SMELTERY
        recipe = arrayOf(
            // @formatter:off
            EARTH_SINGULARITY, MYTHRIL, FORTUNE_SINGULARITY,
            MAGIC_SINGULARITY, VOID_INGOT, METAL_SINGULARITY,
            // @formatter:on
        )
        recipe = buildRecipe {
            +"EMF"
            +"AVT"
            +"   "
            'E' means EARTH_SINGULARITY
            'M' means MYTHRIL
            'F' means FORTUNE_SINGULARITY
            'A' means MAGIC_SINGULARITY
            'V' means VOID_INGOT
            'T' means METAL_SINGULARITY
        }
    }

    // Singularity put here due to load order
    val INFINITY_SINGULARITY by buildSlimefunItem<Singularity>(
        100, mapOf(
            INFINITY_INGOT to 1,
        )
    ) {
        material = Material.SMOOTH_QUARTZ.asMaterialType()
        itemGroup = IEItemGroups.SINGULARITIES
        recipeType = IERecipeTypes.SINGULARITY_CONSTRUCTOR
    }

    val MAGSTEEL_PLATE by buildSlimefunItem<SimpleMaterial> {
        material = Material.NETHERITE_SCRAP.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"MMM"
            +"MHM"
            +"MMM"
            'M' means MAGSTEEL
            'H' means SlimefunItems.HARDENED_METAL_INGOT
        }
    }

    val MACHINE_CIRCUIT by buildSlimefunItem<SimpleMaterial> {
        material = Material.GOLD_INGOT.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"CEC"
            +"CSC"
            +"CEC"
            'C' means SlimefunItems.COPPER_INGOT
            'E' means SlimefunItems.ELECTRO_MAGNET
            'S' means SlimefunItems.SILICON
        }
    }

    val MACHINE_PLATE by buildSlimefunItem<SimpleMaterial> {
        material = Material.PAPER.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"RPR"
            +"MTM"
            +"RPR"
            'R' means SlimefunItems.REINFORCED_ALLOY_INGOT
            'P' means SlimefunItems.REINFORCED_PLATE
            'M' means MAGSTEEL_PLATE
            'T' means TITANIUM
        }
    }

    val MACHINE_CORE by buildSlimefunItem<SimpleMaterial> {
        material = Material.IRON_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"TCT"
            +"CMC"
            +"TCT"
            'T' means TITANIUM
            'C' means MACHINE_CIRCUIT
            'M' means MACHINE_PLATE
        }
    }

    val VOID_GLASS by buildSlimefunItem<VoidGlass>(16) {
        material = Material.GLASS.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"GGG"
            +"GVG"
            +"GGG"
            'V' means VOID_BLOCK
            'G' means Material.GLASS.toItem()
        }
    }

    val INFINITY_MACHINE_CIRCUIT by buildSlimefunItem<SimpleMaterial> {
        material = Material.DIAMOND.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"CICCIC"
            +"VCVVCV"
            +"IVCCVI"
            +"IVCCVI"
            +"VCVVCV"
            +"CICCIC"
            'C' means MACHINE_CIRCUIT
            'I' means INFINITY_INGOT
            'V' means VOID_INGOT
        }
    }

    val INFINITY_MACHINE_CORE by buildSlimefunItem<SimpleMaterial> {
        material = Material.DIAMOND_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"POIIOP"
            +"OPCCPO"
            +"ICIICI"
            +"ICIICI"
            +"OPCCPO"
            +"POIIOP"
            'P' means MACHINE_PLATE
            'O' means MACHINE_CORE
            'I' means INFINITY_INGOT
        }
    }

    val OSCILLATOR_FRAME by buildSlimefunItem<SimpleMaterial> {
        material = Material.IRON_BARS.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"PBP"
            +"B B"
            +"PBP"
            'P' means MACHINE_PLATE
            'B' means SlimefunItems.BLISTERING_INGOT_3
        }
    }

    val UNKNOWN_SMITHING_TEMPLATE by buildSlimefunItem<SimpleMaterial> {
        material = Material.NETHERITE_INGOT.asMaterialType()
        itemGroup = IEItemGroups.MATERIALS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"NNN"
            +"NVN"
            +"NNN"
            'N' means Material.NETHERITE_INGOT.toItem()
            'V' means VOID_INGOT
        }
    }
    //</editor-fold>

    //<editor-fold desc="Slimefun Expansion" defaultstate="collapsed">
    val VOID_CAPACITOR by buildSlimefunItem<Capacitor>(16_000_000) {
        material = HeadTexture.CAPACITOR_25.asMaterialType()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"VRV"
            +"VCV"
            +"VRV"
            'V' means VOID_INGOT
            'R' means REDSTONE_SINGULARITY
            'C' means SlimefunItems.ENERGIZED_CAPACITOR
        }
    }

    val INFINITY_CAPACITOR by buildSlimefunItem<Capacitor>(64_000_000) {
        material = HeadTexture.CAPACITOR_25.asMaterialType()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +" IVVI "
            +" IOOI "
            +" ICCI "
            +" ICCI "
            +" IOOI "
            +" IVVI "
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
            'O' means INFINITY_MACHINE_CORE
            'C' means VOID_CAPACITOR
        }
    }

    val ADVANCED_ENCHANTER by buildSlimefunItem<AutoEnchanter>(5, 180) {
        material = Material.ENCHANTING_TABLE.asMaterialType()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"MMM"
            +"PAP"
            +"COC"
            'M' means MAGSTEEL
            'P' means MAGSTEEL_PLATE
            'A' means SlimefunItems.AUTO_ENCHANTER
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val INFINITY_ENCHANTER by buildSlimefunItem<AutoEnchanter>(75, 12_000) {
        material = Material.ENCHANTING_TABLE.asMaterialType()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"      "
            +"V    V"
            +"VVAAVV"
            +"PVCCVP"
            +"PVOOVP"
            +"PIIIIP"
            'V' means VOID_INGOT
            'A' means ADVANCED_ENCHANTER
            'P' means MACHINE_PLATE
            'C' means INFINITY_MACHINE_CIRCUIT
            'O' means INFINITY_MACHINE_CORE
            'I' means INFINITY_INGOT
        }
    }

    val ADVANCED_DISENCHANTER by buildSlimefunItem<AutoDisenchanter>(5, 180) {
        material = Material.ENCHANTING_TABLE.asMaterialType()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"MMM"
            +"PAP"
            +"COC"
            'M' means MAGSTEEL
            'P' means MAGSTEEL_PLATE
            'A' means SlimefunItems.AUTO_DISENCHANTER
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val INFINITY_DISENCHANTER by buildSlimefunItem<AutoDisenchanter>(90, 12_000) {
        material = Material.ENCHANTING_TABLE.asMaterialType()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"      "
            +"V    V"
            +"VVAAVV"
            +"PVCCVP"
            +"PVOOVP"
            +"PIIIIP"
            'V' means VOID_INGOT
            'A' means ADVANCED_DISENCHANTER
            'P' means MACHINE_PLATE
            'C' means INFINITY_MACHINE_CIRCUIT
            'O' means INFINITY_MACHINE_CORE
            'I' means INFINITY_INGOT
        }
    }

    val ADVANCED_CHARGER by buildSlimefunItem<ChargingBench>(30, 180) {
        material = Material.HONEYCOMB_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"MCM"
            +"CAC"
            +"MOM"
            'M' means MAGSTEEL_PLATE
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
            'A' means SlimefunItems.CHARGING_BENCH
        }
    }

    val INFINITY_CHARGER by buildSlimefunItem<ChargingBench>(6000, 60_000) {
        material = Material.SEA_LANTERN.asMaterialType()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"      "
            +"VccccV"
            +"VcAAcV"
            +"VcAAcV"
            +"VCOOCV"
            +"IIIIII"
            'V' means VOID_INGOT
            'c' means MACHINE_CIRCUIT
            'A' means ADVANCED_CHARGER
            'P' means MACHINE_PLATE
            'C' means INFINITY_MACHINE_CIRCUIT
            'O' means INFINITY_MACHINE_CORE
            'I' means INFINITY_INGOT
        }
    }

    val ADVANCED_GEO_MINER by buildSlimefunItem<GeoMiner>(4, 120) {
        material = HeadTexture.GEO_MINER.asMaterialType()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"MMM"
            +"PAP"
            +"COC"
            'M' means MAGSTEEL_PLATE
            'P' means SlimefunItems.COBALT_PICKAXE
            'A' means SlimefunItems.GEO_MINER
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val ADVANCED_SMELTERY by buildSlimefunItem<Smeltery>(24, 240) {
        material = Material.FURNACE.asMaterialType()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"SSS"
            +"SSS"
            +"COC"
            'S' means SlimefunItems.ELECTRIC_SMELTERY_2
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val VOID_SMELTERY by buildSlimefunItem<Smeltery>(72, 1200) {
        material = Material.FURNACE.asMaterialType()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"VOV"
            +"CSC"
            +"VSV"
            'V' means VOID_BLOCK
            'O' means MACHINE_CORE
            'C' means MACHINE_CIRCUIT
            'S' means ADVANCED_SMELTERY
        }
    }

    val ADVANCED_NETHER_STAR_REACTOR by buildSlimefunItem<NetherStarReactor>(1800, 90000) {
        material = HeadTexture.NETHER_STAR_REACTOR.asMaterialType()
        itemGroup = IEItemGroups.SLIMEFUN_EXPANSION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"GGG"
            +"CNC"
            +"OOO"
            'G' means SlimefunItems.WITHER_PROOF_GLASS
            'C' means MACHINE_CIRCUIT
            'N' means SlimefunItems.NETHER_STAR_REACTOR
            'O' means SlimefunItems.WITHER_PROOF_OBSIDIAN
        }
    }
    //</editor-fold>

    //<editor-fold desc="Food" defaultstate="collapsed">
    val COSMIC_MEATBALLS by buildSlimefunItem<CosmicMeatballs> {
        material = Material.COOKED_BEEF.asMaterialType()
        itemGroup = IEItemGroups.FOOD
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"      "
            +" CBBR "
            +" CVOR "
            +" MO S "
            +" MPPS "
            +"      "
            'C' means Material.CHICKEN.toItem()
            'B' means Material.BEEF.toItem()
            'R' means Material.RABBIT.toItem()
            'V' means VOID_DUST
            'O' means Material.COD.toItem()
            'M' means Material.MUTTON.toItem()
            'S' means Material.SALMON.toItem()
            'P' means Material.PORKCHOP.toItem()
        }
    }

    val ULTIMATE_STEW by buildSlimefunItem<UltimateStew> {
        material = Material.SUSPICIOUS_STEW.asMaterialType()
        itemGroup = IEItemGroups.FOOD
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"      "
            +" WPPC "
            +" WVNC "
            +" cN m "
            +" cMMm "
            +"      "
            'W' means Material.WHEAT.toItem()
            'P' means Material.POTATO.toItem()
            'C' means Material.CACTUS.toItem()
            'V' means VOID_DUST
            'N' means Material.NETHER_WART.toItem()
            'c' means Material.CARROT.toItem()
            'm' means Material.BROWN_MUSHROOM.toItem()
            'M' means Material.RED_MUSHROOM.toItem()
        }
    }
    //</editor-fold>

    //<editor-fold desc="Tools" defaultstate="collapsed">
    val ENDER_FLAME by buildSlimefunItem<SimpleMaterial> {
        material = Material.ENCHANTED_BOOK.asMaterialType()
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.MAGIC_WORKBENCH
        recipe = buildRecipe {
            +"EEE"
            +"EBE"
            +"EEE"
            'E' means ENDER_ESSENCE
            'B' means Material.BOOK.toItem()
        }

        itemModifier = {
            it.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 10)
        }
    }

    val VEIN_MINER_RUNE by buildSlimefunItem<VeinMinerRune> {
        material = Material.DIAMOND.asMaterialType()
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.MAGIC_WORKBENCH
        recipe = buildRecipe {
            +"MVM"
            +"ERE"
            +"MVM"
            'M' means MAGSTEEL_PLATE
            'V' means SlimefunItems.PICKAXE_OF_VEIN_MINING
            'E' means ENDER_ESSENCE
            'R' means SlimefunItems.BLANK_RUNE
        }
    }

    val STRAINER_BASE by buildSlimefunItem<StrainerBase> {
        material = Material.SANDSTONE_WALL.asMaterialType()
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"STS"
            +"STS"
            +"MMM"
            'S' means Material.STICK.toItem()
            'T' means Material.STRING.toItem()
            'M' means MAGSTEEL
        }
    }

    val STRAINER_1 by buildSlimefunItem<Strainer>(30) {
        material = Material.FISHING_ROD.asMaterialType()
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"STS"
            +"TST"
            +"STS"
            'S' means Material.STICK.toItem()
            'T' means Material.STRING.toItem()
        }
    }

    val STRAINER_2 by buildSlimefunItem<Strainer>(60) {
        material = Material.FISHING_ROD.asMaterialType()
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"MSM"
            +"STS"
            +"MSM"
            'S' means Material.STRING.toItem()
            'T' means STRAINER_1
            'M' means MAGSTEEL
        }
    }

    val STRAINER_3 by buildSlimefunItem<Strainer>(90) {
        material = Material.FISHING_ROD.asMaterialType()
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"RSR"
            +"STS"
            +"RSR"
            'S' means Material.STRING.toItem()
            'T' means STRAINER_2
            'R' means SlimefunItems.REINFORCED_ALLOY_INGOT
        }
    }

    val INFINITY_PICKAXE by buildSlimefunItem<InfinityTool> {
        material = Material.NETHERITE_PICKAXE.asMaterialType()
        itemGroup = IEItemGroups.TOOLS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +" VIII "
            +"   IVI"
            +"   VII"
            +"  V  I"
            +" V   V"
            +"V     "
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
        }
        itemModifier = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_AXE by buildSlimefunItem<InfinityTool> {
        material = Material.NETHERITE_AXE.asMaterialType()
        itemGroup = IEItemGroups.TOOLS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +" VII  "
            +"VIIIV "
            +" IIVII"
            +"  VIII"
            +" V IIV"
            +"V   V "
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
        }
        itemModifier = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_SHOVEL by buildSlimefunItem<InfinityTool> {
        material = Material.NETHERITE_SHOVEL.asMaterialType()
        itemGroup = IEItemGroups.TOOLS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"   III"
            +"  IIII"
            +"  IVII"
            +"  VII "
            +" V    "
            +"V     "
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
        }
        itemModifier = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_HOE by buildSlimefunItem<InfinityTool> {
        material = Material.NETHERITE_HOE.asMaterialType()
        itemGroup = IEItemGroups.TOOLS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +" IIIIV"
            +"  IIVI"
            +"   V  "
            +"  V   "
            +" V    "
            +"V     "
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
        }
        itemModifier = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_MATRIX by buildSlimefunItem<InfinityMatrix> {
        material = Material.NETHER_STAR.asMaterialType()
        itemGroup = IEItemGroups.TOOLS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"I II I"
            +"IVVVVI"
            +"VVEEVV"
            +"VVIIVV"
            +"IVVVVI"
            +"I II I"
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
            'E' means Material.ELYTRA.toItem()
        }
    }
    //</editor-fold>

    //<editor-fold desc="Gears" defaultstate="collapsed">
    val INFINITY_HELMET by buildSlimefunItem<InfinityArmor>(
        arrayOf(
            buildHiddenPotionEffect("night_vision", 600, 0),
            buildHiddenPotionEffect("conduit_power", 600, 0),
        ),
        arrayOf(ProtectionType.FLYING_INTO_WALL),
    ) {
        material = Material.NETHERITE_HELMET.asMaterialType()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +" IIII "
            +"IIIIII"
            +"IVIIVI"
            +" I  I "
            +"      "
            +"      "
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
        }
        itemModifier = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINIYT_CHESTPLATE by buildSlimefunItem<InfinityArmor>(
        arrayOf(
            buildHiddenPotionEffect("night_vision", 600, 0),
            buildHiddenPotionEffect("conduit_power", 600, 0),
        ),
        arrayOf(ProtectionType.BEES),
    ) {
        material = Material.NETHERITE_CHESTPLATE.asMaterialType()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +" I  I "
            +"IVIIVI"
            +"VIIIIV"
            +"VIVVIV"
            +" IIII "
            +" IIII "
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
        }
        itemModifier = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_LEGGINGS by buildSlimefunItem<InfinityArmor>(
        arrayOf(
            buildHiddenPotionEffect("haste", 600, 2),
            buildHiddenPotionEffect("regeneration", 600, 0),
            buildHiddenPotionEffect("saturation", 600, 0),
        ),
        arrayOf(ProtectionType.RADIATION),
    ) {
        material = Material.NETHERITE_LEGGINGS.asMaterialType()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +" IIII "
            +"IIIIII"
            +"VI  IV"
            +"VI  IV"
            +"VI  IV"
            +" I  I "
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
        }
        itemModifier = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_BOOTS by buildSlimefunItem<InfinityBoots>(
        arrayOf(
            buildHiddenPotionEffect("speed", 600, 2),
            buildHiddenPotionEffect("dolphins_grace", 600, 0),
        ), arrayOf<ProtectionType>()
    ) {
        material = Material.NETHERITE_BOOTS.asMaterialType()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"      "
            +"II  II"
            +"II  II"
            +"VV  VV"
            +"II  II"
            +"II  II"
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
        }
        itemModifier = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_SHIELD by buildSlimefunItem<InfinityTool> {
        material = Material.SHIELD.asMaterialType()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"II  II"
            +"IVIIVI"
            +"IVIIVI"
            +"IVIIVI"
            +" IVVI "
            +" IVVI "
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
        }
        itemModifier = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_SWORD by buildSlimefunItem<InfinityTool> {
        material = Material.NETHERITE_SWORD.asMaterialType()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"    II"
            +"   IVI"
            +"  IVI "
            +"IIVI  "
            +" VI   "
            +"V I   "
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
        }
        itemModifier = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_BOW by buildSlimefunItem<InfinityBow> {
        material = Material.BOW.asMaterialType()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +" IIV  "
            +"I IIV "
            +"V  EIV"
            +" V  II"
            +"  V  I"
            +"   VI "
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
            'E' means ENDER_FLAME
        }
        itemModifier = {
            applyInfinityGearEnchantment(it)
        }
    }

    val INFINITY_CROSSBOW by buildSlimefunItem<InfinityBow> {
        material = Material.CROSSBOW.asMaterialType()
        itemGroup = IEItemGroups.GEAR
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +" VIII "
            +"VIV  I"
            +"IVII V"
            +"I IIV "
            +"I  VII"
            +" IV II"
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
        }
        itemModifier = {
            applyInfinityGearEnchantment(it)
        }
    }
    //</editor-fold>

    //<editor-fold desc="Machines" defaultstate="collapsed">
    val COBBLESTONE_GENERATOR by buildSlimefunItem<MaterialGenerator>(Material.COBBLESTONE, 1, 24) {
        material = Material.SMOOTH_STONE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"MPM"
            +"WCL"
            +"McM"
            'M' means MAGSTEEL
            'P' means Material.DIAMOND_PICKAXE.toItem()
            'W' means Material.WATER_BUCKET.toItem()
            'C' means COMPRESSED_COBBLESTONE_2
            'L' means Material.LAVA_BUCKET.toItem()
            'c' means MACHINE_CIRCUIT
        }
    }

    val COBBLESTONE_GENERATOR_2 by buildSlimefunItem<MaterialGenerator>(Material.COBBLESTONE, 4, 120) {
        material = Material.SMOOTH_STONE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"MGM"
            +"WCL"
            +"CGC"
            'M' means MAGSTEEL
            'G' means COBBLESTONE_GENERATOR
            'W' means Material.WATER_BUCKET.toItem()
            'C' means COMPRESSED_COBBLESTONE_3
            'L' means Material.LAVA_BUCKET.toItem()
        }
    }

    val COBBLESTONE_GENERATOR_3 by buildSlimefunItem<MaterialGenerator>(Material.COBBLESTONE, 16, 360) {
        material = Material.SMOOTH_STONE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"VGc"
            +"WCL"
            +"cGV"
            'V' means VOID_BLOCK
            'G' means COBBLESTONE_GENERATOR_2
            'c' means MACHINE_CIRCUIT
            'W' means Material.WATER_BUCKET.toItem()
            'C' means COMPRESSED_COBBLESTONE_4
            'L' means Material.LAVA_BUCKET.toItem()
        }
    }

    val COBBLESTONE_GENERATOR_4 by buildSlimefunItem<MaterialGenerator>(Material.COBBLESTONE, 64, 800) {
        material = Material.SMOOTH_STONE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"IV  VI"
            +" VGGV "
            +" VGGV "
            +" VGGV "
            +" VGGV "
            +"IV  VI"
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
            'G' means COBBLESTONE_GENERATOR_3
        }
    }

    val VIRTUAL_FARM by buildSlimefunItem<VirtualFarm>(18, 300) {
        material = Material.GRASS_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"GGG"
            +"MHM"
            +"CBC"
            'G' means Material.GLASS.toItem()
            'M' means MAGSTEEL
            'H' means Material.DIAMOND_HOE.toItem()
            'C' means MACHINE_CIRCUIT
            'B' means Material.GRASS_BLOCK.toItem()
        }
    }

    val VIRTUAL_FARM_2 by buildSlimefunItem<VirtualFarm>(90, 60) {
        material = Material.CRIMSON_NYLIUM.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"GGG"
            +"MFM"
            +"COC"
            'G' means Material.GLASS.toItem()
            'M' means MAGNONIUM
            'F' means VIRTUAL_FARM
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val VIRTUAL_FARM_3 by buildSlimefunItem<VirtualFarm>(270, 30) {
        material = Material.WARPED_NYLIUM.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"VFV"
            +"OFO"
            +"VFV"
            'V' means VOID_BLOCK
            'F' means VIRTUAL_FARM_2
            'O' means MACHINE_CORE
        }
    }

    val VIRTUAL_FARM_4 by buildSlimefunItem<VirtualFarm>(1000, 10) {
        material = Material.END_STONE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"GGGGGG"
            +"G    G"
            +"G    G"
            +"GRRRRG"
            +"PAFFAP"
            +"PCOOCP"
            'G' means Material.GLASS.toItem()
            'R' means Material.GRASS_BLOCK.toItem()
            'P' means MACHINE_PLATE
            'A' means SlimefunItems.CROP_GROWTH_ACCELERATOR_2
            'F' means VIRTUAL_FARM_3
            'C' means INFINITY_MACHINE_CIRCUIT
            'O' means INFINITY_MACHINE_CORE
        }
    }

    val TREE_GROWER by buildSlimefunItem<TreeGrower>(36, 600) {
        material = Material.STRIPPED_OAK_WOOD.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"GGG"
            +"MPM"
            +"CFC"
            'G' means Material.GLASS.toItem()
            'M' means MAGSTEEL
            'P' means Material.PODZOL.toItem()
            'C' means MACHINE_CIRCUIT
            'F' means VIRTUAL_FARM
        }
    }

    val TREE_GROWER_2 by buildSlimefunItem<TreeGrower>(180, 120) {
        material = Material.STRIPPED_ACACIA_WOOD.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"GGG"
            +"MTM"
            +"COC"
            'G' means Material.GLASS.toItem()
            'M' means MAGSTEEL
            'T' means TREE_GROWER
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val TREE_GROWER_3 by buildSlimefunItem<TreeGrower>(540, 60) {
        material = Material.STRIPPED_CRIMSON_HYPHAE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"VTV"
            +"OTO"
            +"VTV"
            'V' means VOID_BLOCK
            'T' means TREE_GROWER_2
            'O' means MACHINE_CORE
        }
    }

    val TREE_GROWER_4 by buildSlimefunItem<TreeGrower>(2000, 12) {
        material = Material.STRIPPED_WARPED_HYPHAE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"GGGGGG"
            +"GA  AG"
            +"GT  TG"
            +"GA  AG"
            +"PppppP"
            +"PCOOCP"
            'G' means Material.GLASS.toItem()
            'A' means SlimefunItems.TREE_GROWTH_ACCELERATOR
            'T' means TREE_GROWER_3
            'P' means MACHINE_PLATE
            'p' means Material.PODZOL.toItem()
            'C' means INFINITY_MACHINE_CIRCUIT
            'O' means INFINITY_MACHINE_CORE
        }
    }

    val FLOWER_GROWER by buildSlimefunItem<FlowerGrower>(18, 300) {
        material = Material.LIME_STAINED_GLASS.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"GGG"
            +"MPM"
            +"CGC"
            'G' means Material.GLASS.toItem()
            'M' means MAGSTEEL
            'P' means Material.POPPY.toItem()
            'C' means MACHINE_CIRCUIT
            'G' means Material.GRASS_BLOCK.toItem()
        }
    }

    val FLOWER_GROWER_2 by buildSlimefunItem<FlowerGrower>(90, 60) {
        material = Material.LIME_STAINED_GLASS.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"GGG"
            +"EFE"
            +"COC"
            'G' means SlimefunItems.HARDENED_GLASS
            'E' means ENDER_ESSENCE
            'F' means FLOWER_GROWER
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val FLOWER_GROWER_3 by buildSlimefunItem<FlowerGrower>(270, 30) {
        material = Material.LIME_STAINED_GLASS.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"VFV"
            +"OFO"
            +"VFV"
            'V' means VOID_BLOCK
            'F' means FLOWER_GROWER_2
            'O' means MACHINE_CORE
        }
    }

    val FLOWER_GROWER_4 by buildSlimefunItem<FlowerGrower>(1000, 10) {
        material = Material.LIME_STAINED_GLASS.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"GGGGGG"
            +"G    G"
            +"G    G"
            +"GRRRRG"
            +"P FF P"
            +"PCOOCP"
            'G' means Material.GLASS.toItem()
            'R' means Material.GRASS_BLOCK.toItem()
            'P' means MACHINE_PLATE
            'F' means FLOWER_GROWER_3
            'C' means INFINITY_MACHINE_CIRCUIT
            'O' means INFINITY_MACHINE_CORE
        }
    }

    val VOID_HARVESTER by buildSlimefunItem<VoidHarvester>(1, 120) {
        material = Material.OBSIDIAN.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"TTT"
            +"PMP"
            +"COC"
            'T' means TITANIUM
            'P' means MACHINE_PLATE
            'M' means SlimefunItems.GEO_MINER
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val VOID_HARVESTER_2 by buildSlimefunItem<VoidHarvester>(8, 1200) {
        material = Material.OBSIDIAN.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"RRR"
            +"PHP"
            +"COC"
            'R' means SlimefunItems.REINFORCED_PLATE
            'P' means MACHINE_PLATE
            'H' means VOID_HARVESTER
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val VOID_HARVESTER_3 by buildSlimefunItem<VoidHarvester>(64, 12000) {
        material = Material.CRYING_OBSIDIAN.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"PPPPPP"
            +"MVVVVM"
            +"MVCCVM"
            +"MVHHVM"
            +"MVVVVM"
            +"PPPPPP"
            'P' means MACHINE_PLATE
            'M' means MAGNONIUM
            'V' means VOID_INGOT
            'C' means INFINITY_MACHINE_CIRCUIT
            'H' means VOID_HARVESTER_2
        }
    }

    val STONEWORKS_FACTORY by buildSlimefunItem<StoneworksFactory>(1, 200) {
        material = Material.BLAST_FURNACE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"PGP"
            +"FCR"
            +"PSP"
            'P' means MAGSTEEL_PLATE
            'G' means COBBLESTONE_GENERATOR
            'F' means SlimefunItems.ELECTRIC_FURNACE
            'C' means MACHINE_CIRCUIT
            'R' means SlimefunItems.ELECTRIC_ORE_GRINDER
            'S' means SlimefunItems.ELECTRIC_PRESS
        }
    }

    val STONEWORKS_FACTORY_2 by buildSlimefunItem<StoneworksFactory>(4, 600) {
        material = Material.BLAST_FURNACE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"PGP"
            +"FAR"
            +"CSC"
            'P' means MAGSTEEL_PLATE
            'G' means COBBLESTONE_GENERATOR_2
            'F' means SlimefunItems.ELECTRIC_FURNACE_2
            'A' means STONEWORKS_FACTORY
            'R' means SlimefunItems.ELECTRIC_ORE_GRINDER_2
            'C' means MACHINE_CIRCUIT
            'S' means SlimefunItems.ELECTRIC_PRESS_2
        }
    }

    val STONEWORKS_FACTORY_3 by buildSlimefunItem<StoneworksFactory>(16, 1800) {
        material = Material.BLAST_FURNACE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"PGV"
            +"FAR"
            +"VSC"
            'P' means MACHINE_PLATE
            'G' means COBBLESTONE_GENERATOR_3
            'V' means VOID_BLOCK
            'F' means SlimefunItems.ELECTRIC_FURNACE_3
            'A' means STONEWORKS_FACTORY_2
            'R' means SlimefunItems.ELECTRIC_ORE_GRINDER_3
            'S' means SlimefunItems.ELECTRIC_PRESS_2
            'C' means MACHINE_CIRCUIT
        }
    }

    val STONEWORKS_FACTORY_4 by buildSlimefunItem<StoneworksFactory>(64, 5400) {
        material = Material.BLAST_FURNACE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"IV  VI"
            +" FSSF "
            +"RVGGVR"
            +"EVCCVE"
            +"PCCCCP"
            +"IV  VI"
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
            'F' means SlimefunItems.ELECTRIC_FURNACE_3
            'S' means STONEWORKS_FACTORY_3
            'R' means SlimefunItems.ELECTRIC_ORE_GRINDER_3
            'G' means COBBLESTONE_GENERATOR_4
            'E' means SlimefunItems.ELECTRIC_PRESS_2
            'C' means MACHINE_CIRCUIT
            'P' means MACHINE_PLATE
        }
    }

    val SINGULARITY_CONSTRUCTOR by buildSlimefunItem<SingularityConstructor>(1, 120) {
        material = Material.QUARTZ_BRICKS.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"MMM"
            +"PRP"
            +"COC"
            'M' means MAGSTEEL
            'P' means MACHINE_PLATE
            'R' means SlimefunItems.CARBON_PRESS_3
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val SINGULARITY_CONSTRUCTOR_2 by buildSlimefunItem<SingularityConstructor>(64, 1200) {
        material = Material.CHISELED_QUARTZ_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +" PPPP "
            +" VCCV "
            +" VSSV "
            +" VSSV "
            +" IOOI "
            +"IIIIII"
            'P' means MACHINE_PLATE
            'V' means VOID_INGOT
            'C' means INFINITY_MACHINE_CIRCUIT
            'S' means SINGULARITY_CONSTRUCTOR
            'O' means INFINITY_MACHINE_CORE
            'I' means INFINITY_INGOT
        }
    }

    val RESOURCE_SYNTHESIZER by buildSlimefunItem<ResourceSynthesizer>(1_000_000) {
        material = Material.LODESTONE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"AAA"
            +"PFP"
            +"POP"
            'A' means ADAMANTITE
            'P' means MACHINE_PLATE
            'F' means SlimefunItems.REINFORCED_FURNACE
            'O' means MACHINE_CORE
        }
    }

    val OBSIDIAN_GENERATOR by buildSlimefunItem<MaterialGenerator>(Material.OBSIDIAN, 1, 240) {
        material = Material.SMOOTH_STONE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"FMF"
            +"DVD"
            +"CGC"
            'F' means SlimefunItems.FLUID_PUMP
            'M' means SlimefunItems.PROGRAMMABLE_ANDROID_MINER
            'D' means Material.DISPENSER.toItem()
            'V' means VOID_INGOT
            'C' means MACHINE_CIRCUIT
            'G' means COBBLESTONE_GENERATOR_2
        }
    }

    // TODO: more tiers of obsidian generator

    val EXTREME_FREEZER by buildSlimefunItem<ExtremeFreezer>(90) {
        material = Material.LIGHT_BLUE_CONCRETE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"FFF"
            +"WPW"
            +"COC"
            'F' means SlimefunItems.FREEZER_2
            'W' means Material.WATER_BUCKET.toItem()
            'P' means SlimefunItems.FLUID_PUMP
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val DUST_EXTRACTOR by buildSlimefunItem<DustExtractor>(200, 4, 2) {
        material = Material.FURNACE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"GPW"
            +"GPW"
            +"COC"
            'G' means SlimefunItems.ELECTRIC_ORE_GRINDER_2
            'P' means SlimefunItems.ELECTRIC_GOLD_PAN_3
            'W' means SlimefunItems.ELECTRIC_DUST_WASHER_3
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val DUST_EXTRACTOR_2 by buildSlimefunItem<DustExtractor>(800, 8, 4) {
        material = Material.FURNACE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"MDM"
            +"MDM"
            +"COC"
            'M' means MAGSTEEL_PLATE
            'D' means DUST_EXTRACTOR
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val DUST_EXTRACTOR_3 by buildSlimefunItem<DustExtractor>(2400, 32, 32) {
        material = Material.FURNACE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"VDV"
            +"PDP"
            +"POP"
            'V' means VOID_BLOCK
            'D' means DUST_EXTRACTOR_2
            'P' means MACHINE_PLATE
            'O' means MACHINE_CORE
        }
    }

    val DUST_EXTRACTOR_4 by buildSlimefunItem<DustExtractor>(7200, 64, 64) {
        material = Material.FURNACE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"V    V"
            +"VICCIV"
            +"VI  IV"
            +"VIDDIV"
            +"VI  IV"
            +"V    V"
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
            'C' means INFINITY_MACHINE_CIRCUIT
            'D' means DUST_EXTRACTOR_3
        }
    }

    val INGOT_FORMER by buildSlimefunItem<IngotFormer>(200, 8, 4) {
        material = Material.BLAST_FURNACE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"III"
            +"III"
            +"COC"
            'I' means SlimefunItems.ELECTRIC_INGOT_FACTORY_2
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val INGOT_FORMER_2 by buildSlimefunItem<IngotFormer>(800, 16, 8) {
        material = Material.BLAST_FURNACE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"PIP"
            +"PIP"
            +"COC"
            'P' means MAGSTEEL_PLATE
            'I' means INGOT_FORMER
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val INGOT_FORMER_3 by buildSlimefunItem<IngotFormer>(2400, 32, 16) {
        material = Material.BLAST_FURNACE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"VIV"
            +"PIP"
            +"POP"
            'V' means VOID_BLOCK
            'I' means INGOT_FORMER_2
            'P' means MACHINE_PLATE
            'O' means MACHINE_CORE
        }
    }

    val INGOT_FORMER_4 by buildSlimefunItem<IngotFormer>(7200, 64, 32) {
        material = Material.BLAST_FURNACE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"V    V"
            +"VICCIV"
            +"VI  IV"
            +"VIFFIV"
            +"VI  IV"
            +"V    V"
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
            'C' means INFINITY_MACHINE_CIRCUIT
            'F' means INGOT_FORMER_3
        }
    }

    val URANIUM_EXTRACTOR by buildSlimefunItem<UraniumExtractor>(200, 4, 1) {
        material = Material.LIME_CONCRETE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"RRR"
            +"PWA"
            +"COC"
            'R' means SlimefunItems.ELECTRIC_ORE_GRINDER_2
            'P' means SlimefunItems.ELECTRIC_GOLD_PAN_3
            'W' means SlimefunItems.ELECTRIC_DUST_WASHER_3
            'A' means SlimefunItems.ENHANCED_AUTO_CRAFTER
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val URANIUM_INGOT_EXTRACTOR by buildSlimefunItem<UraniumIngotExtractor>(800, 4, 1) {
        material = Material.GREEN_CONCRETE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"NEN"
            +"NUN"
            +"UUU"
            'N' means Material.NETHERITE_BLOCK.toItem()
            'E' means SlimefunItems.ENERGIZED_CAPACITOR
            'U' means URANIUM_EXTRACTOR
        }
    }

    val COBBLE_PRESS by buildSlimefunItem<CobblePress>(200) {
        material = Material.SMOOTH_STONE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"PCP"
            +"RRR"
            +"PCP"
            'P' means MACHINE_PLATE
            'C' means COMPRESSED_COBBLESTONE_3
            'R' means SlimefunItems.ELECTRIC_PRESS_2
        }
    }

    val DECOMPRESSOR by buildSlimefunItem<Decompressor>(60) {
        material = Material.TARGET.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"PPP"
            +"SES"
            +"COC"
            'P' means MAGSTEEL_PLATE
            'S' means Material.STICKY_PISTON.toItem()
            'E' means SlimefunItems.ELECTRIC_PRESS_2
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val QUARRY by buildSlimefunItem<Quarry>(300, 1, 0.15) {
        material = Material.CHISELED_SANDSTONE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"PAP"
            +"IGI"
            +"COC"
            'P' means MAGSTEEL_PLATE
            'A' means SlimefunItems.CARBONADO_EDGED_CAPACITOR
            'I' means Material.IRON_PICKAXE.toItem()
            'G' means SlimefunItems.GEO_MINER
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val QUARRY_2 by buildSlimefunItem<Quarry>(900, 2, 0.25) {
        material = Material.CHISELED_RED_SANDSTONE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"PAP"
            +"DQD"
            +"COC"
            'P' means MACHINE_PLATE
            'A' means SlimefunItems.ENERGIZED_CAPACITOR
            'D' means Material.DIAMOND_PICKAXE.toItem()
            'Q' means QUARRY
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val QUARRY_3 by buildSlimefunItem<Quarry>(3600, 4, 0.5) {
        material = Material.CHISELED_NETHER_BRICKS.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"VAV"
            +"DQD"
            +"COC"
            'V' means VOID_INGOT
            'A' means VOID_CAPACITOR
            'N' means Material.NETHERITE_PICKAXE.toItem()
            'Q' means QUARRY_2
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val QUARRY_4 by buildSlimefunItem<Quarry>(36000, 64, 1.0) {
        material = Material.CHISELED_POLISHED_BLACKSTONE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +" PPPP "
            +"PACCAP"
            +"PQOOQP"
            +"V II V"
            +"V II V"
            +"V II V"
            'V' means VOID_INGOT
            'I' means INFINITY_INGOT
            'P' means MACHINE_PLATE
            'A' means INFINITY_PICKAXE
            'C' means INFINITY_MACHINE_CIRCUIT
            'O' means INFINITY_MACHINE_CORE
            'Q' means QUARRY_3
        }
    }

    val GEO_QUARRY by buildSlimefunItem<GeoQuarry>(450, 400, 1) {
        material = Material.QUARTZ_BRICKS.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"PVP"
            +"VGV"
            +"PVP"
            'P' means MACHINE_PLATE
            'V' means VOID_INGOT
            'G' means ADVANCED_GEO_MINER
        }
    }

    val GEO_QUARRY_2 by buildSlimefunItem<GeoQuarry>(90_000, 120, 4) {
        material = Material.QUARTZ_BRICKS.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"I    I"
            +" VVVV "
            +"CVQQVC"
            +"CVQQVC"
            +" VVVV "
            +"I    I"
            'I' means INFINITY_INGOT
            'V' means VOID_INGOT
            'C' means INFINITY_MACHINE_CIRCUIT
            'Q' means GEO_QUARRY
        }
    }

    val GEAR_TRANSFORMER by buildSlimefunItem<GearTransformer>(12_000) {
        material = Material.EMERALD_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"PCP"
            +"CSC"
            +"PCP"
            'P' means MACHINE_PLATE
            'C' means MACHINE_CIRCUIT
            'S' means Material.SMITHING_TABLE.toItem()
        }
    }

    val SMITHING_TEMPLATE_RANDOMIZER by buildSlimefunItem<SmithingTemplateRandomizer>(1_000) {
        material = Material.LODESTONE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"VVV"
            +"VRV"
            +"VTV"
            'V' means VOID_INGOT
            'R' means RESOURCE_SYNTHESIZER
            'T' means UNKNOWN_SMITHING_TEMPLATE
        }
    }

    val ADVANCED_ANVIL by buildSlimefunItem<AdvancedAnvil>(100_000) {
        material = Material.SMITHING_TABLE.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"PPP"
            +"PAP"
            +"COC"
            'P' means MACHINE_PLATE
            'A' means Material.ANVIL.toItem()
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val INFINITY_WORKBENCH by buildSlimefunItem<InfinityWorkbench>(10_000_000) {
        material = Material.RESPAWN_ANCHOR.asMaterialType()
        itemGroup = IEItemGroups.MACHINES
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"VPV"
            +"CTC"
            +"VPV"
            'V' means VOID_INGOT
            'P' means MACHINE_PLATE
            'C' means SlimefunItems.ENERGIZED_CAPACITOR
            'T' means Material.CRAFTING_TABLE.toItem()
        }
    }
    //</editor-fold>

    //<editor-fold desc="Generators" defaultstate="collapsed">
    val HYDRO_GENERATOR by buildSlimefunItem<EnergyGenerator>(GeneratorType.HYDROELECTRIC, 5) {
        material = Material.PRISMARINE_WALL.asMaterialType()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"MCM"
            +"BEB"
            +"MCM"
            'M' means MAGSTEEL
            'C' means MACHINE_CIRCUIT
            'B' means Material.BUCKET.toItem()
            'E' means SlimefunItems.ELECTRO_MAGNET
        }
    }

    val HYDRO_GENERATOR_2 by buildSlimefunItem<EnergyGenerator>(GeneratorType.HYDROELECTRIC, 45) {
        material = Material.END_STONE_BRICK_WALL.asMaterialType()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"GCG"
            +"POP"
            +"GCG"
            'G' means HYDRO_GENERATOR
            'C' means MACHINE_CIRCUIT
            'P' means MAGSTEEL_PLATE
            'O' means MACHINE_CORE
        }
    }

    val GEOTHERMAL_GENERATOR by buildSlimefunItem<EnergyGenerator>(GeneratorType.GEOTHERMAL, 35) {
        material = Material.MAGMA_BLOCK.asMaterialType()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"PPP"
            +"LLL"
            +"COC"
            'P' means MAGSTEEL_PLATE
            'L' means SlimefunItems.LAVA_GENERATOR_2
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val GEOTHERMAL_GENERATOR_2 by buildSlimefunItem<EnergyGenerator>(GeneratorType.GEOTHERMAL, 210) {
        material = Material.SHROOMLIGHT.asMaterialType()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"GCG"
            +"POP"
            +"GCG"
            'G' means GEOTHERMAL_GENERATOR
            'C' means MACHINE_CIRCUIT
            'P' means MAGSTEEL_PLATE
            'O' means MACHINE_CORE
        }
    }

    val SOLAR_PANEL by buildSlimefunItem<EnergyGenerator>(GeneratorType.SOLAR, 10) {
        material = Material.BLUE_GLAZED_TERRACOTTA.asMaterialType()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"MPM"
            +"SSS"
            +"CCC"
            'M' means MAGSTEEL
            'P' means MAGSTEEL_PLATE
            'S' means SlimefunItems.SOLAR_PANEL
            'C' means MACHINE_CIRCUIT
        }
    }

    val SOLAR_PANEL_2 by buildSlimefunItem<EnergyGenerator>(GeneratorType.SOLAR, 150) {
        material = Material.RED_GLAZED_TERRACOTTA.asMaterialType()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"SSS"
            +"TGT"
            +"CCC"
            'S' means SOLAR_PANEL
            'T' means TITANIUM
            'G' means SlimefunItems.SOLAR_GENERATOR_4
            'C' means MACHINE_CIRCUIT
        }
    }

    val SOLAR_PANEL_3 by buildSlimefunItem<EnergyGenerator>(GeneratorType.SOLAR, 750) {
        material = Material.YELLOW_GLAZED_TERRACOTTA.asMaterialType()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"PPP"
            +"SSS"
            +"COC"
            'P' means MACHINE_PLATE
            'S' means SOLAR_PANEL_2
            'C' means MACHINE_CIRCUIT
            'O' means MACHINE_CORE
        }
    }

    val VOID_PANEL by buildSlimefunItem<EnergyGenerator>(GeneratorType.LUNAR, 3_000) {
        material = Material.LIGHT_GRAY_GLAZED_TERRACOTTA.asMaterialType()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"VVV"
            +"SSS"
            +"MMM"
            'V' means VOID_INGOT
            'S' means SOLAR_PANEL_3
            'M' means MAGNONIUM
        }
    }

    val INFINITY_PANEL by buildSlimefunItem<EnergyGenerator>(GeneratorType.INFINITY, 60_000) {
        material = Material.LIGHT_BLUE_GLAZED_TERRACOTTA.asMaterialType()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +"SSSSSS"
            +"SSSSSS"
            +"IIIIII"
            +"ICOOCI"
            +"IIIIII"
            +"PPPPPP"
            'S' means SOLAR_PANEL_3
            'I' means INFINITY_INGOT
            'C' means INFINITY_MACHINE_CIRCUIT
            'O' means INFINITY_MACHINE_CORE
            'P' means VOID_PANEL
        }
    }

    val INFINITY_REACTOR by buildSlimefunItem<InfinityReactor>(120_000) {
        material = Material.BEACON.asMaterialType()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +" IIII "
            +"IIVVII"
            +"IPPPPI"
            +"IPRRPI"
            +"IPPPPI"
            +"ICOOCI"
            'I' means INFINITY_INGOT
            'V' means VOID_INGOT
            'P' means MACHINE_PLATE
            'R' means ADVANCED_NETHER_STAR_REACTOR
            'C' means INFINITY_MACHINE_CIRCUIT
            'O' means INFINITY_MACHINE_CORE
        }
    }

    val INFINITY_SINGULARITY_REACTOR by buildSlimefunItem<InfinitySingularityReactor>(180_000) {
        material = Material.BEACON.asMaterialType()
        itemGroup = IEItemGroups.GENERATORS
        recipeType = IERecipeTypes.INFINITY_WORKBENCH
        recipe = buildRecipe(6) {
            +" S  S "
            +"IIIIII"
            +"P CC P"
            +"P RO P"
            +"IIIIII"
            +"      "
            'S' means INFINITY_SINGULARITY
            'I' means INFINITY_INGOT
            'P' means MACHINE_PLATE
            'R' means INFINITY_REACTOR
            'C' means INFINITY_MACHINE_CIRCUIT
            'O' means INFINITY_MACHINE_CORE
        }
    }
    //</editor-fold>

    //<editor-fold desc="Mob Simulation" defaultstate="collapsed">
    val MOB_SIMULATION_CHAMBER by buildSlimefunItem<MobSimulationChamber>(150) {
        material = Material.GILDED_BLACKSTONE.asMaterialType()
        itemGroup = IEItemGroups.MOB_SIMULATION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"MPM"
            +"CBC"
            +"MPM"
            'M' means MAGSTEEL_PLATE
            'P' means MACHINE_PLATE
            'C' means MACHINE_CIRCUIT
            'B' means SlimefunItems.PROGRAMMABLE_ANDROID_BUTCHER
        }
    }

    val MOB_DATA_INFUSER by buildSlimefunItem<MobDataInfuser>(20_000) {
        material = Material.LODESTONE.asMaterialType()
        itemGroup = IEItemGroups.MOB_SIMULATION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"CRC"
            +"ROR"
            +"CRC"
            'C' means MACHINE_CIRCUIT
            'R' means SlimefunItems.REINFORCED_ALLOY_INGOT
            'O' means MACHINE_CORE
        }
    }

    val MOB_DATA_CARD_EMPTY by buildSlimefunItem<SimpleMaterial> {
        material = Material.CHAINMAIL_CHESTPLATE.asMaterialType()
        itemGroup = IEItemGroups.MOB_SIMULATION
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = buildRecipe {
            +"MCM"
            +"SDE"
            +"MCM"
            'M' means SlimefunItems.MAGNESIUM_INGOT
            'C' means MACHINE_CIRCUIT
            'S' means SlimefunItems.SYNTHETIC_SAPPHIRE
            'D' means SlimefunItems.SYNTHETIC_DIAMOND
            'E' means SlimefunItems.SYNTHETIC_EMERALD
        }
    }
    //</editor-fold>

    //<editor-fold desc="Hidden" defaultstate="collapsed">
    val OSCILLATOR by buildSlimefunItem<Oscillator>(Material.AIR.toItem()) {
        material = Material.REDSTONE_TORCH.asMaterialType()
        itemGroup = IEItemGroups.HIDDEN
        recipeType = RecipeType.NULL
    }

    // register this to ensure the recipe output items are recognized as sf items
    val MOB_DATA_CARD by buildSlimefunItem<MobDataCard>(MobDataCardProps.EMPTY) {
        material = Material.LEATHER_CHESTPLATE.asMaterialType()
        itemGroup = IEItemGroups.HIDDEN
        recipeType = RecipeType.NULL
    }
    //</editor-fold>
}
