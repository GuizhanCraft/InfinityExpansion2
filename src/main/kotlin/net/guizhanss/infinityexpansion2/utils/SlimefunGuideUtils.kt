package net.guizhanss.infinityexpansion2.utils

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import net.guizhanss.infinityexpansion2.core.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.implementation.groups.displays.InfinityWorkbenchDisplay
import net.guizhanss.infinityexpansion2.implementation.groups.displays.InformationalDisplay
import net.guizhanss.infinityexpansion2.implementation.groups.displays.SingularityDisplay
import net.guizhanss.infinityexpansion2.implementation.items.materials.Singularity
import net.guizhanss.infinityexpansion2.implementation.recipes.IERecipeTypes
import net.guizhanss.infinityexpansion2.utils.items.isSlimefunItem
import org.bukkit.inventory.ItemStack

fun displayItem(profile: PlayerProfile, item: ItemStack, mode: SlimefunGuideMode) {
    if (item.isSlimefunItem()) {
        displayItem(profile, SlimefunItem.getByItem(item)!!, mode)
    } else {
        SlimefunGuide.displayItem(profile, item, true)
    }
}

fun displayItem(profile: PlayerProfile, sfItem: SlimefunItem, mode: SlimefunGuideMode) {
    if (sfItem.recipeType == IERecipeTypes.INFINITY_WORKBENCH) {
        // infinity workbench recipe
        SlimefunGuide.openItemGroup(profile, InfinityWorkbenchDisplay(sfItem), mode, 1)
    } else if (sfItem is Singularity) {
        // singularity display
        SlimefunGuide.openItemGroup(profile, SingularityDisplay(sfItem), mode, 1)
    } else if (sfItem is InformationalRecipeDisplayItem) {
        // custom item display
        SlimefunGuide.openItemGroup(profile, InformationalDisplay(sfItem), mode, 1)
    } else {
        // normal recipe
        SlimefunGuide.displayItem(profile, sfItem, true)
    }
}
