package net.guizhanss.infinityexpansion2.implementation.guide.groups

import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import net.guizhanss.infinityexpansion2.core.menu.FlexMenu
import net.guizhanss.infinityexpansion2.core.menu.MenuItem
import net.guizhanss.infinityexpansion2.utils.slimefunext.getBackButton
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import kotlin.math.ceil
import kotlin.math.min

/**
 * A [FlexMenu] that lists all [MenuItem]s.
 */
abstract class AbstractItemGroup(
    key: NamespacedKey, item: ItemStack
) : FlexMenu(key, item) {

    private val _menuItems = mutableListOf<MenuItem>()

    /**
     * All the [MenuItem]s in this [AbstractItemGroup].
     */
    val menuItems: List<MenuItem> get() = _menuItems

    /**
     * Add [MenuItem]s to this [AbstractItemGroup].
     */
    fun addMenuItem(vararg items: MenuItem) {
        _menuItems.addAll(items)
    }

    override fun isVisible(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) =
        mode == SlimefunGuideMode.SURVIVAL_MODE

    override fun initPage(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu) {
        val guide = Slimefun.getRegistry().getSlimefunGuide(mode)

        HEADER.forEach {
            menu.addItem(it, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler())
        }
        FOOTER.forEach {
            menu.addItem(it, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler())
        }

        menu.addItem(GUIDE_BACK, getBackButton(p)) { _, _, _, action ->
            if (action.isShiftClicked) {
                guide.openMainMenu(profile, profile.guideHistory.mainMenuPage)
            } else {
                profile.guideHistory.goBack(guide)
            }
            false
        }
    }

    override fun drawPage(
        p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu, page: Int
    ) {
        profile.guideHistory.add(this, page)

        val pages = ceil(_menuItems.size / PAGE_SIZE.toDouble()).toInt()
        val startIdx = (page - 1) * PAGE_SIZE
        val endIdx = min(startIdx + PAGE_SIZE, _menuItems.size)
        val subList = _menuItems.subList(startIdx, endIdx)

        for (i in 0 until PAGE_SIZE) {
            val slot = i + 9
            val item = subList.getOrNull(i)

            if (item != null) {
                menu.replaceExistingItem(slot, item.getItem(p, profile))
                menu.addMenuClickHandler(slot) { _, _, _, action ->
                    item.onClick(p, profile, mode, action)
                    false
                }
            } else {
                menu.replaceExistingItem(slot, null)
                menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler())
            }
        }

        // prev
        menu.replaceExistingItem(PAGE_PREV, ChestMenuUtils.getPreviousButton(p, page, pages))
        if (page > 1) {
            menu.addMenuClickHandler(PAGE_PREV) { _, _, _, _ ->
                SoundEffect.GUIDE_BUTTON_CLICK_SOUND.playFor(p)
                drawPage(p, profile, mode, menu, page - 1)
                false
            }
        } else {
            menu.addMenuClickHandler(PAGE_PREV, ChestMenuUtils.getEmptyClickHandler())
        }

        // next
        menu.replaceExistingItem(PAGE_NEXT, ChestMenuUtils.getNextButton(p, page, pages))
        if (page < pages) {
            menu.addMenuClickHandler(PAGE_NEXT) { _, _, _, _ ->
                SoundEffect.GUIDE_BUTTON_CLICK_SOUND.playFor(p)
                drawPage(p, profile, mode, menu, page + 1)
                false
            }
        } else {
            menu.addMenuClickHandler(PAGE_NEXT, ChestMenuUtils.getEmptyClickHandler())
        }
    }

    companion object {

        private val HEADER = arrayOf(0, 2, 3, 4, 5, 6, 7, 8)
        private const val GUIDE_BACK = 1
        private val FOOTER = arrayOf(45, 46, 47, 48, 49, 50, 51, 52, 53)
        private const val PAGE_SIZE = 36
        private const val PAGE_PREV = 46
        private const val PAGE_NEXT = 52
    }
}
