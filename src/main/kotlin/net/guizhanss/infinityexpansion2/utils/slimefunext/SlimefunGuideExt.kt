@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.utils.slimefunext

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import net.guizhanss.guizhanlib.kt.minecraft.extensions.isAir
import net.guizhanss.guizhanlib.kt.slimefun.extensions.getSlimefunItem
import net.guizhanss.guizhanlib.kt.slimefun.extensions.isSlimefunItem
import net.guizhanss.guizhanlib.kt.slimefun.items.toItem
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.implementation.guide.items.InfinityWorkbenchItem
import net.guizhanss.infinityexpansion2.implementation.guide.items.InformationItem
import net.guizhanss.infinityexpansion2.implementation.guide.items.NormalSlimefunItem
import net.guizhanss.infinityexpansion2.implementation.guide.items.SingularityItem
import net.guizhanss.infinityexpansion2.implementation.items.materials.Singularity
import net.guizhanss.infinityexpansion2.implementation.items.mobsim.MobDataCard
import net.guizhanss.infinityexpansion2.implementation.recipes.IERecipeTypes
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun displayItem(profile: PlayerProfile, item: ItemStack, mode: SlimefunGuideMode) {
    if (item.isSlimefunItem()) {
        displayItem(profile, item.getSlimefunItem(), mode)
    } else {
        SlimefunGuide.displayItem(profile, item, true)
    }
}

fun displayItem(profile: PlayerProfile, item: SlimefunItemStack, mode: SlimefunGuideMode) {
    displayItem(profile, SlimefunItem.getByItem(item.toItem())!!, mode)
}

fun displayItem(profile: PlayerProfile, sfItem: SlimefunItem, mode: SlimefunGuideMode, item: ItemStack? = null) {
    when {
        sfItem.recipeType == IERecipeTypes.INFINITY_WORKBENCH -> {
            // infinity workbench recipe
            SlimefunGuide.openItemGroup(profile, InfinityWorkbenchItem(sfItem), mode, 1)
        }

        sfItem is Singularity -> {
            // singularity display
            SlimefunGuide.openItemGroup(profile, SingularityItem(sfItem), mode, 1)
        }

        sfItem is InformationalRecipeDisplayItem -> {
            // mob data cards need extra handling
            if (!item.isAir() && sfItem is MobDataCard) {
                MobDataCard.getMobDataCard(MobDataCard.getMobDataId(item))?.let { card ->
                    SlimefunGuide.openItemGroup(profile, InformationItem(card), mode, 1)
                }
            } else {
                // custom item display
                SlimefunGuide.openItemGroup(profile, InformationItem(sfItem), mode, 1)
            }
        }

        sfItem.addon == InfinityExpansion2.instance -> {
            // all the other ie2 items should be taken over
            SlimefunGuide.openItemGroup(profile, NormalSlimefunItem(sfItem), mode, 1)
        }

        else -> {
            // other recipe
            SlimefunGuide.displayItem(profile, sfItem, true)
        }
    }
}

/**
 * Get the back button for the guide.
 */
fun getBackButton(p: Player) = ChestMenuUtils.getBackButton(
    p,
    "",
    InfinityExpansion2.localization.getLore("guide.back.previous"),
    InfinityExpansion2.localization.getLore("guide.back.main")
)
