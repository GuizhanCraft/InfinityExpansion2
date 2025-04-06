@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.guide.items

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.menu.FlexMenu
import net.guizhanss.infinityexpansion2.utils.bukkitext.ie2Key
import org.bukkit.entity.Player

/**
 * The base [FlexMenu] to display any [SlimefunItem].
 */
abstract class AbstractItem(protected val sfItem: SlimefunItem) :
    FlexMenu(ie2Key(sfItem.id), sfItem.item.clone()) {

    override fun isVisible(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) = false

    override fun getGuideTitle(p: Player): String {
        var itemName = InfinityExpansion2.integrationService.getItemName(p, sfItem.id)
        if (itemName.isEmpty()) {
            itemName = ItemUtils.getItemName(sfItem.item)
        }
        return itemName
    }
}

