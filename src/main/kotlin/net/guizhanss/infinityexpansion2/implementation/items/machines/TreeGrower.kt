package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
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
            ItemStack(Material.OAK_SAPLING), arrayOf(
                ItemStack(Material.OAK_LEAVES, 8),
                ItemStack(Material.OAK_LOG, 6),
                ItemStack(Material.STICK),
                ItemStack(Material.APPLE)
            )
        )

        addRecipe(
            ItemStack(Material.SPRUCE_SAPLING), arrayOf(
                ItemStack(Material.SPRUCE_LEAVES, 8),
                ItemStack(Material.SPRUCE_LOG, 6),
                ItemStack(Material.STICK, 2)
            )
        )

        addRecipe(
            ItemStack(Material.DARK_OAK_SAPLING), arrayOf(
                ItemStack(Material.DARK_OAK_LEAVES, 8),
                ItemStack(Material.DARK_OAK_LOG, 6),
                ItemStack(Material.APPLE)
            )
        )

        addRecipe(
            ItemStack(Material.BIRCH_SAPLING), arrayOf(
                ItemStack(Material.BIRCH_LEAVES, 8),
                ItemStack(Material.BIRCH_LOG, 6)
            )
        )

        addRecipe(
            ItemStack(Material.ACACIA_SAPLING), arrayOf(
                ItemStack(Material.ACACIA_LEAVES, 8),
                ItemStack(Material.ACACIA_LOG, 6)
            )
        )

        addRecipe(
            ItemStack(Material.JUNGLE_SAPLING), arrayOf(
                ItemStack(Material.JUNGLE_LEAVES, 8),
                ItemStack(Material.JUNGLE_LOG, 6),
                ItemStack(Material.COCOA_BEANS)
            )
        )

        addRecipe(
            ItemStack(Material.WARPED_FUNGUS), arrayOf(
                ItemStack(Material.WARPED_HYPHAE, 8),
                ItemStack(Material.WARPED_STEM, 6),
                ItemStack(Material.SHROOMLIGHT)
            )
        )

        addRecipe(
            ItemStack(Material.CRIMSON_FUNGUS), arrayOf(
                ItemStack(Material.CRIMSON_HYPHAE, 8),
                ItemStack(Material.CRIMSON_STEM, 6),
                ItemStack(Material.WEEPING_VINES)
            )
        )

        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_20)) {
            addRecipe(
                ItemStack(Material.CHERRY_SAPLING), arrayOf(
                    ItemStack(Material.CHERRY_LEAVES, 8),
                    ItemStack(Material.CHERRY_LOG, 6),
                    ItemStack(Material.STICK)
                )
            )
        }
    }
}
