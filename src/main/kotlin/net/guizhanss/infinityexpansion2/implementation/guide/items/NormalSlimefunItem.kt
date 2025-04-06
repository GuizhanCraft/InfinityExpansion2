package net.guizhanss.infinityexpansion2.implementation.guide.items

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import net.guizhanss.infinityexpansion2.utils.slimefunext.displayItem
import net.guizhanss.infinityexpansion2.utils.slimefunext.getBackButton
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import kotlin.jvm.optionals.getOrNull
import kotlin.math.ceil
import kotlin.math.min

/**
 * The base class for all standard Slimefun item.
 */
open class NormalSlimefunItem(sfItem: SlimefunItem) : AbstractItem(sfItem) {

    protected open val backgroundSlots = BACKGROUND_SLOTS
    protected open val inputBorder = INPUT_BORDER
    protected open val outputBorder = OUTPUT_BORDER
    protected open val recipeTypeSlot = RECIPE_TYPE_SLOT
    protected open val backSlot = BACK_SLOT
    protected open val recipeArea = RECIPE_AREA
    protected open val outputSlot = OUTPUT_SLOT
    protected open val wikiSlot = WIKI_SLOT
    protected open val displayRecipePrev = PAGE_PREV
    protected open val displayRecipeNext = PAGE_NEXT
    protected open val displayRecipeSlots = DISPLAY_RECIPE_SLOTS

    override fun initPage(
        p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu
    ) {
        val guide = Slimefun.getRegistry().getSlimefunGuide(mode)

        // prepare area for recipe display
        if (displayRecipes.isNotEmpty() && displayRecipeSlots.isNotEmpty()) {
            menu.addItem(displayRecipeSlots.last(), null)
        }

        // background & border
        backgroundSlots.forEach { slot ->
            menu.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler())
        }
        inputBorder.forEach { slot ->
            menu.addItem(slot, ChestMenuUtils.getInputSlotTexture(), ChestMenuUtils.getEmptyClickHandler())
        }
        outputBorder.forEach { slot ->
            menu.addItem(slot, ChestMenuUtils.getOutputSlotTexture(), ChestMenuUtils.getEmptyClickHandler())
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

        // recipe type
        menu.addItem(recipeTypeSlot, sfItem.recipeType.getItem(p), ChestMenuUtils.getEmptyClickHandler())

        // recipe
        recipeArea.forEachIndexed { index, slot ->
            val ingredient = sfItem.recipe.getOrNull(index)
            menu.addItem(slot, ingredient)
            if (ingredient != null) {
                menu.addMenuClickHandler(slot) { _, _, _, _ ->
                    displayItem(profile, ingredient, mode)
                    false
                }
            } else {
                menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler())
            }
        }

        // output
        menu.addItem(outputSlot, sfItem.recipeOutput, ChestMenuUtils.getEmptyClickHandler())

        // wiki
        val wikiPage = sfItem.wikipage.getOrNull()
        if (wikiPage != null) {
            menu.addItem(wikiSlot, ChestMenuUtils.getWikiButton()) { p, _, _, _ ->
                p.closeInventory()
                ChatUtils.sendURL(p, wikiPage)
                false
            }
        }
    }

    override fun drawPage(
        p: Player,
        profile: PlayerProfile,
        mode: SlimefunGuideMode,
        menu: ChestMenu,
        page: Int
    ) {
        profile.guideHistory.add(this, page)

        if (displayRecipes.isEmpty() || displayRecipeSlots.isEmpty()) return

        showDisplayRecipes(p, profile, mode, menu, displayRecipes, page)
    }

    open val displayRecipes: List<ItemStack?>
        get() = if (sfItem is RecipeDisplayItem) sfItem.displayRecipes else emptyList()

    protected open fun showDisplayRecipes(
        p: Player,
        profile: PlayerProfile,
        mode: SlimefunGuideMode,
        menu: ChestMenu,
        recipes: List<ItemStack?>,
        page: Int,
    ) {
        displayRecipeSlots.forEach { slot ->
            menu.replaceExistingItem(slot, null)
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler())
        }

        val pageSize = displayRecipeSlots.size
        val startIndex = (page - 1) * pageSize
        val endIndex = min(startIndex + pageSize, recipes.size)
        val totalPages = ceil(recipes.size * 1.0 / pageSize).toInt()

        val displayRecipes = recipes.subList(startIndex, endIndex)

        displayRecipes.forEachIndexed { index, item ->
            menu.replaceExistingItem(displayRecipeSlots[index], item)
            menu.addMenuClickHandler(displayRecipeSlots[index]) { _, _, _, _ ->
                displayItem(profile, item!!, mode)
                false
            }
        }

        // page control
        menu.replaceExistingItem(displayRecipePrev, ChestMenuUtils.getPreviousButton(p, page, totalPages))
        if (page > 1) {
            menu.addMenuClickHandler(displayRecipePrev) { p, _, _, _ ->
                SoundEffect.GUIDE_BUTTON_CLICK_SOUND.playFor(p)
                drawPage(p, profile, mode, menu, page - 1)
                false
            }
        } else {
            menu.addMenuClickHandler(displayRecipePrev, ChestMenuUtils.getEmptyClickHandler())
        }

        menu.replaceExistingItem(displayRecipeNext, ChestMenuUtils.getNextButton(p, page, totalPages))
        if (page < totalPages) {
            menu.addMenuClickHandler(displayRecipeNext) { _, _, _, _ ->
                SoundEffect.GUIDE_BUTTON_CLICK_SOUND.playFor(p)
                drawPage(p, profile, mode, menu, page + 1)
                false
            }
        } else {
            menu.addMenuClickHandler(displayRecipeNext, ChestMenuUtils.getEmptyClickHandler())
        }
    }

    companion object {

        private val BACKGROUND_SLOTS = intArrayOf(27, 28, 29, 30, 31, 32, 33, 34, 35)
        private const val BACK_SLOT = 0
        private const val WIKI_SLOT = 8
        private const val RECIPE_TYPE_SLOT = 10
        private val INPUT_BORDER = intArrayOf()
        private val OUTPUT_BORDER = intArrayOf()
        private const val OUTPUT_SLOT = 16
        private val RECIPE_AREA = intArrayOf(
            3, 4, 5,
            12, 13, 14,
            21, 22, 23,
        )
        private const val PAGE_PREV = 28
        private const val PAGE_NEXT = 34
        private val DISPLAY_RECIPE_SLOTS = intArrayOf(
            36, 45, 37, 46, 38, 47, 39, 48, 40, 49, 41, 50, 42, 51, 43, 52, 44, 53
        )
    }
}
