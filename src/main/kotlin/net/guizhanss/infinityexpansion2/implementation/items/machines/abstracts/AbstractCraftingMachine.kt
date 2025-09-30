package net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import net.guizhanss.guizhanlib.kt.slimefun.debug.debugMessage
import net.guizhanss.guizhanlib.slimefun.machines.MenuBlock
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.core.recipes.MachineRecipe
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.logging.Level

/**
 * A crafting machine. The variant that uses energy is [AbstractEnergyCraftingMachine].
 */
abstract class AbstractCraftingMachine(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    protected val layout: MenuLayout,
) : MenuBlock(itemGroup, itemStack, recipeType, recipe) {

    // the subclass can choose to override this property to provide pre-defined recipes instead of adding them one by one
    open val recipes: List<MachineRecipe>
        get() = _recipes

    private val _recipes = mutableListOf<MachineRecipe>()

    fun addRecipe(input: Array<ItemStack?>, output: ItemStack) {
        _recipes.add(MachineRecipe.of(input, output))
    }

    open val craftSlot: Int = 23

    open val craftButton: ItemStack = GuiItems.CRAFT

    open val strictIngredientCheck: Boolean = true

    override fun setup(preset: BlockMenuPreset) {
        layout.setupPreset(preset)

        preset.drawBackground(craftButton, intArrayOf(craftSlot))
    }

    override fun onNewInstance(menu: BlockMenu, b: Block) {
        menu.addMenuClickHandler(craftSlot) { p, _, _, _ ->
            craft(menu, p)
            false
        }
    }

    override fun getInputSlots() = layout.inputSlots

    override fun getOutputSlots() = layout.outputSlots

    protected open fun craft(menu: BlockMenu, p: Player) {
        val input = Array(inputSlots.size) { index -> menu.getItemInSlot(inputSlots[index]) }

        val recipe = getMatchingRecipe(input)
        if (recipe == null || !recipe.check(p)) {
            menu.setStatus { GuiItems.INVALID_INPUT }
            InfinityExpansion2.integrationService.sendMessage(p, "crafting.invalid-input")
            return
        }

        val output = recipe.output.clone()
        if (!menu.fits(output, *outputSlots)) {
            menu.setStatus { GuiItems.NO_ROOM }
            return
        }

        recipe.consume(input)
        menu.pushItem(output, *outputSlots)
        menu.setStatus { GuiItems.SUCCESS }
        try {
            onSuccessfulCraft(menu, output, p)
        } catch (e: Exception) {
            InfinityExpansion2.log(Level.SEVERE, e, "An error occurred while processing a successful craft")
        }
        return
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

    private fun getMatchingRecipe(input: Array<ItemStack?>): MachineRecipe? {
        val inputSnapshot = ItemStackWrapper.wrapArray(input)
        recipes.forEach { recipe ->
            if (recipe.check(inputSnapshot, strictIngredientCheck)) {
                return recipe
            }
        }
        return null
    }

    protected fun BlockMenu.setStatus(itemStack: () -> ItemStack) {
        if (hasViewer()) {
            replaceExistingItem(layout.statusSlot, itemStack())
        }
    }
}
