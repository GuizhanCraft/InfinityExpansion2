package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.ItemState
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.core.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingMachine
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

open class GrowingMachine(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    tickRate: Int,
    energyPerTick: Int,
) : AbstractTickingMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.SINGLE_INPUT, energyPerTick, tickRate),
    InformationalRecipeDisplayItem {

    private val _recipes = mutableMapOf<ItemStack, Array<ItemStack>>()

    val recipes: Map<ItemStack, Array<ItemStack>> get() = _recipes

    fun addRecipe(input: ItemStack, output: Array<ItemStack>): GrowingMachine {
        check(state == ItemState.UNREGISTERED) { "Cannot add recipes after the machine has been registered" }
        _recipes[input] = output
        return this
    }

    override fun process(b: Block, menu: BlockMenu): Boolean {
        val input = menu.getItemInSlot(inputSlots[0])
        if (input == null) {
            menu.setStatus(GuiItems.INVALID_INPUT)
            return false
        }

        val output = findRecipe(input)
        if (output == null) {
            menu.setStatus(GuiItems.INVALID_INPUT)
            return false
        }

        menu.setStatus(GuiItems.PRODUCING)
        if (shouldRun()) {
            for (item in output) {
                menu.pushItem(item.clone(), *outputSlots)
            }
        }
        return true
    }

    private fun findRecipe(item: ItemStack): Array<ItemStack>? {
        return _recipes.entries.find { (input, _) -> SlimefunUtils.isItemSimilar(item, input, false) }?.value
    }

    override fun getDefaultDisplayRecipes(): List<ItemStack> {
        val result = mutableListOf<ItemStack>()
        _recipes.forEach { (input, output) ->
            val size = output.size
            for (i in 0 until size) {
                result.add(input)
                result.add(output[i])
            }
        }
        return result
    }

    override fun getInformationalItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyConsumptionPerTick(getEnergyConsumptionPerTick()),
        GuiItems.RECIPES,
        GuiItems.RECIPES,
    )
}
