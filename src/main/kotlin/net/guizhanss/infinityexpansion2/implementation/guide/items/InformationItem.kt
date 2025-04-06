@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.guide.items

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * Displays the recipe of an [SlimefunItem] that has some extra information to show.
 */
open class InformationItem<T>(val infoSfItem: T) :
    NormalSlimefunItem(infoSfItem) where T : SlimefunItem, T : InformationalRecipeDisplayItem {

    override val displayRecipePrev = PAGE_PREV
    override val displayRecipeNext = PAGE_NEXT

    override fun initPage(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu) {
        super.initPage(p, profile, mode, menu)

        // extra info
        val infoItems = infoSfItem.getInfoItems()
        for (index in infoItems.indices) {
            if (index >= INFO_SLOTS.size) break
            menu.addItem(INFO_SLOTS[index], infoItems[index])
        }
    }

    override val displayRecipes: List<ItemStack?>
        get() = infoSfItem.getDefaultDisplayRecipes()

    companion object {

        private const val PAGE_PREV = 27
        private const val PAGE_NEXT = 35
        private val INFO_SLOTS = intArrayOf(29, 30, 31, 32, 33)
    }
}
