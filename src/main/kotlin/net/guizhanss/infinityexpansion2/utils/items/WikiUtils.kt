package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import net.guizhanss.infinityexpansion2.core.items.attributes.CustomWikiItem
import net.guizhanss.infinityexpansion2.utils.constant.Strings

fun sfWikiHandler(item: SlimefunItem) = ChestMenu.MenuClickHandler { p, _, _, _ ->
    p.closeInventory()
    ChatUtils.sendURL(p, item.wikipage.get())
    false
}

fun ie2WikiHandler(item: CustomWikiItem) = ChestMenu.MenuClickHandler { p, _, _, _ ->
    p.closeInventory()
    ChatUtils.sendURL(p, "${Strings.WIKI_URL}${item.wikiUrl}")
    false
}
