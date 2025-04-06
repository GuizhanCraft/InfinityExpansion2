package net.guizhanss.infinityexpansion2.implementation.guide.groups

import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.menu.MenuItem
import net.guizhanss.infinityexpansion2.utils.constant.Strings
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * The main InfinityExpansion2 item group.
 */
class MainGroup(
    key: NamespacedKey,
    item: ItemStack
) : AbstractItemGroup(key, item) {

    fun addSubGroups(vararg groups: SubGroup) {
        addMenuItem(*groups.map {
            object : MenuItem {
                override fun getItem(p: Player, profile: PlayerProfile) = it.getItem(p)

                override fun onClick(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, action: ClickAction) {
                    SlimefunGuide.openItemGroup(profile, DisplaySubGroup(it), mode, 1)
                }
            }
        }.toTypedArray())
    }

    override fun getGuideTitle(p: Player) = InfinityExpansion2.integrationService.getItemGroupName(p, "main")

    override fun initPage(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, menu: ChestMenu) {
        super.initPage(p, profile, mode, menu)

        // guide
        menu.addItem(GUIDE_SLOT, GuiItems.GUIDE) { pl, _, _, _ ->
            pl.closeInventory()
            pl.sendMessage("Work in progress")
            false
        }
        // TODO: in-game guide

        // wiki
        menu.addItem(WIKI_SLOT, GuiItems.WIKI) { pl, _, _, _ ->
            pl.closeInventory()
            ChatUtils.sendURL(pl, Strings.WIKI_URL)
            false
        }
    }

    companion object {

        private const val GUIDE_SLOT = 4
        private const val WIKI_SLOT = 7
    }
}
