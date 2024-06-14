@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.groups

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.core.items.ExtraItem
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * A [SubGroup] is an [ItemGroup] that is used to display Infinity Expansion's items.
 */
open class SubGroup(
    key: NamespacedKey,
    item: ItemStack
) : ItemGroup(key, item) {
    private val _extraItems: MutableList<ExtraItem> = mutableListOf()

    val extraItems: List<ExtraItem> get() = _extraItems

    fun addExtraItem(recipeType: RecipeType, input: Array<out ItemStack?>, output: ItemStack) {
        val item = ExtraItem(recipeType, input, output)
        _extraItems.add(item)
        item.register()
    }

    override fun isVisible(p: Player) = false

    fun getName() = item.itemMeta!!.displayName
}
