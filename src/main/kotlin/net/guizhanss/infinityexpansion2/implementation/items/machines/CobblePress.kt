package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import net.guizhanss.infinityexpansion2.utils.bukkitext.withAmount
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
            Material.COBBLESTONE.toItem(64),
            IEItems.COMPRESSED_COBBLESTONE_1.withAmount(8)
        )
        addRecipe(
            IEItems.COMPRESSED_COBBLESTONE_1.withAmount(64),
            IEItems.COMPRESSED_COBBLESTONE_2.withAmount(8)
        )
        addRecipe(
            IEItems.COMPRESSED_COBBLESTONE_2.withAmount(64),
            IEItems.COMPRESSED_COBBLESTONE_3.withAmount(8)
        )
        addRecipe(
            IEItems.COMPRESSED_COBBLESTONE_3.withAmount(64),
            IEItems.COMPRESSED_COBBLESTONE_4.withAmount(8)
        )
        addRecipe(
            IEItems.COMPRESSED_COBBLESTONE_4.withAmount(64),
            IEItems.COMPRESSED_COBBLESTONE_5.withAmount(8)
        )
    }
}
