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
    val NO_POWER = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.RED_STAINED_GLASS_PANE),
        "no_power"
    )
    val INVALID_INPUT = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.RED_STAINED_GLASS_PANE),
        "invalid_input"
    )
    val PRODUCES = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.LIME_STAINED_GLASS_PANE),
        "produces"
    )
    val RECIPES = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.LIME_STAINED_GLASS_PANE),
        "recipes"
    )

    // display recipe info
    fun tickRate(tickRate: Int) = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.CLOCK),
        "tick_rate",
        "&7${tickRate}"
    )

    fun energyConsumption(energyPerTick: Int) = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.REDSTONE),
        "energy_consumption",
        LoreUtils.powerPerTick(energyPerTick),
        LoreUtils.powerPerSecond(energyPerTick)
    )

    // strainer specific
    val ANY_STRAINER = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.FISHING_ROD),
        "any_strainer"
    )
}
