package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class VirtualFarm(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
    outputInterval: Int,
) : GrowingMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick, outputInterval) {

    init {
        addRecipe(Material.WHEAT_SEEDS.toItem(), Material.WHEAT.toItem(2))
        addRecipe(Material.CARROT.toItem(), Material.CARROT.toItem(2))
        addRecipe(Material.POTATO.toItem(), Material.POTATO.toItem(2))
        addRecipe(Material.BEETROOT_SEEDS.toItem(), Material.BEETROOT.toItem(2))
        addRecipe(Material.PUMPKIN_SEEDS.toItem(), Material.PUMPKIN.toItem())
        addRecipe(Material.MELON_SEEDS.toItem(), Material.MELON.toItem())
        addRecipe(Material.SUGAR_CANE.toItem(), Material.SUGAR_CANE.toItem(2))
        addRecipe(Material.COCOA_BEANS.toItem(), Material.COCOA_BEANS.toItem(2))
        addRecipe(Material.CACTUS.toItem(), Material.CACTUS.toItem(2))
        addRecipe(Material.BAMBOO.toItem(), Material.BAMBOO.toItem(6))
        addRecipe(Material.CHORUS_FLOWER.toItem(), Material.CHORUS_FRUIT.toItem(6))
        addRecipe(Material.NETHER_WART.toItem(), Material.NETHER_WART.toItem(2))
        addRecipe(Material.SWEET_BERRIES.toItem(), Material.SWEET_BERRIES.toItem(2))
    }
}
