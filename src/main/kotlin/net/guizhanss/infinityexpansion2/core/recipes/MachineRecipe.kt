package net.guizhanss.infinityexpansion2.core.recipes

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * The recipe class for crafting machines.
 */
class MachineRecipe private constructor(
    val recipe: Array<out ItemStack?>,
    val output: ItemStack,
) {

    private val sfOutput: SlimefunItem? = SlimefunItem.getByItem(output)

    fun check(input: Array<out ItemStack?>): Boolean {
        if (input.size != recipe.size) {
            return false
        }
        recipe.forEachIndexed { i, recipeItem ->
            val similar = ItemUtils.canStack(recipeItem, input[i])
            if (!similar || (recipeItem != null && recipeItem.amount > input[i]!!.amount)) {
                return false
            }
        }
        return true
    }

    /**
     * Check if output is valid for the player.
     */
    fun check(p: Player) =
        sfOutput == null || sfOutput.canUse(p, false)

    fun consume(input: Array<out ItemStack?>) {
        recipe.forEachIndexed { i, recipeItem ->
            if (recipeItem != null) {
                ItemUtils.consumeItem(input[i]!!, recipeItem.amount, true)
            }
        }
    }

    companion object {

        fun of(input: Array<out ItemStack?>, output: ItemStack) =
            MachineRecipe(ItemStackWrapper.wrapArray(input), output)
    }
}
