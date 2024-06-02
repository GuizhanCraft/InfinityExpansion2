package net.guizhanss.infinityexpansion2.implementation.groups

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.menu.FlexGroup
import net.guizhanss.infinityexpansion2.core.menu.MenuItem
import net.guizhanss.infinityexpansion2.utils.displayItem
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * A fake [FlexGroup] that customizes the click action of the items.
 */
class FakeSubGroup(
    val itemGroup: ItemGroup,
) : FlexGroup(itemGroup.key, CustomItemStack(Material.BARRIER, "x")) {
    init {
        itemGroup.items.forEach { sfItem ->
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
                        // needs unlock
                        sfItem.research!!.unlockFromGuide(GUIDE, p, profile, sfItem, this@FakeSubGroup, 0)
                    } else {
                        displayItem(profile, sfItem, mode)
                    }
                }
            })
        }
    }

    override fun getGuideTitle(p: Player) =
        if (itemGroup is SubGroup) {
            itemGroup.getName()
        } else {
            itemGroup.getDisplayName(p)
        }

    companion object {
        private val GUIDE = Slimefun.getRegistry().getSlimefunGuide(SlimefunGuideMode.SURVIVAL_MODE)
    }
}
