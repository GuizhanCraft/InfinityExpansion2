package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
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

    fun progressBar(progress: Int, total: Int) = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.LIME_STAINED_GLASS_PANE),
        "progress",
        "&7$progress / $total",
        ChestMenuUtils.getProgressBar(total - progress, total),
    )

    // display recipe info
    fun tickRate(tickRate: Int) = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.CLOCK),
        "tick_rate",
        "&7${tickRate}"
    )

    fun energyConsumption(energyPerTick: Int, tickRate: Int = 1) = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.REDSTONE),
        "energy_consumption",
        MachineLore.powerPerTick(energyPerTick),
        MachineLore.powerPerSecond(energyPerTick / tickRate)
    )

    fun totalProgress(total: Int) = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.GOLD_INGOT),
        "progress_total",
        "&7${total}"
    )

    fun increaseProgress(increase: Int) = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.GOLD_NUGGET),
        "progress_increase",
        "&7${increase}"
    )

    // strainer specific
    val ANY_STRAINER = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.FISHING_ROD),
        "any_strainer"
    )

    // stoneworks factory specific
    val SW_CHANGE = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.YELLOW_STAINED_GLASS_PANE),
        "sw_change"
    )
}
