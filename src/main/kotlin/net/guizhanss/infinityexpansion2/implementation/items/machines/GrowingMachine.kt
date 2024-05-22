package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.ItemState
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

open class GrowingMachine(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    tickRate: Int,
    energyPerTick: Int,
) : AbstractMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.SINGLE_INPUT), RecipeDisplayItem {
    private val tickRateSetting = IntRangeSetting(this, "tick-rate", 1, tickRate, 3600)
    private val energyPerTickSetting = IntRangeSetting(this, "energy-per-tick", 1, energyPerTick, 1_000_000_000)

    init {
        addItemSetting(tickRateSetting, energyPerTickSetting)
    }

    private val recipes = mutableMapOf<ItemStack, Array<ItemStack>>()

    fun getRecipes() = recipes.toMap()

    fun addRecipe(input: ItemStack, output: Array<ItemStack>): GrowingMachine {
        check(state == ItemState.UNREGISTERED) { "Cannot add recipes after the machine has been registered" }
        recipes[input] = output
        return this
    }

    override fun getEnergyConsumptionPerTick() = energyPerTickSetting.value

    override fun process(b: Block, menu: BlockMenu): Boolean {
        val input = menu.getItemInSlot(inputSlots[0])
        if (input == null) {
            if (menu.hasViewer()) {
                menu.replaceExistingItem(layout.statusSlot, GuiItems.INVALID_INPUT)
            }
            return false
        }

        val output = findRecipe(input)
        if (output == null) {
            if (menu.hasViewer()) {
                menu.replaceExistingItem(layout.statusSlot, GuiItems.INVALID_INPUT)
            }
            return false
        }

        if (menu.hasViewer()) {
            menu.replaceExistingItem(layout.statusSlot, GuiItems.PRODUCING)
        }
        if (InfinityExpansion2.sfTickCount() % tickRateSetting.value == 0) {
            for (item in output) {
                menu.pushItem(item.clone(), *outputSlots)
            }
        }
        return true
    }

    override fun getRecipeSectionLabel(p: Player) = InfinityExpansion2.integrationService.getLore(p, "info")

    override fun getDisplayRecipes(): List<ItemStack> {
        val result = mutableListOf(
            GuiItems.tickRate(tickRateSetting.value),
            GuiItems.energyConsumption(energyPerTickSetting.value),
            GuiItems.RECIPES,
            GuiItems.RECIPES
        )
        recipes.forEach { (input, output) ->
            val size = output.size
            for (i in 0 until size) {
                result.add(input)
                result.add(output[i])
            }
        }
        return result
    }

    private fun findRecipe(item: ItemStack): Array<ItemStack>? {
        return recipes.entries.find { (input, _) -> SlimefunUtils.isItemSimilar(item, input, false) }?.value
    }
}
