package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.guizhanlib.minecraft.utils.MinecraftVersionUtil
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class TreeGrower(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
    outputInterval: Int,
) : GrowingMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick, outputInterval) {

    init {
        addRecipe(
            Material.OAK_SAPLING.toItem(), arrayOf(
                Material.OAK_LEAVES.toItem(8),
                Material.OAK_LOG.toItem(6),
                Material.STICK.toItem(),
                Material.APPLE.toItem()
            )
        )

        addRecipe(
            Material.SPRUCE_SAPLING.toItem(), arrayOf(
                Material.SPRUCE_LEAVES.toItem(8),
                Material.SPRUCE_LOG.toItem(6),
                Material.STICK.toItem(2)
            )
        )

        addRecipe(
            Material.DARK_OAK_SAPLING.toItem(), arrayOf(
                Material.DARK_OAK_LEAVES.toItem(8),
                Material.DARK_OAK_LOG.toItem(6),
                Material.APPLE.toItem()
            )
        )

        addRecipe(
            Material.BIRCH_SAPLING.toItem(), arrayOf(
                Material.BIRCH_LEAVES.toItem(8),
                Material.BIRCH_LOG.toItem(6)
            )
        )

        addRecipe(
            Material.ACACIA_SAPLING.toItem(), arrayOf(
                Material.ACACIA_LEAVES.toItem(8),
                Material.ACACIA_LOG.toItem(6)
            )
        )

        addRecipe(
            Material.JUNGLE_SAPLING.toItem(), arrayOf(
                Material.JUNGLE_LEAVES.toItem(8),
                Material.JUNGLE_LOG.toItem(6),
                Material.COCOA_BEANS.toItem()
            )
        )

        addRecipe(
            Material.WARPED_FUNGUS.toItem(), arrayOf(
                Material.WARPED_HYPHAE.toItem(8),
                Material.WARPED_STEM.toItem(6),
                Material.SHROOMLIGHT.toItem()
            )
        )

        addRecipe(
            Material.CRIMSON_FUNGUS.toItem(), arrayOf(
                Material.CRIMSON_HYPHAE.toItem(8),
                Material.CRIMSON_STEM.toItem(6),
                Material.WEEPING_VINES.toItem()
            )
        )

        if (MinecraftVersionUtil.isAtLeast(19)) {
            addRecipe(
                Material.MANGROVE_PROPAGULE.toItem(), arrayOf(
                    Material.MANGROVE_LEAVES.toItem(8),
                    Material.MANGROVE_LOG.toItem(6),
                    Material.MANGROVE_ROOTS.toItem(4),
                    Material.MUDDY_MANGROVE_ROOTS.toItem(2)
                )
            )
        }

        if (MinecraftVersionUtil.isAtLeast(20)) {
            addRecipe(
                Material.CHERRY_SAPLING.toItem(), arrayOf(
                    Material.CHERRY_LEAVES.toItem(8),
                    Material.CHERRY_LOG.toItem(6),
                    Material.STICK.toItem()
                )
            )
        }
    }
}
