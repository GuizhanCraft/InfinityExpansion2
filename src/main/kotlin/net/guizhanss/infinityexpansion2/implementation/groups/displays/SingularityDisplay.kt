@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.groups.displays

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.implementation.items.materials.Singularity
import net.guizhanss.infinityexpansion2.utils.displayItem
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import kotlin.math.ceil
import kotlin.math.min

/**
 * Displays the recipe of an [SlimefunItem] that has some extra information to show.
 */
open class SingularityDisplay(private val singularity: Singularity) : ItemDisplay(singularity) {
    override fun setupMenu(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu) {
        val guide = Slimefun.getRegistry().getSlimefunGuide(mode)

        // backgrounds
        BACKGROUND.forEach {
            menu.addItem(it, GuiItems.RECIPES, ChestMenuUtils.getEmptyClickHandler())
        }

        // recipe type
        menu.addItem(RECIPE_TYPE_SLOT, sfItem.recipeType.getItem(p)) { _, _, _, _ ->
            displayItem(profile, IEItems.SINGULARITY_CONSTRUCTOR, mode)
            false
        }

        // back
        menu.addItem(
            GUIDE_BACK,
            ChestMenuUtils.getBackButton(
                p,
                "",
                ChatColor.GRAY.toString() + Slimefun.getLocalization().getMessage(p, "guide.back.guide")
            )
        ) { _, _, _, _ ->
            profile.guideHistory.goBack(guide)
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
        setupIngredients(p, profile, menu, mode, 1)
    }

    private fun setupIngredients(p: Player, profile: PlayerProfile, menu: ChestMenu, mode: SlimefunGuideMode, page: Int) {
        val startIndex = (page - 1) * 9
        val endIndex = min(startIndex + 9, singularity.ingredients.size)
        val totalPages = ceil(singularity.ingredients.size / 9.0).toInt()
        val ingredients = singularity.ingredients.entries.toList().subList(startIndex, endIndex)

        SoundEffect.GUIDE_BUTTON_CLICK_SOUND.playFor(p)

        RECIPE_DISPLAY_SLOTS.forEach {
            menu.replaceExistingItem(it, null)
            menu.addMenuClickHandler(it, ChestMenuUtils.getEmptyClickHandler())
        }

        ingredients.forEachIndexed { index, (item, progress) ->
            menu.replaceExistingItem(RECIPE_DISPLAY_SLOTS[index * 2], item)
            menu.addMenuClickHandler(RECIPE_DISPLAY_SLOTS[index * 2]) { _, _, _, _ ->
                displayItem(profile, item, mode)
                false
            }
            menu.replaceExistingItem(
                RECIPE_DISPLAY_SLOTS[index * 2 + 1],
                GuiItems.increaseProgress(progress).apply { this.amount = progress }
            )
        }

        menu.replaceExistingItem(PAGE_PREV, ChestMenuUtils.getPreviousButton(p, page, totalPages))
        if (page > 1) {
            menu.addMenuClickHandler(PAGE_PREV) { _, _, _, _ ->
                setupIngredients(p, profile, menu, mode, page - 1)
                false
            }
        } else {
            menu.addMenuClickHandler(PAGE_PREV, ChestMenuUtils.getEmptyClickHandler())
        }

        menu.replaceExistingItem(PAGE_NEXT, ChestMenuUtils.getNextButton(p, page, totalPages))
        if (page < totalPages) {
            menu.addMenuClickHandler(PAGE_NEXT) { _, _, _, _ ->
                setupIngredients(p, profile, menu, mode, page + 1)
                false
            }
        } else {
            menu.addMenuClickHandler(PAGE_NEXT, ChestMenuUtils.getEmptyClickHandler())
        }
    }

    companion object {
        private val BACKGROUND = intArrayOf(9, 10, 11, 12, 13, 14, 15, 16, 17)
        private const val GUIDE_BACK = 0
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
