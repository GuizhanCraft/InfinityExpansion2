package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.ItemState
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingActionMachine
import net.guizhanss.infinityexpansion2.utils.bukkitext.withAmount
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.items.isSlimefunItem
import net.guizhanss.infinityexpansion2.utils.matches
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

class ResourceSynthesizer(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerUse: Int,
) : AbstractTickingActionMachine(
    itemGroup, itemStack, recipeType, recipe, MenuLayout.RESOURCE_SYNTHESIZER, energyPerUse
), InformationalRecipeDisplayItem {

    private val _recipes = mutableMapOf<Pair<String, String>, ItemStack>()

    val recipes: Map<Pair<String, String>, ItemStack> get() = _recipes

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        check(state == ItemState.UNREGISTERED) { "Cannot add recipes after the machine has been registered" }
        val recipes = InfinityExpansion2.configService.resourceSynthesizerRecipes
        recipes.forEach { recipe ->
            if (!recipe.inputs.first.isSlimefunItem()) return@forEach
            if (!recipe.inputs.second.isSlimefunItem()) return@forEach
            if (!recipe.output.first.isSlimefunItem()) return@forEach

            _recipes[recipe.inputs] =
                SlimefunItem.getById(recipe.output.first)!!.item.withAmount(recipe.output.second)
        }
    }

    override fun process(b: Block, menu: BlockMenu): Boolean {
        // check input
        val input1 = SlimefunItem.getByItem(menu.getItemInSlot(inputSlots[0]))
        val input2 = SlimefunItem.getByItem(menu.getItemInSlot(inputSlots[1]))

        if (input1 == null || input2 == null) {
            menu.setStatus { GuiItems.INVALID_INPUT }
            return false
        }

        val recipe = _recipes.entries.firstOrNull { it.key.matches(input1.id, input2.id) }

        if (recipe == null) {
            menu.setStatus { GuiItems.INVALID_INPUT }
            return false
        }

        // process
        val output = recipe.value.clone()
        if (menu.fits(output, *outputSlots)) {
            menu.setStatus { GuiItems.NO_ROOM }
            return false
        }

        menu.setStatus { GuiItems.PRODUCING }
        menu.consumeItem(inputSlots[0])
        menu.consumeItem(inputSlots[1])
        menu.pushItem(output, *outputSlots)

        return true
    }

    override fun getDefaultDisplayRecipes(): List<ItemStack?> {
        val result = mutableListOf<ItemStack?>()
        _recipes.forEach { (pair, output) ->
            val (input1, input2) = pair
            result.add(SlimefunItem.getById(input1)?.item)
            result.add(output)
            result.add(SlimefunItem.getById(input2)?.item)
            result.add(output)
        }
        return result
    }

    override fun getInformationalItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyConsumptionPerUse(getEnergyConsumptionPerAction()),
    )

    override fun getDividerItem() = GuiItems.RECIPES
}
