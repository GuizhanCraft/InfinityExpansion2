package net.guizhanss.infinityexpansion2.utils.items

import net.guizhanss.infinityexpansion2.InfinityExpansion2
import org.bukkit.Material

object GuiItems {
    val COLLECTING = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.GREEN_STAINED_GLASS_PANE),
        "collecting"
    )
    val PRODUCING = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.GREEN_STAINED_GLASS_PANE),
        "producing"
    )
    val NO_SPACE = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.RED_STAINED_GLASS_PANE),
        "no_space"
    )
    val INSUFFICIENT_POWER = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.RED_STAINED_GLASS_PANE),
        "insufficient_power"
    )
    val NO_STRAINER = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.RED_STAINED_GLASS_PANE),
        "no_strainer"
    )
}
