@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.guide.items

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.utils.slimefunext.getBackButton
import org.bukkit.entity.Player

/**
 * A special menu that displays recipes of [InfinityWorkbenchItem].
 */
open class InfinityRecipeDisplayMenu(sfItem: SlimefunItem) : NormalSlimefunItem(sfItem) {

    override val displayRecipePrev = PAGE_PREV
    override val displayRecipeNext = PAGE_NEXT
    override val displayRecipeSlots = DISPLAY_RECIPE_SLOTS

    override fun initPage(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu) {
        val guide = Slimefun.getRegistry().getSlimefunGuide(mode)

        // background
        BACKGROUND_SLOTS.forEach { slot ->
            menu.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler())
        }

        // back
        menu.addItem(BACK_SLOT, getBackButton(p)) { _, _, _, action ->
            if (action.isShiftClicked) {
                guide.openMainMenu(profile, profile.guideHistory.mainMenuPage)
            } else {
                profile.guideHistory.goBack(guide)
            }
            false
        }

        // info slots
        if (sfItem is InformationalRecipeDisplayItem) {
            sfItem.getInfoItems().forEachIndexed { idx, item ->
                if (idx >= INFO_SLOTS.size) return@forEachIndexed
                menu.addItem(INFO_SLOTS[idx], item, ChestMenuUtils.getEmptyClickHandler())
            }
        }
    }

    override val displayRecipes = when {
        sfItem is InformationalRecipeDisplayItem && sfItem.getDefaultDisplayRecipes()
            .isNotEmpty() -> sfItem.getDefaultDisplayRecipes()

        sfItem is RecipeDisplayItem && sfItem.displayRecipes.isNotEmpty() -> sfItem.displayRecipes

        else -> emptyList()
    }

    companion object {

        private const val BACK_SLOT = 0
        private val BACKGROUND_SLOTS = intArrayOf(
            1, 2, 3, 4, 5, 6, 7, 8,
            27, 28, 29, 30, 31, 32, 33, 34, 35,
        )
        private val INFO_SLOTS = intArrayOf(
            2, 3, 4, 5, 6, 7, 8,
        )
        private val DISPLAY_RECIPE_SLOTS = intArrayOf(
            9, 18, 10, 19, 11, 20, 12, 21, 13, 22, 14, 23, 15, 24, 16, 25, 17, 26,
        )
        private const val PAGE_PREV = 28
        private const val PAGE_NEXT = 34
    }
}
