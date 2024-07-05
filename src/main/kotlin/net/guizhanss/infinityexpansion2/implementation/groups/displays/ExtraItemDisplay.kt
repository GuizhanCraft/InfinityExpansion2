@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.groups.displays

import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.ExtraItem
import net.guizhanss.infinityexpansion2.utils.bukkitext.createKey
import net.guizhanss.infinityexpansion2.utils.displayItem
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class ExtraItemDisplay(val extraItem: ExtraItem) : FlexItemGroup("display".createKey(), extraItem.output) {
    override fun isVisible(p: Player, profile: PlayerProfile, layout: SlimefunGuideMode) = false

    override fun open(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) {
        profile.guideHistory.add(this, 1)

        val menu = ChestMenu(InfinityExpansion2.integrationService.getTranslatedItemName(p, extraItem.output))

        menu.setEmptySlotsClickable(false)
        menu.addMenuOpeningHandler { SoundEffect.GUIDE_BUTTON_CLICK_SOUND.playFor(it) }

        setupMenu(p, profile, mode, menu)

        menu.open(p)
    }

    private fun setupMenu(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu) {
        val guide = Slimefun.getRegistry().getSlimefunGuide(mode)

        // back
        menu.addItem(
            BACK_SLOT, ChestMenuUtils.getBackButton(
                p, "", ChatColor.GRAY.toString() + Slimefun.getLocalization().getMessage(p, "guide.back.guide")
            )
        ) { _, _, _, _ ->
            profile.guideHistory.goBack(guide)
            false
        }

        // recipe type
        menu.addItem(RECIPE_TYPE_SLOT, extraItem.recipeType.getItem(p), ChestMenuUtils.getEmptyClickHandler())

        // input
        RECIPE_AREA.forEachIndexed { index, slot ->
            val ingredient = extraItem.input.getOrNull(index)
            menu.addItem(slot, ingredient) { _, _, _, _ ->
                if (ingredient != null) {
                    displayItem(profile, ingredient, mode)
                }
                false
            }
        }

        // output
        menu.addItem(OUTPUT_SLOT, extraItem.output, ChestMenuUtils.getEmptyClickHandler())
    }

    companion object {
        private val BACK_SLOT = 0
        private val RECIPE_TYPE_SLOT = 10
        private val RECIPE_AREA = intArrayOf(
            3, 4, 5,
            12, 13, 14,
            21, 22, 23
        )
        private val OUTPUT_SLOT = 16
    }
}
