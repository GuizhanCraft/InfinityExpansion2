package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.bukkitext.createKey
import net.guizhanss.infinityexpansion2.utils.constant.Strings
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object GuiItems {

    val GUIDE = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.ENCHANTED_BOOK),
        "guide"
    )
    val WIKI = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.KNOWLEDGE_BOOK),
        "wiki"
    )
    val COLLECTING = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.LIME_STAINED_GLASS_PANE),
        "collecting"
    )
    val PRODUCING = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.LIME_STAINED_GLASS_PANE),
        "producing"
    )
    val NO_ROOM = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.RED_STAINED_GLASS_PANE),
        "no_room"
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
    val WORLD_NORMAL = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.GRASS_BLOCK),
        "world_normal"
    )
    val WORLD_NETHER = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.NETHERRACK),
        "world_nether"
    )
    val WORLD_THE_END = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.END_STONE),
        "world_the_end"
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

    fun energyConsumptionPerTick(energyPerTick: Int, tickRate: Int = 1) = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.REDSTONE),
        "energy_consumption",
        MachineLore.powerPerTick(energyPerTick),
        MachineLore.powerPerSecond(energyPerTick / tickRate)
    )

    fun energyConsumptionPerUse(energyPerUse: Int) = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.REDSTONE),
        "energy_consumption",
        MachineLore.powerPerUse(energyPerUse)
    )

    fun energyProductionPerTick(energyPerTick: Int, tickRate: Int = 1) = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.GLOWSTONE_DUST),
        "energy_production",
        MachineLore.powerPerTick(energyPerTick),
        MachineLore.powerPerSecond(energyPerTick, tickRate)
    )

    fun outputInterval(interval: Int) = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.COMPASS),
        "output_interval",
        "&7${interval}"
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

    fun chance(chance: Double) = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.WHEAT_SEEDS),
        "chance",
        "&7${String.format("%.1f", chance * 100)}%"
    )

    fun sfItem(allowed: Boolean) = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(if (allowed) Material.GREEN_STAINED_GLASS_PANE else Material.RED_STAINED_GLASS_PANE),
        "sf_item",
        if (allowed) Strings.CHECK else Strings.CROSS
    )

    fun experience(exp: Int) = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.EXPERIENCE_BOTTLE),
        "experience",
        "&7${exp}"
    )

    val OSCILLATOR_SLOT = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(Material.LIME_STAINED_GLASS_PANE),
        "oscillator_slot"
    )
}

/**
 * Get a copy of the original [ItemStack] with display item flag.
 */
internal fun ItemStack.toDisplayItem(): ItemStack {
    val item = clone()
    val meta = item.itemMeta
    PersistentDataAPI.setBoolean(meta!!, "display_item".createKey(), true)
    item.itemMeta = meta
    return item
}

/**
 * Get a copy of the original [ItemStack] without display item flag.
 */
internal fun ItemStack.removeDisplayItem(): ItemStack {
    val item = clone()
    val meta = item.itemMeta
    PersistentDataAPI.remove(meta!!, "display_item".createKey())
    item.itemMeta = meta
    return item
}
