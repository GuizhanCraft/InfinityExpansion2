@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.core.menu

import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.GuideHistory
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * The extended base [FlexItemGroup].
 */
abstract class FlexMenu(
    key: NamespacedKey, item: ItemStack
) : FlexItemGroup(key, item) {

    /**
     * The title of this guide.
     */
    abstract fun getGuideTitle(p: Player): String

    final override fun open(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) {
        val menu = ChestMenu(getGuideTitle(p))

        menu.isEmptySlotsClickable = false
        menu.addMenuOpeningHandler { SoundEffect.GUIDE_BUTTON_CLICK_SOUND.playFor(it) }

        initPage(p, profile, mode, menu)
        drawPage(p, profile, mode, menu, getLastPage(profile))

        menu.open(p)
    }

    /**
     * A very hacky way to get the last page from guide history, since flex group does not have a page field.
     *
     * Default to 1 if failed.
     */
    private fun getLastPage(profile: PlayerProfile): Int {
        try {
            val getEntryMethod = GuideHistory::class.java.getDeclaredMethod("getLastEntry", Boolean::class.java)
            getEntryMethod.isAccessible = true
            val entry = getEntryMethod.invoke(profile.guideHistory, false) ?: return 1

            val entryClass = Class.forName("io.github.thebusybiscuit.slimefun4.core.guide.GuideEntry")
            val entryGetObjMethod = entryClass.getDeclaredMethod("getIndexedObject")
            entryGetObjMethod.isAccessible = true
            val obj = entryGetObjMethod.invoke(entry)

            if (obj is FlexMenu) {
                val entryGetPageMethod = entryClass.getDeclaredMethod("getPage")
                entryGetPageMethod.isAccessible = true
                return entryGetPageMethod.invoke(entry) as? Int ?: 1
            }
        } catch (_: Exception) {
            // ignored, fallback to 1
        }
        return 1
    }

    /**
     * This method is called only once when the menu is opened.
     */
    abstract fun initPage(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu)

    /**
     * This method is called when the menu is opened or page is changed.
     */
    abstract fun drawPage(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu, page: Int)
}
