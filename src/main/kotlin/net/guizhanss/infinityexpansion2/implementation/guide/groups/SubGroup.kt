@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.guide.groups

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * A [SubGroup] is an [ItemGroup] that is used to store all Infinity Expansion's items.
 *
 * The display group is actually [DisplaySubGroup].
 * This is used for displaying in cheat menu (when JEG isn't installed).
 */
open class SubGroup(
    key: NamespacedKey,
    item: ItemStack
) : ItemGroup(key, item) {

    override fun isVisible(p: Player) = false

    fun getName() = ItemUtils.getItemName(item)
}
