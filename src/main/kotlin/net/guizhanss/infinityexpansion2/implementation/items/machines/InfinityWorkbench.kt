package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.core.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractActionMachine
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

class InfinityWorkbench(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerUse: Int,
) : AbstractActionMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.INFINITY_WORKBENCH, energyPerUse),
    InformationalRecipeDisplayItem {

    override fun onNewInstance(menu: BlockMenu, b: Block) {
        // TODO
    }

    override fun getInformationalItems() = listOf(
        GuiItems.energyConsumptionPerUse(getEnergyConsumptionPerAction())
    )

    companion object {
        private const val RECIPE_BOOK_SLOT = 7
        private val RECIPE_AREA = arrayOf(
            // @formatter:off
            0, 1, 2, 3, 4, 5,
            9, 10, 11, 12, 13, 14,
            18, 19, 20, 21, 22, 23,
            27, 28, 29, 30, 31, 32,
            36, 37, 38, 39, 40, 41,
            45, 46, 47, 48, 49, 50
            // @formatter:on
        )
    }
}
