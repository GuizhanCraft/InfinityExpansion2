package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.implementation.IEItems
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class Decompressor(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
) : HopperMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick) {

    init {
        addRecipe(ItemStack(Material.NETHERITE_BLOCK), arrayOf(ItemStack(Material.NETHERITE_INGOT, 9)))
        addRecipe(ItemStack(Material.EMERALD_BLOCK), arrayOf(ItemStack(Material.EMERALD, 9)))
        addRecipe(ItemStack(Material.DIAMOND_BLOCK), arrayOf(ItemStack(Material.DIAMOND, 9)))
        addRecipe(ItemStack(Material.GOLD_BLOCK), arrayOf(ItemStack(Material.GOLD_INGOT, 9)))
        addRecipe(ItemStack(Material.IRON_BLOCK), arrayOf(ItemStack(Material.IRON_INGOT, 9)))
        addRecipe(ItemStack(Material.LAPIS_BLOCK), arrayOf(ItemStack(Material.LAPIS_LAZULI, 9)))
        addRecipe(ItemStack(Material.REDSTONE_BLOCK), arrayOf(ItemStack(Material.REDSTONE, 9)))
        addRecipe(ItemStack(Material.COAL_BLOCK), arrayOf(ItemStack(Material.COAL, 9)))
        addRecipe(ItemStack(Material.QUARTZ_BLOCK), arrayOf(ItemStack(Material.QUARTZ, 4)))
        addRecipe(IEItems.COMPRESSED_COBBLESTONE_5, arrayOf(SlimefunItemStack(IEItems.COMPRESSED_COBBLESTONE_4, 8)))
        addRecipe(IEItems.COMPRESSED_COBBLESTONE_4, arrayOf(SlimefunItemStack(IEItems.COMPRESSED_COBBLESTONE_3, 8)))
        addRecipe(IEItems.COMPRESSED_COBBLESTONE_3, arrayOf(SlimefunItemStack(IEItems.COMPRESSED_COBBLESTONE_2, 8)))
        addRecipe(IEItems.COMPRESSED_COBBLESTONE_2, arrayOf(SlimefunItemStack(IEItems.COMPRESSED_COBBLESTONE_1, 8)))
        addRecipe(IEItems.COMPRESSED_COBBLESTONE_1, arrayOf(ItemStack(Material.COBBLESTONE, 8)))
    }
}
