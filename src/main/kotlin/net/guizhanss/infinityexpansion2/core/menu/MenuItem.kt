package net.guizhanss.infinityexpansion2.core.menu

import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

interface MenuItem {
    fun getItem(p: Player): ItemStack

    fun onClick(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode)
}
