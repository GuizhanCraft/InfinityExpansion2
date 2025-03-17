package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import net.guizhanss.guizhanlib.kt.minecraft.extensions.toItem
import net.guizhanss.guizhanlib.kt.slimefun.items.edit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ExtremeFreezer(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
) : HopperMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick) {

    init {
        addRecipe(Material.ICE.toItem(2), SlimefunItems.REACTOR_COOLANT_CELL.edit { amount(1) })
        addRecipe(Material.MAGMA_BLOCK.toItem(2), SlimefunItems.NETHER_ICE_COOLANT_CELL.edit { amount(1) })
    }
}
