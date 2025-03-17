package net.guizhanss.infinityexpansion2.implementation.groups

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction
import net.guizhanss.guizhanlib.kt.minecraft.extensions.toItem
import net.guizhanss.guizhanlib.kt.minecraft.items.edit
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.menu.FlexGroup
import net.guizhanss.infinityexpansion2.core.menu.MenuItem
import net.guizhanss.infinityexpansion2.implementation.groups.displays.ExtraItemDisplay
import net.guizhanss.infinityexpansion2.utils.slimefunext.displayItem
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * A fake [FlexGroup] that customizes the click action of the items.
 */
class FakeSubGroup(
    val itemGroup: SubGroup,
) : FlexGroup(itemGroup.key, Material.BARRIER.toItem()) {

    init {
        itemGroup.items.forEach { sfItem ->
            addMenuItem(object : MenuItem {
                override fun getItem(p: Player, profile: PlayerProfile): ItemStack {
                    return if (sfItem.hasResearch() && !profile.hasUnlocked(sfItem.research)) {
                        getNotResearchedItem(p, sfItem)
                    } else {
                        sfItem.item
                    }
                }

                override fun onClick(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, action: ClickAction) {
                    val guide = Slimefun.getRegistry().getSlimefunGuide(mode)
                    if (mode == SlimefunGuideMode.CHEAT_MODE) {
                        // cheat mode
                        val amount = if (action.isShiftClicked) sfItem.item.maxStackSize else 1
                        p.inventory.addItem(sfItem.item.edit { amount(amount) })
                    } else if (sfItem.hasResearch() && !profile.hasUnlocked(sfItem.research)) {
                        // needs unlock
                        sfItem.research!!.unlockFromGuide(guide, p, profile, sfItem, this@FakeSubGroup, 0)
                    } else {
                        // survival mode unlocked
                        displayItem(profile, sfItem, mode)
                    }
                }
            })
        }

        itemGroup.extraItems.forEach { extraItem ->
            addMenuItem(object : MenuItem {
                override fun getItem(p: Player, profile: PlayerProfile): ItemStack {
                    val sfItem = SlimefunItem.getByItem(extraItem.output)
                    return if (sfItem != null && sfItem.hasResearch() && !profile.hasUnlocked(sfItem.research)) {
                        getNotResearchedItem(p, sfItem)
                    } else {
                        extraItem.output
                    }
                }

                override fun onClick(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, action: ClickAction) {
                    val sfItem = SlimefunItem.getByItem(extraItem.output)
                    val guide = Slimefun.getRegistry().getSlimefunGuide(mode)
                    if (sfItem != null && sfItem.hasResearch() && !profile.hasUnlocked(sfItem.research)) {
                        // needs unlock
                        sfItem.research!!.unlockFromGuide(guide, p, profile, sfItem, this@FakeSubGroup, 0)
                    } else {
                        SlimefunGuide.openItemGroup(profile, ExtraItemDisplay(extraItem), mode, 1)
                    }
                }
            })
        }
    }

    override fun getGuideTitle(p: Player) = itemGroup.getName()

    companion object {

        private fun getNotResearchedItem(p: Player, sfItem: SlimefunItem) =
            ChestMenuUtils.getNotResearchedItem().edit {
                name(InfinityExpansion2.integrationService.getItemName(p, sfItem.id))
                lore(
                    // Slimefun does not have all localization for research
                    // keeping same format because im lazy to add more localization
                    "&4&l" + Slimefun.getLocalization().getMessage(p, "guide.locked"),
                    "",
                    "&a> Click to unlock",
                    "",
                    "&7Cost: &b" + sfItem.research!!.cost + " Level(s)"
                )
            }
    }
}
