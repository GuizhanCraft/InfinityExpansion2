package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.core.IERegistry
import net.guizhanss.infinityexpansion2.core.items.attributes.CustomWikiItem
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.core.recipes.MachineRecipe
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractEnergyCraftingMachine
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.inventory.ItemStack

class InfinityWorkbench(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerUse: Int,
) : AbstractEnergyCraftingMachine(
    itemGroup,
    itemStack,
    recipeType,
    recipe,
    MenuLayout.INFINITY_WORKBENCH,
    energyPerUse
),
    InformationalRecipeDisplayItem, CustomWikiItem {

    override val wikiUrl = "machines/infinity-workbench"

    override val recipes: List<MachineRecipe>
        get() = IERegistry.infinityRecipes

    override val craftSlot = 25

    override fun getInfoItems() = listOf(
        GuiItems.energyConsumptionPerUse(getEnergyConsumptionPerAction())
    )

    // TODO: implement fast recipe filling

    companion object {

        private const val RECIPE_BOOK_SLOT = 7
    }
}
