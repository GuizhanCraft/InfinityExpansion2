@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.groups

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.menu.FlexGroup
import net.guizhanss.infinityexpansion2.core.menu.MenuItem
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class MainGroup(
    key: NamespacedKey,
    item: ItemStack
) : FlexGroup(key, item) {
    fun addSubGroups(vararg groups: ItemGroup) {
        addMenuItem(*groups.map {
            object : MenuItem {
                override fun getItem(p: Player, profile: PlayerProfile) = it.getItem(p)

                override fun onClick(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) {
                    SlimefunGuide.openItemGroup(profile, FakeSubGroup(it), mode, 1)
                }
            }
        }.toTypedArray())
    }

    override fun getGuideTitle(p: Player) = InfinityExpansion2.integrationService.getItemGroupName(p, "main")

    override fun setupBorder(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu) {
        super.setupBorder(p, profile, mode, menu)

        // guide
        menu.addItem(GUIDE_SLOT, GuiItems.GUIDE)
        // TODO: in-game guide

        // wiki
        menu.addItem(WIKI_SLOT, GuiItems.WIKI) { pl, _, _, _ ->
            pl.closeInventory()
            ChatUtils.sendURL(pl, "https://docs.ybw0014.dev/infinity-expansion-2/")
            false
        }
    }

    companion object {
        private const val GUIDE_SLOT = 4
        private const val WIKI_SLOT = 7
    }
}
