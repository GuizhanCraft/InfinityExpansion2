@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.guide.items

import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.implementation.items.materials.Singularity
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.slimefunext.displayItem
import net.guizhanss.infinityexpansion2.utils.slimefunext.getBackButton
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * Displays the recipe of a [Singularity].
 */
open class SingularityItem(private val singularity: Singularity) : NormalSlimefunItem(singularity) {

    override val backSlot = BACK_SLOT
    override val displayRecipeSlots = RECIPE_DISPLAY_SLOTS
    override val displayRecipePrev = PAGE_PREV
    override val displayRecipeNext = PAGE_NEXT

    override fun initPage(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu) {
        val guide = Slimefun.getRegistry().getSlimefunGuide(mode)

        // backgrounds
        BACKGROUND_SLOTS.forEach {
            menu.addItem(it, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler())
        }

        // recipe type
        menu.addItem(RECIPE_TYPE_SLOT, sfItem.recipeType.getItem(p)) { _, _, _, _ ->
            displayItem(profile, IEItems.SINGULARITY_CONSTRUCTOR, mode)
            false
        }

        // back
        menu.addItem(backSlot, getBackButton(p)) { _, _, _, action ->
            if (action.isShiftClicked) {
                guide.openMainMenu(profile, profile.guideHistory.mainMenuPage)
            } else {
                profile.guideHistory.goBack(guide)
            }
            false
        }

        // total progress
        menu.addItem(
            TOTAL_PROGRESS_SLOT,
            GuiItems.totalProgress(singularity.totalProgress),
            ChestMenuUtils.getEmptyClickHandler()
        )

        // output
        menu.addItem(OUTPUT_SLOT, sfItem.item.clone(), ChestMenuUtils.getEmptyClickHandler())

        // ingredients
        menu.addItem(RECIPE_DISPLAY_SLOTS.last(), null)
    }

    override val displayRecipes: List<ItemStack?>
        get() = singularity.ingredients.flatMap { (ingredient, progress) ->
            listOf(
                ingredient,
                GuiItems.increaseProgress(progress).apply { this.amount = progress }
            )
        }

    companion object {

        private val BACKGROUND_SLOTS = intArrayOf(9, 10, 11, 12, 13, 14, 15, 16, 17)
        private const val BACK_SLOT = 0
        private const val RECIPE_TYPE_SLOT = 2
        private const val TOTAL_PROGRESS_SLOT = 4
        private const val OUTPUT_SLOT = 6
        private const val PAGE_PREV = 10
        private const val PAGE_NEXT = 16
        private val RECIPE_DISPLAY_SLOTS = intArrayOf(
            18, 27, 19, 28, 20, 29, 21, 30, 22, 31, 23, 32, 24, 33, 25, 34, 26, 35
        )
    }
}
