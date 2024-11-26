package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

/**
 * A [HopperMachine] that the recipe output is randomly selected from all [ItemStack]s.
 */
open class RandomHopperMachine(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
) : HopperMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick) {

    override fun process(b: Block, menu: BlockMenu): Boolean {
        for (slot in inputSlots) {
            val input = menu.getItemInSlot(slot) ?: continue
            recipes.forEach { (recipeInput, recipeOutput) ->
                if (SlimefunUtils.isItemSimilar(input, recipeInput, false, false)
                    && input.amount >= recipeInput.amount
                ) {
                    val output = recipeOutput.random().clone()

                    // must have at least one slot empty
                    if (!outputSlots.any { menu.getItemInSlot(it) == null }) {
                        menu.setStatus { GuiItems.NO_ROOM }
                        return false
                    }

                    menu.setStatus { GuiItems.PRODUCING }
                    menu.consumeItem(slot, recipeInput.amount)
                    menu.pushItem(output, *outputSlots)
                    return true
                }
            }
        }

        menu.setStatus { GuiItems.INVALID_INPUT }
        return false
    }
}
