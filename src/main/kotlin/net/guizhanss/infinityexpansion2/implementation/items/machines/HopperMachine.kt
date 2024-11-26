package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.ItemState
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.libraries.dough.inventory.InvUtils
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingMachine
import net.guizhanss.infinityexpansion2.utils.MutableRecipes
import net.guizhanss.infinityexpansion2.utils.RecipeInput
import net.guizhanss.infinityexpansion2.utils.RecipeOutput
import net.guizhanss.infinityexpansion2.utils.Recipes
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.toDisplayRecipe
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

/**
 * A hopper like GUI machine that has 7 input slots and output slots.
 */
open class HopperMachine(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
) : AbstractTickingMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.HOPPER, energyPerTick),
    InformationalRecipeDisplayItem {

    private val _recipes: MutableRecipes = mutableMapOf()

    val recipes: Recipes get() = _recipes

    fun addRecipe(input: RecipeInput, output: RecipeOutput) {
        require(output.isNotEmpty()) { "Recipe output cannot be empty" }
        check(state == ItemState.UNREGISTERED) { "Cannot add recipes after the machine has been registered" }
        _recipes[input] = output
    }

    @JvmName("addRecipeVararg")
    fun addRecipe(input: RecipeInput, vararg output: ItemStack) {
        addRecipe(input, output)
    }

    override fun process(b: Block, menu: BlockMenu): Boolean {
        for (slot in inputSlots) {
            val input = menu.getItemInSlot(slot) ?: continue
            recipes.forEach { (recipeInput, recipeOutput) ->
                if (SlimefunUtils.isItemSimilar(input, recipeInput, false, false)
                    && input.amount >= recipeInput.amount
                ) {
                    if (!InvUtils.fitAll(menu.toInventory(), recipeOutput, *outputSlots)) {
                        menu.setStatus { GuiItems.NO_ROOM }
                        return false
                    }

                    recipeOutput.forEach { menu.pushItem(it.clone(), *outputSlots) }
                    menu.consumeItem(slot, recipeInput.amount)
                    menu.setStatus { GuiItems.PRODUCING }
                    return true
                }
            }
        }

        menu.setStatus { GuiItems.INVALID_INPUT }
        return false
    }

    override fun getDefaultDisplayRecipes() = _recipes.flatMap { it.toPair().toDisplayRecipe() }

    override fun getInformationalItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyConsumptionPerTick(getEnergyConsumptionPerTick()),
    )

    override fun getDividerItem() = GuiItems.RECIPES
}
