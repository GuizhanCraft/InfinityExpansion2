package net.guizhanss.infinityexpansion2.implementation.items.mobsim

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.core.IERegistry
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.core.recipes.MachineRecipe
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractCraftingMachine
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.inventory.ItemStack

class MobDataInfuser(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerUse: Int,
) : AbstractCraftingMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.CRAFTING_DEFAULT, energyPerUse),
    InformationalRecipeDisplayItem {

    override val recipes: List<MachineRecipe>
        get() = IERegistry.mobDataInfuserRecipes

    override fun getInformationalItems() = listOf(
        GuiItems.energyConsumptionPerUse(getEnergyConsumptionPerAction())
    )
}
