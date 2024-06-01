@file:Suppress("deprecation", "UNUSED_CHANGED_VALUE")

package net.guizhanss.infinityexpansion2.implementation.groups.infinity

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.attributes.CustomTickRateMachine
import net.guizhanss.infinityexpansion2.core.attributes.EnergyActionConsumer
import net.guizhanss.infinityexpansion2.core.attributes.EnergyTickingConsumer
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.utils.createKey
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.ChatColor
import org.bukkit.entity.Player

/**
 * The fake item group that is used to display infinity recipe.
 */
class InfinityRecipeGroup(private val sfItem: SlimefunItem) :
    FlexItemGroup(sfItem.id.createKey(), sfItem.item.clone()) {
    override fun isVisible(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) = false

    override fun open(p: Player, profile: PlayerProfile, mode: SlimefunGuideMode) {
        val guide = Slimefun.getRegistry().getSlimefunGuide(mode)

        profile.guideHistory.add(this, 1)

        val menu = ChestMenu(InfinityExpansion2.integrationService.getItemName(p, sfItem.id))

        menu.setEmptySlotsClickable(false)
        menu.addMenuOpeningHandler { pl -> SoundEffect.GUIDE_BUTTON_CLICK_SOUND.playFor(pl) }

        // backgrounds
        BACKGROUND.forEach {
            menu.addItem(it, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler())
        }
        INPUT_BORDER.forEach {
            menu.addItem(it, ChestMenuUtils.getInputSlotTexture(), ChestMenuUtils.getEmptyClickHandler())
        }
        OUTPUT_BORDER.forEach {
            menu.addItem(it, ChestMenuUtils.getOutputSlotTexture(), ChestMenuUtils.getEmptyClickHandler())
        }

        // recipe type
        menu.addItem(
            RECIPE_TYPE_SLOT,
            IEItems.INFINITY_WORKBENCH
        ) { _, _, _, _ ->
            SlimefunGuide.displayItem(profile, IEItems.INFINITY_WORKBENCH.clone(), true)
            false
        }

        // back
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

        // recipe
        RECIPE_AREA.forEachIndexed { index, slot ->
            val ingredient = sfItem.recipe.getOrNull(index)
            menu.addItem(slot, ingredient) { _, _, _, _ ->
                if (ingredient != null) {
                    SlimefunGuide.displayItem(profile, ingredient, true)
                }
                false
            }
        }

        // output
        menu.addItem(OUTPUT_SLOT, sfItem.item.clone(), ChestMenuUtils.getEmptyClickHandler())

        // extra info
        var infoIdx = 0
        if (sfItem is CustomTickRateMachine) {
            menu.addItem(INFO_SLOTS[infoIdx++], GuiItems.tickRate(sfItem.getCustomTickRate()))
        }
        if (sfItem is EnergyTickingConsumer) {
            menu.addItem(INFO_SLOTS[infoIdx++], GuiItems.energyConsumptionPerTick(sfItem.getEnergyConsumptionPerTick()))
        }
        if (sfItem is EnergyActionConsumer) {
            menu.addItem(
                INFO_SLOTS[infoIdx++],
                GuiItems.energyConsumptionPerUse(sfItem.getEnergyConsumptionPerAction())
            )
        }

        menu.open(p)
    }

    companion object {
        private const val GUIDE_BACK = 1
        private const val RECIPE_TYPE_SLOT = 10
        private val BACKGROUND = arrayOf(0, 2, 36, 37, 38, 45, 46, 47)
        private val INPUT_BORDER = arrayOf(9, 11)
        private val OUTPUT_BORDER = arrayOf(18, 20, 27, 28, 29)
        private const val OUTPUT_SLOT = 19
        private val RECIPE_AREA = arrayOf(
            // @formatter:off
            3, 4, 5, 6, 7, 8,
            12, 13, 14, 15, 16, 17,
            21, 22, 23, 24, 25, 26,
            30, 31, 32, 33, 34, 35,
            39, 40, 41, 42, 43, 44,
            48, 49, 50, 51, 52, 53,
            // @formatter:on
        )
        private val INFO_SLOTS = arrayOf(36, 37, 38, 45, 46, 47)
    }
}
