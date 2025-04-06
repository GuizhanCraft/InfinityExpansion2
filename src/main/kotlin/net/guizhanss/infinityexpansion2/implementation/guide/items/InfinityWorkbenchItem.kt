@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.guide.items

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import net.guizhanss.guizhanlib.kt.slimefun.items.toItem
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.slimefunext.displayItem
import org.bukkit.entity.Player

/**
 * Display an item that is crafted in the Infinity Workbench.
 */
open class InfinityWorkbenchItem(sfItem: SlimefunItem) : NormalSlimefunItem(sfItem) {

    override val backgroundSlots = BACKGROUND_SLOT
    override val inputBorder = INPUT_BORDER
    override val outputBorder = OUTPUT_BORDER
    override val recipeTypeSlot = RECIPE_TYPE_SLOT
    override val backSlot = BACK_SLOT
    override val recipeArea = RECIPE_AREA
    override val outputSlot = OUTPUT_SLOT
    override val wikiSlot = WIKI_SLOT
    override val displayRecipeSlots = intArrayOf() // set the slots for display recipes to empty, disable displaying

    override fun initPage(
        p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu
    ) {
        super.initPage(p, profile, mode, menu)

        // recipe type
        menu.addItem(recipeTypeSlot, IEItems.INFINITY_WORKBENCH.toItem())
        menu.addMenuClickHandler(recipeTypeSlot) { _, _, _, _ ->
            displayItem(profile, IEItems.INFINITY_WORKBENCH, mode)
            false
        }

        if (sfItem is InformationalRecipeDisplayItem) {
            // info
            val infoItems = sfItem.getInfoItems()
            for (index in infoItems.indices) {
                if (index >= INFO_SLOTS.size) break
                menu.addItem(INFO_SLOTS[index], infoItems[index])
            }

            // display items
            if (displayRecipes.isNotEmpty()) {
                val displayRecipesSlot =
                    if (infoItems.size >= INFO_SLOTS.size) DISPLAY_RECIPE_SLOT else INFO_SLOTS[infoItems.size]

                menu.replaceExistingItem(displayRecipesSlot, GuiItems.DISPLAY_RECIPES)
                menu.addMenuClickHandler(displayRecipesSlot) { _, _, _, _ ->
                    SlimefunGuide.openItemGroup(profile, InfinityRecipeDisplayMenu(sfItem), mode, 1)
                    false
                }
            }
        }
    }

    override val displayRecipes = when {
        sfItem is InformationalRecipeDisplayItem && sfItem.getDefaultDisplayRecipes()
            .isNotEmpty() -> sfItem.getDefaultDisplayRecipes()

        else -> emptyList()
    }

    companion object {

        private val BACKGROUND_SLOT = intArrayOf(0, 2, 36, 37, 38, 45, 46, 47)
        private const val BACK_SLOT = 1
        private const val RECIPE_TYPE_SLOT = 10
        private val INPUT_BORDER = intArrayOf(9, 11)
        private val OUTPUT_BORDER = intArrayOf(18, 20, 27, 28, 29)
        private const val OUTPUT_SLOT = 19
        private val RECIPE_AREA = intArrayOf(
            // @formatter:off
            3, 4, 5, 6, 7, 8,
            12, 13, 14, 15, 16, 17,
            21, 22, 23, 24, 25, 26,
            30, 31, 32, 33, 34, 35,
            39, 40, 41, 42, 43, 44,
            48, 49, 50, 51, 52, 53,
            // @formatter:on
        )
        private val INFO_SLOTS = intArrayOf(36, 37, 38, 45, 46, 47)

        private const val DISPLAY_RECIPE_SLOT = 28
        private const val WIKI_SLOT = 11
    }
}
