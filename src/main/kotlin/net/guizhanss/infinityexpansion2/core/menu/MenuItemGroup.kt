package net.guizhanss.infinityexpansion2.core.menu

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import org.bukkit.entity.Player

class MenuItemGroup(
    private val itemGroup: ItemGroup,
) : MenuItem {
    override fun getItem(p: Player) = itemGroup.getItem(p)

    override fun onClick(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) {
        SlimefunGuide.openItemGroup(profile, itemGroup, mode, 1)
    }
}
