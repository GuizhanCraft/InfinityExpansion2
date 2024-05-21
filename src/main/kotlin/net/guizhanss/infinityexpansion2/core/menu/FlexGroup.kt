@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.core.menu

import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import org.bukkit.ChatColor
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import kotlin.math.ceil
import kotlin.math.min

abstract class FlexGroup(
    key: NamespacedKey,
    item: ItemStack
) : FlexItemGroup(key, item) {
    companion object {
        private val HEADER = arrayOf(0, 2, 3, 4, 5, 6, 7, 8)
        private const val GUIDE_BACK = 1
        private val FOOTER = arrayOf(45, 46, 47, 48, 49, 50, 51, 52, 53)
        private const val PAGE_SIZE = 36
        private const val PAGE_PREV = 46
        private const val PAGE_NEXT = 52
    }

    private val menuItems: MutableList<MenuItem> = mutableListOf()

    fun addMenuItem(vararg items: MenuItem) {
        menuItems.addAll(items)
    }

    abstract fun getGuideTitle(p: Player): String

    override fun isVisible(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) =
        mode == SlimefunGuideMode.SURVIVAL_MODE

    override fun open(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) {
        openGuide(p, profile, mode, 1)
    }

    private fun openGuide(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, page: Int) {
        val guide = Slimefun.getRegistry().getSlimefunGuide(mode)

        profile.guideHistory.add(this, 1)

        val menu = ChestMenu(getGuideTitle(p))

        menu.setEmptySlotsClickable(false)
        menu.addMenuOpeningHandler { pl -> SoundEffect.GUIDE_BUTTON_CLICK_SOUND.playFor(pl) }

        for (i in HEADER) {
            menu.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler())
        }
        for (i in FOOTER) {
            menu.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler())
        }

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

        displayPage(p, profile, mode, menu, page)

        menu.open(p)
    }

    private fun displayPage(
        p: Player,
        profile: PlayerProfile,
        mode: SlimefunGuideMode,
        menu: ChestMenu,
        page: Int
    ) {
        val startIdx = (page - 1) * PAGE_SIZE
        val endIdx = min(startIdx + PAGE_SIZE, menuItems.size)
        val subList = menuItems.subList(startIdx, endIdx)
        val pages = ceil(menuItems.size / PAGE_SIZE.toDouble()).toInt()

        for (i in 0 until PAGE_SIZE) {
            val slot = i + 9
            val item = subList.getOrNull(i)

            if (item != null) {
                menu.replaceExistingItem(slot, item.getItem(p))
                menu.addMenuClickHandler(slot) { _, _, _, _ ->
                    item.onClick(p, profile, mode)
                    false
                }
            } else {
                menu.replaceExistingItem(slot, null)
                menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler())
            }
        }

        // prev
        if (page > 1) {
            menu.replaceExistingItem(PAGE_PREV, ChestMenuUtils.getPreviousButton(p, page, pages))
            menu.addMenuClickHandler(PAGE_PREV) { _, _, _, _ ->
                displayPage(p, profile, mode, menu, page - 1)
                false
            }
        } else {
            menu.replaceExistingItem(PAGE_PREV, ChestMenuUtils.getBackground())
            menu.addMenuClickHandler(PAGE_PREV, ChestMenuUtils.getEmptyClickHandler())
        }

        // next
        if (page < pages) {
            menu.replaceExistingItem(PAGE_NEXT, ChestMenuUtils.getNextButton(p, page, pages))
            menu.addMenuClickHandler(PAGE_NEXT) { _, _, _, _ ->
                displayPage(p, profile, mode, menu, page + 1)
                false
            }
        } else {
            menu.replaceExistingItem(PAGE_NEXT, ChestMenuUtils.getBackground())
            menu.addMenuClickHandler(PAGE_NEXT, ChestMenuUtils.getEmptyClickHandler())
        }
    }
}
