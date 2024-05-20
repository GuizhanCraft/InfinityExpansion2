@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.items.groups

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import org.bukkit.ChatColor
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


class MainGroup(
    key: NamespacedKey,
    item: ItemStack
) : FlexItemGroup(key, item) {
    companion object {
        private val HEADER = arrayOf(0, 2, 3, 4, 5, 6, 7, 8)
        private const val GUIDE_BACK = 1
        private val FOOTER = arrayOf(45, 46, 47, 48, 49, 50, 51, 52, 53)
    }

    private val subGroups: MutableList<ItemGroup> = mutableListOf()

    fun addSubGroups(vararg groups: ItemGroup) {
        subGroups.addAll(groups)
    }

    override fun isVisible(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) =
        mode == SlimefunGuideMode.SURVIVAL_MODE

    override fun open(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) {
        openGuide(p, profile, mode, 1)
    }

    private fun openGuide(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode, page: Int) {
        val guide = Slimefun.getRegistry().getSlimefunGuide(mode)

        profile.guideHistory.add(this, 1)

        val menu = ChestMenu(InfinityExpansion2.localization.getItemGroupName("main"))

        menu.setEmptySlotsClickable(false)
        menu.addMenuOpeningHandler { pl -> SoundEffect.GUIDE_BUTTON_CLICK_SOUND.playFor(pl) }

        for (i in HEADER) {
            menu.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler())
        }
        for (i in FOOTER) {
            menu.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler())
        }

        menu.addItem(
            GUIDE_BACK,
            ChestMenuUtils.getBackButton(
                p,
                "",
                ChatColor.GRAY.toString() + Slimefun.getLocalization().getMessage(p, "guide.back.guide")
            )
        ) { _, _, _, _ ->
            profile.guideHistory.goBack(guide)
            false
        }

        for (i in 0 until 36) {
            val slot = i + 9

            if (i + 1 > subGroups.size) break

            val group = subGroups[i]
            menu.addItem(slot, group.getItem(p)) { _, _, _, _ ->
                SlimefunGuide.openItemGroup(profile, group, mode, 1)
                false
            }
        }

        menu.open(p)
    }
}
