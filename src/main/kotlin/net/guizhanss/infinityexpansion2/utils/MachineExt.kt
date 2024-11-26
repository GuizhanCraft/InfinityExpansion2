package net.guizhanss.infinityexpansion2.utils

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.utils.bukkitext.isAir
import org.bukkit.inventory.ItemStack

typealias RecipeInput = ItemStack
typealias RecipeOutput = Array<out ItemStack>
typealias Recipes = Map<RecipeInput, RecipeOutput>
typealias MutableRecipes = MutableMap<RecipeInput, RecipeOutput>

/**
 * Get the display recipe [List] for the given pair of a single item input recipe.
 */
fun Pair<RecipeInput, RecipeOutput>.toDisplayRecipe(): List<ItemStack> {
    val (input, output) = this
    val result = mutableListOf<ItemStack>()
    val size = output.size
    for (i in 0 until size) {
        result.add(input)
        result.add(output[i])
    }
    return result
}

fun BlockMenu.calculateItems(vararg slots: Int) = calculateItems(slots.map { getItemInSlot(it) })

fun calculateItems(items: List<ItemStack?>): Map<ItemStack, Int> {
    val result = mutableMapOf<ItemStack, Int>()
    items.forEach {
        if (it == null || it.isAir) return@forEach

        val itemTemplate = it.clone().apply { amount = 1 }

        result[itemTemplate] = result.getOrPut(itemTemplate) { 0 } + it.amount
    }
    return result
}

fun powerPerTickToSecond(powerPerTick: Int, tickRate: Int = 1) =
    (powerPerTick * 20.0 / (Slimefun.getTickerTask().tickRate * tickRate)).toInt()
