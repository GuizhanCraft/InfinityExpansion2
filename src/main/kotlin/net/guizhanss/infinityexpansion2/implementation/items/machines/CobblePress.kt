package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.implementation.IEItems
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class CobblePress(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
) : HopperMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick) {

    init {
        addRecipe(
            ItemStack(Material.COBBLESTONE, 64),
            arrayOf(SlimefunItemStack(IEItems.COMPRESSED_COBBLESTONE_1, 8))
        )
        addRecipe(
            SlimefunItemStack(IEItems.COMPRESSED_COBBLESTONE_1, 64),
            arrayOf(SlimefunItemStack(IEItems.COMPRESSED_COBBLESTONE_2, 8))
        )
        addRecipe(
            SlimefunItemStack(IEItems.COMPRESSED_COBBLESTONE_2, 64),
            arrayOf(SlimefunItemStack(IEItems.COMPRESSED_COBBLESTONE_3, 8))
        )
        addRecipe(
            SlimefunItemStack(IEItems.COMPRESSED_COBBLESTONE_3, 64),
            arrayOf(SlimefunItemStack(IEItems.COMPRESSED_COBBLESTONE_4, 8))
        )
        addRecipe(
            SlimefunItemStack(IEItems.COMPRESSED_COBBLESTONE_4, 64),
            arrayOf(SlimefunItemStack(IEItems.COMPRESSED_COBBLESTONE_5, 8))
        )
    }
}
