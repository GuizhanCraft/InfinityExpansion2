@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.groups.displays

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import net.guizhanss.guizhanlib.kt.slimefun.items.toItem
import net.guizhanss.infinityexpansion2.implementation.IEItems
import org.bukkit.entity.Player

/**
 * Display an item that is crafted in the Infinity Workbench.
 */
open class InfinityWorkbenchDisplay(sfItem: SlimefunItem) : InformationalDisplay(sfItem) {

    override val background = BACKGROUND
    override val inputBorder = INPUT_BORDER
    override val outputBorder = OUTPUT_BORDER
    override val recipeTypeSlot = RECIPE_TYPE_SLOT
    override val guideBackSlot = GUIDE_BACK
    override val recipeArea = RECIPE_AREA
    override val outputSlot = OUTPUT_SLOT
    override val infoSlots = INFO_SLOTS

    override fun getRecipeTypeItem(p: Player) = IEItems.INFINITY_WORKBENCH.toItem()

    override fun displayRecipes(
        p: Player,
        profile: PlayerProfile,
        mode: SlimefunGuideMode,
        menu: ChestMenu,
        page: Int
    ) {
        // no display recipes in infinity recipe
    }

    companion object {

        private val BACKGROUND = intArrayOf(0, 2, 36, 37, 38, 45, 46, 47)
        private const val GUIDE_BACK = 1
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
    }
}
