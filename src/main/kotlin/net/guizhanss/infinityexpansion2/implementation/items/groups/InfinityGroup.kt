package net.guizhanss.infinityexpansion2.implementation.items.groups

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class InfinityGroup(
    key: NamespacedKey,
    item: ItemStack
) : FlexItemGroup(key, item) {
    companion object {
        // guide list
        private val HEADER = arrayOf(0, 2, 3, 4, 5, 6, 7, 8)
        private val BACK_SLOT = 1
        private val FOOTER = arrayOf(45, 46, 47, 48, 49, 50, 51, 52, 53)
        private val PAGE_PREV = 46
        private val PAGE_NEXT = 52
        // item display
    }

    private val subGroups: MutableList<ItemGroup> = mutableListOf()

    fun addSubGroups(vararg groups: ItemGroup) {
        subGroups.addAll(groups)
    }

    override fun isVisible(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) =
        mode == SlimefunGuideMode.SURVIVAL_MODE

    override fun open(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) {
        // openGuide(p, profile, mode, 1)
    }
}
