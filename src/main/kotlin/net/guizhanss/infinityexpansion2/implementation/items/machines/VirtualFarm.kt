package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class VirtualFarm(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
    tickRate: Int,
) : GrowingMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick, tickRate) {
    init {
        addRecipe(ItemStack(Material.WHEAT_SEEDS), arrayOf(ItemStack(Material.WHEAT, 2)))
        addRecipe(ItemStack(Material.CARROT), arrayOf(ItemStack(Material.CARROT, 2)))
        addRecipe(ItemStack(Material.POTATO), arrayOf(ItemStack(Material.POTATO, 2)))
        addRecipe(ItemStack(Material.BEETROOT_SEEDS), arrayOf(ItemStack(Material.BEETROOT, 2)))
        addRecipe(ItemStack(Material.PUMPKIN_SEEDS), arrayOf(ItemStack(Material.PUMPKIN)))
        addRecipe(ItemStack(Material.MELON_SEEDS), arrayOf(ItemStack(Material.MELON)))
        addRecipe(ItemStack(Material.SUGAR_CANE), arrayOf(ItemStack(Material.SUGAR_CANE, 2)))
        addRecipe(ItemStack(Material.COCOA_BEANS), arrayOf(ItemStack(Material.COCOA_BEANS, 2)))
        addRecipe(ItemStack(Material.CACTUS), arrayOf(ItemStack(Material.CACTUS, 2)))
        addRecipe(ItemStack(Material.BAMBOO), arrayOf(ItemStack(Material.BAMBOO, 6)))
        addRecipe(ItemStack(Material.CHORUS_FLOWER), arrayOf(ItemStack(Material.CHORUS_FRUIT, 6)))
        addRecipe(ItemStack(Material.NETHER_WART), arrayOf(ItemStack(Material.NETHER_WART, 2)))
    }
}
