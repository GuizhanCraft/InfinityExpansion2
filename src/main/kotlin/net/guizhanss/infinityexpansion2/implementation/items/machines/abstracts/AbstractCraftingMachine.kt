package net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.core.recipes.MachineRecipe
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.toDebugMessage
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.logging.Level

/**
 * A crafting machine runs and consumes energy when crafting.
 */
abstract class AbstractCraftingMachine(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    layout: MenuLayout,
    energyPerUse: Int,
) : AbstractActionMachine(itemGroup, itemStack, recipeType, recipe, layout, energyPerUse) {

    // the subclass can choose to override this property to provide pre-defined recipes instead of adding them one by one
    open val recipes: List<MachineRecipe>
        get() = _recipes

    private val _recipes = mutableListOf<MachineRecipe>()

    open val craftSlot: Int = 23

    open val craftButton: ItemStack = GuiItems.CRAFT

    override fun setup(preset: BlockMenuPreset) {
        super.setup(preset)

        preset.addItem(craftSlot, craftButton)
    }

    override fun onNewInstance(menu: BlockMenu, b: Block) {
        super.onNewInstance(menu, b)

        menu.addMenuClickHandler(craftSlot) { p, _, _, _ ->
            if (getCharge(menu.location) < getEnergyConsumptionPerAction()) {
                menu.setStatus { GuiItems.NO_POWER }
            } else if (craft(menu, p)) {
                removeCharge(menu.location, getEnergyConsumptionPerAction())
            }
            false
        }
    }

    fun addRecipe(input: Array<ItemStack?>, output: ItemStack) {
        _recipes.add(MachineRecipe.of(input, output))
    }

    private fun craft(menu: BlockMenu, p: Player): Boolean {
        val input = Array(inputSlots.size) { index -> menu.getItemInSlot(inputSlots[index]) }
        InfinityExpansion2.debug("crafting start, input: ${input.toDebugMessage()}")

        val recipe = getRecipe(input)
        if (recipe == null || !recipe.check(p)) {
            menu.setStatus { GuiItems.INVALID_INPUT }
            InfinityExpansion2.integrationService.sendMessage(p, "crafting.invalid-input")
            return false
        }

        val output = recipe.output.clone()
        if (!menu.fits(output, *outputSlots)) {
            menu.setStatus { GuiItems.NO_ROOM }
            return false
        }

        recipe.consume(input)
        menu.pushItem(output, *outputSlots)
        menu.setStatus { GuiItems.SUCCESS }
        try {
            onSuccessfulCraft(menu, output, p)
        } catch (e: Exception) {
            InfinityExpansion2.log(Level.SEVERE, e, "An error occurred while processing a successful craft")
        }
        return true
    }

    /**
     * Override this method to add custom behavior when a craft is successful.
     * This method is called after the output is pushed to the output slots.
     */
    open fun onSuccessfulCraft(menu: BlockMenu, output: ItemStack, p: Player) {
        InfinityExpansion2.integrationService.sendMessage(
            p,
            "crafting.success",
            InfinityExpansion2.integrationService.getTranslatedItemName(p, output)
        )
    }

    private fun getRecipe(input: Array<ItemStack?>): MachineRecipe? {
        val inputSnapshot = ItemStackWrapper.wrapArray(input)
        InfinityExpansion2.debug("recipe count: ${recipes.size}")
        recipes.forEach { recipe ->
            if (recipe.check(inputSnapshot)) {
                return recipe
            }
        }
        return null
    }

}
