package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.constant.Keys
import net.guizhanss.infinityexpansion2.utils.constant.Strings
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.text.DecimalFormatSymbols
import java.util.Locale

object GuiItems {

    private val decimalPoint = DecimalFormatSymbols.getInstance(Locale.getDefault()).decimalSeparator

    val GUIDE = InfinityExpansion2.localization.getGuiItem(
        Material.ENCHANTED_BOOK.convert(),
        "guide"
    )
    val WIKI = InfinityExpansion2.localization.getGuiItem(
        Material.KNOWLEDGE_BOOK.convert(),
        "wiki"
    )
    val COLLECTING = InfinityExpansion2.localization.getGuiItem(
        Material.LIME_STAINED_GLASS_PANE.convert(),
        "collecting"
    )
    val PRODUCING = InfinityExpansion2.localization.getGuiItem(
        Material.LIME_STAINED_GLASS_PANE.convert(),
        "producing"
    )
    val NO_ROOM = InfinityExpansion2.localization.getGuiItem(
        Material.RED_STAINED_GLASS_PANE.convert(),
        "no_room"
    )
    val NO_POWER = InfinityExpansion2.localization.getGuiItem(
        Material.RED_STAINED_GLASS_PANE.convert(),
        "no_power"
    )
    val INVALID_INPUT = InfinityExpansion2.localization.getGuiItem(
        Material.RED_STAINED_GLASS_PANE.convert(),
        "invalid_input"
    )
    val SUCCESS = InfinityExpansion2.localization.getGuiItem(
        Material.LIME_STAINED_GLASS_PANE.convert(),
        "success"
    )
    val PRODUCES = InfinityExpansion2.localization.getGuiItem(
        Material.LIME_STAINED_GLASS_PANE.convert(),
        "produces"
    )
    val RECIPES = InfinityExpansion2.localization.getGuiItem(
        Material.LIME_STAINED_GLASS_PANE.convert(),
        "recipes"
    )
    val CRAFT = InfinityExpansion2.localization.getGuiItem(
        // https://minecraft-heads.com/custom-heads/head/24180-crafting-table
        "2cdc0feb7001e2c10fd5066e501b87e3d64793092b85a50c856d962f8be92c78".convertHead(),
        "craft"
    )
    val WORLD_NORMAL = InfinityExpansion2.localization.getGuiItem(
        Material.GRASS_BLOCK.convert(),
        "world_normal"
    )
    val WORLD_NETHER = InfinityExpansion2.localization.getGuiItem(
        Material.NETHERRACK.convert(),
        "world_nether"
    )
    val WORLD_THE_END = InfinityExpansion2.localization.getGuiItem(
        Material.END_STONE.convert(),
        "world_the_end"
    )

    fun progressBar(progress: Int, total: Int) = InfinityExpansion2.localization.getGuiItem(
        Material.LIME_STAINED_GLASS_PANE.convert(),
        "progress",
        "&7$progress / $total",
        ChestMenuUtils.getProgressBar(total - progress, total),
    )

    // display recipe info
    fun tickRate(tickRate: Int) = InfinityExpansion2.localization.getGuiItem(
        Material.CLOCK.convert(),
        "tick_rate",
        "&7${tickRate}"
    )

    fun energyConsumptionPerTick(energyPerTick: Int, tickRate: Int = 1) = InfinityExpansion2.localization.getGuiItem(
        Material.REDSTONE.convert(),
        "energy_consumption",
        MachineLore.powerPerTick(energyPerTick),
        MachineLore.powerPerSecond(energyPerTick / tickRate)
    )

    fun energyConsumptionPerUse(energyPerUse: Int) = InfinityExpansion2.localization.getGuiItem(
        Material.REDSTONE.convert(),
        "energy_consumption",
        MachineLore.powerPerUse(energyPerUse)
    )

    fun energyProductionPerTick(energyPerTick: Int, tickRate: Int = 1) = InfinityExpansion2.localization.getGuiItem(
        Material.GLOWSTONE_DUST.convert(),
        "energy_production",
        MachineLore.powerPerTick(energyPerTick),
        MachineLore.powerPerSecond(energyPerTick, tickRate)
    )

    fun outputInterval(interval: Int) = InfinityExpansion2.localization.getGuiItem(
        Material.COMPASS.convert(),
        "output_interval",
        "&7${interval}"
    )

    fun totalProgress(total: Int) = InfinityExpansion2.localization.getGuiItem(
        Material.GOLD_INGOT.convert(),
        "progress_total",
        "&7${total}"
    )

    fun increaseProgress(increase: Int) = InfinityExpansion2.localization.getGuiItem(
        Material.GOLD_NUGGET.convert(),
        "progress_increase",
        "&7${increase}"
    )

    fun chance(chance: Double) = InfinityExpansion2.localization.getGuiItem(
        Material.WHEAT_SEEDS.convert(),
        "chance",
        "&7${String.format("%.6f", chance * 100.0).trimEnd('0').trimEnd(decimalPoint)}%"
    )

    fun sfItem(allowed: Boolean) = InfinityExpansion2.localization.getGuiItem(
        MaterialType.Material(if (allowed) Material.GREEN_STAINED_GLASS_PANE else Material.RED_STAINED_GLASS_PANE),
        "sf_item",
        if (allowed) Strings.CHECK else Strings.CROSS
    )

    fun experience(exp: Int) = InfinityExpansion2.localization.getGuiItem(
        Material.EXPERIENCE_BOTTLE.convert(),
        "experience",
        "&7${exp}"
    )

    val OSCILLATOR_SLOT = InfinityExpansion2.localization.getGuiItem(
        Material.LIME_STAINED_GLASS_PANE.convert(),
        "oscillator_slot"
    )
}

/**
 * Get a copy of the original [ItemStack] with display item flag.
 */
internal fun ItemStack.toDisplayItem(): ItemStack {
    val item = clone()
    val meta = item.itemMeta
    PersistentDataAPI.setBoolean(meta!!, Keys.DISPLAY_ITEM, true)
    item.itemMeta = meta
    return item
}

/**
 * Get a copy of the original [ItemStack] without display item flag.
 */
internal fun ItemStack.removeDisplayItem(): ItemStack {
    val item = clone()
    val meta = item.itemMeta
    PersistentDataAPI.remove(meta!!, Keys.DISPLAY_ITEM)
    item.itemMeta = meta
    return item
}
