package net.guizhanss.infinityexpansion2.implementation.groups.infinity

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.menu.FlexGroup
import net.guizhanss.infinityexpansion2.core.menu.MenuItem
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * The fake group to display
 */
class InfinityGroup(
    key: NamespacedKey,
    item: ItemStack
) : FlexGroup(key, item) {
    fun addItem(sfItem: SlimefunItem) {
        addMenuItem(object : MenuItem {
            override fun getItem(p: Player, profile: PlayerProfile): ItemStack {
                return if (sfItem.hasResearch() && !profile.hasUnlocked(sfItem.research)) {
                    CustomItemStack(
                        ChestMenuUtils.getNotResearchedItem(),
                        InfinityExpansion2.integrationService.getItemName(p, sfItem.id),
                        // Slimefun does not have all localization for research
                        // keeping same format because im lazy to add more localization
                        "&4&l" + Slimefun.getLocalization().getMessage(p, "guide.locked"),
                        "",
                        "&a> Click to unlock",
                        "",
                        "&7Cost: &b" + sfItem.research!!.cost + " Level(s)"
                    )
                } else {
                    sfItem.item
                }
            }

            override fun onClick(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) {
                if (sfItem.hasResearch() && !profile.hasUnlocked(sfItem.research)) {
                    sfItem.research!!.unlockFromGuide(GUIDE, p, profile, sfItem, this@InfinityGroup, 0)
                } else {
                    SlimefunGuide.openItemGroup(profile, InfinityRecipeGroup(sfItem), mode, 1)
                }
            }
        })
    }

    override fun getGuideTitle(p: Player) = InfinityExpansion2.integrationService.getItemGroupName(p, "infinity")

    companion object {
        private val GUIDE = Slimefun.getRegistry().getSlimefunGuide(SlimefunGuideMode.SURVIVAL_MODE)
    }
}
