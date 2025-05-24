package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import net.guizhanss.guizhanlib.kt.minecraft.extensions.toItem
import net.guizhanss.guizhanlib.kt.slimefun.items.edit
import net.guizhanss.guizhanlib.kt.slimefun.items.toItem
import net.guizhanss.infinityexpansion2.core.items.attributes.CustomWikiItem
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.HopperMachine
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class Decompressor(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
) : HopperMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick), CustomWikiItem {

    override val wikiUrl = "machines/decompressor"

    init {
        addRecipe(Material.NETHERITE_BLOCK.toItem(), Material.NETHERITE_INGOT.toItem(9))
        addRecipe(Material.EMERALD_BLOCK.toItem(), Material.EMERALD.toItem(9))
        addRecipe(Material.DIAMOND_BLOCK.toItem(), Material.DIAMOND.toItem(9))
        addRecipe(Material.GOLD_BLOCK.toItem(), Material.GOLD_INGOT.toItem(9))
        addRecipe(Material.IRON_BLOCK.toItem(), Material.IRON_INGOT.toItem(9))
        addRecipe(Material.COPPER_BLOCK.toItem(), SlimefunItems.COPPER_INGOT.edit { amount(9) })
        addRecipe(Material.LAPIS_BLOCK.toItem(), Material.LAPIS_LAZULI.toItem(9))
        addRecipe(Material.REDSTONE_BLOCK.toItem(), Material.REDSTONE.toItem(9))
        addRecipe(Material.COAL_BLOCK.toItem(), Material.COAL.toItem(9))
        addRecipe(Material.QUARTZ_BLOCK.toItem(), Material.QUARTZ.toItem(4))
        addRecipe(IEItems.COMPRESSED_COBBLESTONE_5.toItem(), IEItems.COMPRESSED_COBBLESTONE_4.edit { amount(8) })
        addRecipe(IEItems.COMPRESSED_COBBLESTONE_4.toItem(), IEItems.COMPRESSED_COBBLESTONE_3.edit { amount(8) })
        addRecipe(IEItems.COMPRESSED_COBBLESTONE_3.toItem(), IEItems.COMPRESSED_COBBLESTONE_2.edit { amount(8) })
        addRecipe(IEItems.COMPRESSED_COBBLESTONE_2.toItem(), IEItems.COMPRESSED_COBBLESTONE_1.edit { amount(8) })
        addRecipe(IEItems.COMPRESSED_COBBLESTONE_1.toItem(), Material.COBBLESTONE.toItem(8))
    }
}
