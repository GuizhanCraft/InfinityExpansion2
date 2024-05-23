package net.guizhanss.infinityexpansion2.implementation.items.materials

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.IERegistry
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import kotlin.math.ceil

class Singularity(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    private val defaultTotalProgress: Int,
    val ingredients: Map<ItemStack, Int>,
) : SimpleMaterial(itemGroup, itemStack, recipeType, recipe), RecipeDisplayItem {
    val totalProgress
        get() = ceil(defaultTotalProgress * InfinityExpansion2.configService.singularityCostMultiplier).toInt()

    override fun postRegister() {
        if (!isDisabled) {
            IERegistry.singularities.add(this)
        }
    }

    override fun getRecipeSectionLabel(p: Player) = InfinityExpansion2.integrationService.getLore(p, "info")

    override fun getDisplayRecipes(): List<ItemStack?> {
        val list = mutableListOf<ItemStack?>(null)
        list.add(GuiItems.totalProgress(totalProgress))

        ingredients.forEach { (item, amount) ->
            list.add(item)
            list.add(GuiItems.increaseProgress(amount).apply { this.amount = amount })
        }
        return list
    }
}
