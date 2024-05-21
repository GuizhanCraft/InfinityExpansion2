@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import net.guizhanss.infinityexpansion2.implementation.items.groups.IEItemGroups
import net.guizhanss.infinityexpansion2.implementation.items.materials.EnderEssence
import net.guizhanss.infinityexpansion2.implementation.items.materials.SimpleMaterial
import net.guizhanss.infinityexpansion2.implementation.items.materials.VoidBlock
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
        recipe = RecipeUtils.full(ItemStack(Material.COBBLESTONE))
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
            // TODO: IRON_SINGULARITY,
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
            // TODO: DIAMOND_SINGULARITY,
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
            // TODO: MAGNESIUM_SINGULARITY,
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
            // TODO: EARTH_SINGULARITY,
            MYTHRIL,
            // TODO: FORTUNE_SINGULARITY,
            // TODO: MAGIC_SINGULARITY,
            VOID_INGOT,
            // TODO: METAL_SINGULARITY,
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
            ItemStack(Material.STICK), ItemStack(Material.STRING), ItemStack(Material.STICK),
            ItemStack(Material.STICK), ItemStack(Material.STRING), ItemStack(Material.STICK),
            MAGSTEEL, MAGSTEEL, MAGSTEEL,
        )
    }

    val STRAINER_1 = buildSlimefunItem<Strainer>(30) {
        id = "STRAINER_1"
        material = MaterialType.Material(Material.FISHING_ROD)
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            ItemStack(Material.STICK), ItemStack(Material.STRING), ItemStack(Material.STICK),
            ItemStack(Material.STRING), ItemStack(Material.STICK), ItemStack(Material.STRING),
            ItemStack(Material.STICK), ItemStack(Material.STRING), ItemStack(Material.STICK),
        )
    }

    val STRAINER_2 = buildSlimefunItem<Strainer>(60) {
        id = "STRAINER_2"
        material = MaterialType.Material(Material.FISHING_ROD)
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            MAGSTEEL, ItemStack(Material.STRING), MAGSTEEL,
            ItemStack(Material.STRING), STRAINER_1, ItemStack(Material.STRING),
            MAGSTEEL, ItemStack(Material.STRING), MAGSTEEL,
        )
    }

    val STRAINER_3 = buildSlimefunItem<Strainer>(90) {
        id = "STRAINER_3"
        material = MaterialType.Material(Material.FISHING_ROD)
        itemGroup = IEItemGroups.TOOLS
        recipeType = RecipeType.ENHANCED_CRAFTING_TABLE
        recipe = arrayOf(
            SlimefunItems.REINFORCED_ALLOY_INGOT, ItemStack(Material.STRING), SlimefunItems.REINFORCED_ALLOY_INGOT,
            ItemStack(Material.STRING), STRAINER_2, ItemStack(Material.STRING),
            SlimefunItems.REINFORCED_ALLOY_INGOT, ItemStack(Material.STRING), SlimefunItems.REINFORCED_ALLOY_INGOT,
        )
    }
    // </editor-fold>
}
