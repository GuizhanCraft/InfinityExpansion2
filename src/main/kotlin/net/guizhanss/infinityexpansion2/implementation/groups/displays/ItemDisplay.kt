@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.groups.displays

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.bukkitext.createKey
import org.bukkit.entity.Player

/**
 * A fake [ItemGroup] that displays any [SlimefunItem].
 */
abstract class ItemDisplay(protected val sfItem: SlimefunItem) :
    FlexItemGroup(sfItem.id.createKey(), sfItem.item.clone()) {

    override fun isVisible(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) = false

    override fun open(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) {
        profile.guideHistory.add(this, 1)

        val menu = ChestMenu(InfinityExpansion2.integrationService.getItemName(p, sfItem.id))

        menu.setEmptySlotsClickable(false)
        menu.addMenuOpeningHandler { pl -> SoundEffect.GUIDE_BUTTON_CLICK_SOUND.playFor(pl) }

        setupMenu(p, profile, mode, menu)

        menu.open(p)
    }

    abstract fun setupMenu(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu)
}

