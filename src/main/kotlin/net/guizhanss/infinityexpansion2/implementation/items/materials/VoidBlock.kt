package net.guizhanss.infinityexpansion2.implementation.items.materials

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.WitherProofBlock
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.utils.RecipeUtils
import org.bukkit.inventory.ItemStack

class VoidBlock(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>
) : WitherProofBlock(itemGroup, itemStack, recipeType, recipe) {
    override fun postRegister() {
        // decompress recipe
        // ItemStack isn't available yet, delay registering
        InfinityExpansion2.scheduler().runAsync {
            RecipeType.ENHANCED_CRAFTING_TABLE.register(
                RecipeUtils.expand(IEItems.VOID_BLOCK),
                CustomItemStack(IEItems.VOID_INGOT, 9),
            )
        }
    }
}
