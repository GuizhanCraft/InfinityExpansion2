package net.guizhanss.infinityexpansion2.core.recipes

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper
import net.guizhanss.guizhanlib.kt.slimefun.debug.debugMessage
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.debug.DebugCase
import net.guizhanss.infinityexpansion2.utils.Debug
import net.guizhanss.infinityexpansion2.utils.items.isSimilar
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
    private val recipeValidItemCount = recipe.count { it != null }

    fun check(input: Array<out ItemStack?>, strict: Boolean = true): Boolean {
        Debug.log(DebugCase.MACHINE_RECIPE, "Checking recipe: ${recipe.debugMessage()}")
        Debug.log(DebugCase.MACHINE_RECIPE, "Output: $output")
        if (input.size != recipe.size || recipeValidItemCount != input.count { it != null }) {
            return false
        }
        Debug.log(DebugCase.MACHINE_RECIPE, "input size match")
        recipe.forEachIndexed { i, recipeItem ->
            // check if the recipe item is null and the input item is null
            // because canStack returns false for both null
            if (recipeItem == null && input[i] == null) {
                return@forEachIndexed
            }

            val similar = if (strict) ItemUtils.canStack(recipeItem, input[i]) else isSimilar(recipeItem, input[i])
            Debug.log(DebugCase.MACHINE_RECIPE, "Checking item $i: $recipeItem, ${input[i]}, similar: $similar")
            if (!similar || (recipeItem != null && recipeItem.amount > input[i]!!.amount)) {
                return false
            }
        }
        Debug.log(DebugCase.MACHINE_RECIPE, "All ingredients match")
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
