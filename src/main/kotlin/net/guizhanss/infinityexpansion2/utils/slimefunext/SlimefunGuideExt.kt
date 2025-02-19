package net.guizhanss.infinityexpansion2.utils.slimefunext

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.implementation.groups.displays.InfinityWorkbenchDisplay
import net.guizhanss.infinityexpansion2.implementation.groups.displays.InformationalDisplay
import net.guizhanss.infinityexpansion2.implementation.groups.displays.SingularityDisplay
import net.guizhanss.infinityexpansion2.implementation.items.materials.Singularity
import net.guizhanss.infinityexpansion2.implementation.items.mobsim.MobDataCard
import net.guizhanss.infinityexpansion2.implementation.recipes.IERecipeTypes
import net.guizhanss.infinityexpansion2.utils.bukkitext.isAir
import net.guizhanss.infinityexpansion2.utils.toId
import org.bukkit.inventory.ItemStack

fun displayItem(profile: PlayerProfile, item: ItemStack, mode: SlimefunGuideMode) {
    if (item.isSlimefunItem()) {
        displayItem(profile, SlimefunItem.getByItem(item)!!, mode)
    } else {
        SlimefunGuide.displayItem(profile, item, true)
    }
}

fun displayItem(profile: PlayerProfile, sfItem: SlimefunItem, mode: SlimefunGuideMode, item: ItemStack? = null) {
    if (sfItem.recipeType == IERecipeTypes.INFINITY_WORKBENCH) {
        // infinity workbench recipe
        SlimefunGuide.openItemGroup(profile, InfinityWorkbenchDisplay(sfItem), mode, 1)
    } else if (sfItem is Singularity) {
        // singularity display
        SlimefunGuide.openItemGroup(profile, SingularityDisplay(sfItem), mode, 1)
    } else if (sfItem is InformationalRecipeDisplayItem) {
        var actualItem = sfItem

        // need to find the proper display item
        if (!item.isAir() && sfItem is MobDataCard) {
            MobDataCard.getMobDataId(item)?.let { id ->
                SlimefunItem.getById("${IEItems.MOB_DATA_CARD.itemId}_${id.toId()}")?.let { displayItem ->
                    actualItem = displayItem
                }
            }
        }
        // custom item display
        SlimefunGuide.openItemGroup(profile, InformationalDisplay(actualItem), mode, 1)
    } else {
        // normal recipe
        SlimefunGuide.displayItem(profile, sfItem, true)
    }
}
