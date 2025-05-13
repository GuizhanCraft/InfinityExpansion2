package net.guizhanss.infinityexpansion2.core.menu

import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * A [MenuItem] can can have any custom click action.
 */
interface MenuItem {

    fun getItem(p: Player, profile: PlayerProfile): ItemStack

    fun onClick(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, action: ClickAction)
}
