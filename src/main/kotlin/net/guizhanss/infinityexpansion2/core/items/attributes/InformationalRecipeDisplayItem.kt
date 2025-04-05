package net.guizhanss.infinityexpansion2.core.items.attributes

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.jetbrains.annotations.ApiStatus

/**
 * An extended [RecipeDisplayItem] that provides additional informational items to display in the guide.
 */
interface InformationalRecipeDisplayItem : RecipeDisplayItem {

    /**
     * This list contains the default display recipes without information items.
     */
    fun getDefaultDisplayRecipes(): List<ItemStack?> = listOf()

    /**
     * The divider item that is used between information items and the default display recipes.
     */
    fun getDividerItem(): ItemStack? = null

    /**
     * This list contains all the information items, which should not be displayed when a custom guide layout is used.
     */
    fun getInfoItems(): List<ItemStack?> = listOf()

    override fun getRecipeSectionLabel(p: Player) = InfinityExpansion2.integrationService.getLore(p, "info")

    /**
     * The default display recipe is combined with informational items and dividers.
     * This method should not be overridden.
     */
    @ApiStatus.NonExtendable
    override fun getDisplayRecipes(): List<ItemStack?> {
        val result = ArrayList(getInfoItems())
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
