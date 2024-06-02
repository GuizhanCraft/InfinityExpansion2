package net.guizhanss.infinityexpansion2.core.attributes

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * An extended [RecipeDisplayItem] that provides additional informational items to display in the guide.
 */
interface InformationalRecipeDisplayItem : RecipeDisplayItem {
    /**
     * This list contains the default display recipes without informational items.
     */
    fun getDefaultDisplayRecipes(): List<ItemStack?> = listOf()

    /**
     * The divider item that is used between informational items and the default display recipes.
     */
    fun getDividerItem(): ItemStack? = null

    /**
     * This list contains all the informational items, which should not be displayed when a custom guide layout is used.
     */
    fun getInformationalItems(): List<ItemStack?> = listOf()

    override fun getRecipeSectionLabel(p: Player) = InfinityExpansion2.integrationService.getLore(p, "info")

    override fun getDisplayRecipes(): List<ItemStack?> {
        val result = ArrayList(getInformationalItems())
        if (result.size % 2 != 0) {
            result.add(null)
        }
        val divider = getDividerItem()
        if (divider != null) {
            result.addAll(arrayOf(divider))
        }
        result.addAll(getDefaultDisplayRecipes())
        return result
    }
}
