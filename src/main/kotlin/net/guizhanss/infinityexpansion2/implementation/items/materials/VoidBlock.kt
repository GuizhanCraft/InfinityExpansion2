package net.guizhanss.infinityexpansion2.implementation.items.materials

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.WitherProofBlock
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.utils.bukkitext.withAmount
import net.guizhanss.infinityexpansion2.utils.items.builder.recipes.buildRecipe
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
                buildRecipe {
                    +"V  "
                    +"   "
                    +"   "
                    'V' means IEItems.VOID_BLOCK
                },
                IEItems.VOID_INGOT.withAmount(9),
            )
        }
    }
}
