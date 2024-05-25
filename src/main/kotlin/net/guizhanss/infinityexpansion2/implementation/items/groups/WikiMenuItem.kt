package net.guizhanss.infinityexpansion2.implementation.items.groups

import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.menu.MenuItem
import net.guizhanss.infinityexpansion2.utils.items.MaterialType
import org.bukkit.Material
import org.bukkit.entity.Player

object WikiMenuItem : MenuItem {
    override fun getItem(p: Player, profile: PlayerProfile) = InfinityExpansion2.localization.getItemGroupItem(
        MaterialType.Material(Material.BOOK),
        "wiki"
    )

    override fun onClick(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) {
        p.closeInventory()

        // TODO: show wiki link
    }
}
