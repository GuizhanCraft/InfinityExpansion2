package net.guizhanss.infinityexpansion2.implementation.items.materials

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.IERegistry
import net.guizhanss.infinityexpansion2.core.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import kotlin.math.ceil

class Singularity(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    defaultTotalProgress: Int,
    val ingredients: Map<ItemStack, Int>,
) : SimpleMaterial(itemGroup, itemStack, recipeType, recipe), InformationalRecipeDisplayItem {
    private val defaultProgressSetting = IntRangeSetting(this, "default-progress", 1, defaultTotalProgress, 1_000_000)

    init {
        addItemSetting(defaultProgressSetting)
    }

    val totalProgress
        get() = ceil(defaultProgressSetting.value * InfinityExpansion2.configService.singularityCostMultiplier).toInt()

    override fun postRegister() {
        if (!isDisabled) {
            IERegistry.singularities.add(this)
            ingredients.entries.forEach { (ingredient, _) ->
                IERegistry.singularityIngredientMap[ingredient] = this
            }
        }
    }

    override fun getRecipeSectionLabel(p: Player) = InfinityExpansion2.integrationService.getLore(p, "info")

    override fun getInformationalItems() = listOf(null, GuiItems.totalProgress(totalProgress))

    override fun getDefaultDisplayRecipes(): List<ItemStack?> {
        val list = mutableListOf<ItemStack?>()

        ingredients.flatMap { (item, amount) ->
            listOf(item, GuiItems.increaseProgress(amount).apply { this.amount = amount })
        }
        return list
    }
}
