@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.groups.displays

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.utils.displayItem
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import kotlin.math.ceil
import kotlin.math.min

/**
 * Displays the recipe of an [SlimefunItem] that has some extra information to show.
 */
open class InformationalDisplay(sfItem: SlimefunItem) : ItemDisplay(sfItem) {
    protected open val background = BACKGROUND
    protected open val inputBorder = INPUT_BORDER
    protected open val outputBorder = OUTPUT_BORDER
    protected open val recipeTypeSlot = RECIPE_TYPE_SLOT
    protected open val guideBackSlot = GUIDE_BACK
    protected open val recipeArea = RECIPE_AREA
    protected open val outputSlot = OUTPUT_SLOT
    protected open val infoSlots = INFO_SLOTS

    protected open fun getRecipeTypeItem(p: Player) = sfItem.recipeType.getItem(p)

    override fun setupMenu(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu) {
        val guide = Slimefun.getRegistry().getSlimefunGuide(mode)

        // backgrounds
        background.forEach {
            menu.addItem(it, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler())
        }
        inputBorder.forEach {
            menu.addItem(it, ChestMenuUtils.getInputSlotTexture(), ChestMenuUtils.getEmptyClickHandler())
        }
        outputBorder.forEach {
            menu.addItem(it, ChestMenuUtils.getOutputSlotTexture(), ChestMenuUtils.getEmptyClickHandler())
        }

        // recipe type
        menu.addItem(recipeTypeSlot, getRecipeTypeItem(p), ChestMenuUtils.getEmptyClickHandler())

        // back
        menu.addItem(
            guideBackSlot, ChestMenuUtils.getBackButton(
                p, "", ChatColor.GRAY.toString() + Slimefun.getLocalization().getMessage(p, "guide.back.guide")
            )
        ) { _, _, _, _ ->
            profile.guideHistory.goBack(guide)
            false
        }

        // recipe
        recipeArea.forEachIndexed { index, slot ->
            val ingredient = sfItem.recipe.getOrNull(index)
            menu.addItem(slot, ingredient) { _, _, _, _ ->
                if (ingredient != null) {
                    displayItem(profile, ingredient, mode)
                }
                false
            }
        }

        // output
        menu.addItem(outputSlot, sfItem.item.clone(), ChestMenuUtils.getEmptyClickHandler())

        if (sfItem is InformationalRecipeDisplayItem) {
            // extra info
            val infoItems = sfItem.getInformationalItems()
            for (index in infoItems.indices) {
                if (index >= infoSlots.size) break
                menu.addItem(infoSlots[index], infoItems[index])
            }

            // display recipe displays
            if (sfItem.getDefaultDisplayRecipes().isNotEmpty()) {
                displayRecipes(p, profile, mode, menu)
            }
        }
    }

    protected open fun displayRecipes(
        p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu, page: Int = 1
    ) {
        val sfi = sfItem as InformationalRecipeDisplayItem
        val startIndex = (page - 1) * DISPLAY_RECIPE_SLOTS.size
        val endIndex = min(startIndex + DISPLAY_RECIPE_SLOTS.size, sfi.getDefaultDisplayRecipes().size)
        val totalPages = ceil(sfi.getDefaultDisplayRecipes().size * 1.0 / DISPLAY_RECIPE_SLOTS.size).toInt()

        SoundEffect.GUIDE_BUTTON_CLICK_SOUND.playFor(p)

        menu.addItem(DISPLAY_RECIPE_SLOTS.last(), null)

        DISPLAY_RECIPE_SLOTS.forEach {
            menu.replaceExistingItem(it, null)
            menu.addMenuClickHandler(it, ChestMenuUtils.getEmptyClickHandler())
        }

        val displayRecipes = sfi.getDefaultDisplayRecipes().subList(startIndex, endIndex)

        displayRecipes.forEachIndexed { index, item ->
            menu.replaceExistingItem(DISPLAY_RECIPE_SLOTS[index], item)
            menu.addMenuClickHandler(DISPLAY_RECIPE_SLOTS[index]) { _, _, _, _ ->
                displayItem(profile, item!!, mode)
                false
            }
        }

        menu.replaceExistingItem(PAGE_PREV, ChestMenuUtils.getPreviousButton(p, page, totalPages))
        if (page > 1) {
            menu.addMenuClickHandler(PAGE_PREV) { _, _, _, _ ->
                displayRecipes(p, profile, mode, menu, page - 1)
                false
            }
        } else {
            menu.addMenuClickHandler(PAGE_PREV, ChestMenuUtils.getEmptyClickHandler())
        }

        menu.replaceExistingItem(PAGE_NEXT, ChestMenuUtils.getNextButton(p, page, totalPages))
        if (page < totalPages) {
            menu.addMenuClickHandler(PAGE_NEXT) { _, _, _, _ ->
                displayRecipes(p, profile, mode, menu, page + 1)
                false
            }
        } else {
            menu.addMenuClickHandler(PAGE_NEXT, ChestMenuUtils.getEmptyClickHandler())
        }
    }

    companion object {
        private val BACKGROUND = intArrayOf(27, 28, 29, 30, 31, 32, 33, 34, 35)
        private const val GUIDE_BACK = 0
        private const val RECIPE_TYPE_SLOT = 10
        private val INPUT_BORDER = intArrayOf()
        private val OUTPUT_BORDER = intArrayOf()
        private const val OUTPUT_SLOT = 16
        private val RECIPE_AREA = intArrayOf(
            // @formatter:off
            3, 4, 5,
            12, 13, 14,
            21, 22, 23,
            // @formatter:on
        )
        private val INFO_SLOTS = intArrayOf(29, 30, 31, 32, 33)
        private const val PAGE_PREV = 27
        private const val PAGE_NEXT = 35
        private val DISPLAY_RECIPE_SLOTS = intArrayOf(
            36, 45, 37, 46, 38, 47, 39, 48, 40, 49, 41, 50, 42, 51, 43, 52, 44, 53
        )
    }
}
