package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.guizhanlib.minecraft.utils.compatibility.MaterialX
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class FlowerGrower(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
    outputInterval: Int,
) : GrowingMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick, outputInterval) {

    init {
        addRecipe(MaterialX.SHORT_GRASS.toItem(), MaterialX.SHORT_GRASS.toItem(4))
        addRecipe(Material.FERN.toItem(), Material.FERN.toItem(4))
        addRecipe(Material.DEAD_BUSH.toItem(), Material.DEAD_BUSH.toItem(4))
        addRecipe(Material.DANDELION.toItem(), Material.DANDELION.toItem(4))
        addRecipe(Material.POPPY.toItem(), Material.POPPY.toItem(4))
        addRecipe(Material.BLUE_ORCHID.toItem(), Material.BLUE_ORCHID.toItem(4))
        addRecipe(Material.ALLIUM.toItem(), Material.ALLIUM.toItem(4))
        addRecipe(Material.AZURE_BLUET.toItem(), Material.AZURE_BLUET.toItem(4))
        addRecipe(Material.RED_TULIP.toItem(), Material.RED_TULIP.toItem(4))
        addRecipe(Material.ORANGE_TULIP.toItem(), Material.ORANGE_TULIP.toItem(4))
        addRecipe(Material.WHITE_TULIP.toItem(), Material.WHITE_TULIP.toItem(4))
        addRecipe(Material.PINK_TULIP.toItem(), Material.PINK_TULIP.toItem(4))
        addRecipe(Material.OXEYE_DAISY.toItem(), Material.OXEYE_DAISY.toItem(4))
        addRecipe(Material.CORNFLOWER.toItem(), Material.CORNFLOWER.toItem(4))
        addRecipe(Material.LILY_OF_THE_VALLEY.toItem(), Material.LILY_OF_THE_VALLEY.toItem(4))
        addRecipe(Material.WITHER_ROSE.toItem(), Material.WITHER_ROSE.toItem(4))
        addRecipe(Material.PINK_PETALS.toItem(), Material.PINK_PETALS.toItem(4))
        addRecipe(Material.SPORE_BLOSSOM.toItem(), Material.SPORE_BLOSSOM.toItem(4))
        addRecipe(Material.WEEPING_VINES.toItem(), Material.WEEPING_VINES.toItem(4))
        addRecipe(Material.TWISTING_VINES.toItem(), Material.TWISTING_VINES.toItem(4))
        addRecipe(Material.VINE.toItem(), Material.VINE.toItem(4))
        addRecipe(Material.TALL_GRASS.toItem(), Material.TALL_GRASS.toItem(4))
        addRecipe(Material.LARGE_FERN.toItem(), Material.LARGE_FERN.toItem(4))
        addRecipe(Material.SUNFLOWER.toItem(), Material.SUNFLOWER.toItem(4))
        addRecipe(Material.LILAC.toItem(), Material.LILAC.toItem(4))
        addRecipe(Material.ROSE_BUSH.toItem(), Material.ROSE_BUSH.toItem(4))
        addRecipe(Material.PEONY.toItem(), Material.PEONY.toItem(4))
        addRecipe(Material.CHORUS_FLOWER.toItem(), Material.CHORUS_FLOWER.toItem(4))
        addRecipe(Material.GLOW_LICHEN.toItem(), Material.GLOW_LICHEN.toItem(4))
        addRecipe(Material.LILY_PAD.toItem(), Material.LILY_PAD.toItem(4))
    }
}
