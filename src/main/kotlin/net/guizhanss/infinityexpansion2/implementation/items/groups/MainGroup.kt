@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.items.groups

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.menu.FlexGroup
import net.guizhanss.infinityexpansion2.core.menu.MenuItemGroup
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class MainGroup(
    key: NamespacedKey,
    item: ItemStack
) : FlexGroup(key, item) {
    fun addSubGroups(vararg groups: ItemGroup) {
        addMenuItem(*groups.map { MenuItemGroup(it) }.toTypedArray())
    }

    // TODO: i18n
    override fun getGuideTitle(p: Player) = InfinityExpansion2.localization.getItemGroupName("main")
}
