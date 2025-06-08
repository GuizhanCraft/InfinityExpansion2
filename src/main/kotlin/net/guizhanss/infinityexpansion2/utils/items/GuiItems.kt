package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import net.guizhanss.guizhanlib.common.utils.StringUtil
import net.guizhanss.guizhanlib.kt.minecraft.items.edit
import net.guizhanss.guizhanlib.kt.slimefun.items.builder.asMaterialType
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.ProtectionType
import net.guizhanss.infinityexpansion2.utils.constant.Keys
import net.guizhanss.infinityexpansion2.utils.constant.Strings
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionEffect
import java.text.DecimalFormatSymbols
import java.util.Locale
import kotlin.random.Random

/**
 * The helper object for GUI items.
 */
internal object GuiItems {

    // get the decimal point on the system locale
    // not all the countries use dot(.) as decimal point
    private val decimalPoint = DecimalFormatSymbols.getInstance(Locale.getDefault()).decimalSeparator

    // guide items
    val GUIDE = InfinityExpansion2.localization.getGuiItem(
        Material.ENCHANTED_BOOK.asMaterialType(),
        "guide"
    )
    val WIKI = InfinityExpansion2.localization.getGuiItem(
        Material.KNOWLEDGE_BOOK.asMaterialType(),
        "wiki"
    )
    val COLLECTING = InfinityExpansion2.localization.getGuiItem(
        Material.LIME_STAINED_GLASS_PANE.asMaterialType(),
        "collecting"
    )
    val PRODUCING = InfinityExpansion2.localization.getGuiItem(
        Material.LIME_STAINED_GLASS_PANE.asMaterialType(),
        "producing"
    )
    val NO_ROOM = InfinityExpansion2.localization.getGuiItem(
        Material.RED_STAINED_GLASS_PANE.asMaterialType(),
        "no_room"
    )
    val NO_POWER = InfinityExpansion2.localization.getGuiItem(
        Material.RED_STAINED_GLASS_PANE.asMaterialType(),
        "no_power"
    )
    val INVALID_INPUT = InfinityExpansion2.localization.getGuiItem(
        Material.RED_STAINED_GLASS_PANE.asMaterialType(),
        "invalid_input"
    )
    val DISABLED = InfinityExpansion2.localization.getGuiItem(
        Material.BARRIER.asMaterialType(),
        "disabled"
    )
    val DISABLED_IN_WORLD = InfinityExpansion2.localization.getGuiItem(
        Material.BARRIER.asMaterialType(),
        "disabled_in_world"
    )
    val SUCCESS = InfinityExpansion2.localization.getGuiItem(
        Material.LIME_STAINED_GLASS_PANE.asMaterialType(),
        "success"
    )

    fun progressBar(progress: Int, total: Int) = InfinityExpansion2.localization.getGuiItem(
        Material.YELLOW_STAINED_GLASS_PANE.asMaterialType(),
        "progress",
        "&7$progress / $total",
        ChestMenuUtils.getProgressBar(total - progress, total),
    )

    val CRAFT = InfinityExpansion2.localization.getGuiItem(
        // https://minecraft-heads.com/custom-heads/head/24180-crafting-table
        "2cdc0feb7001e2c10fd5066e501b87e3d64793092b85a50c856d962f8be92c78".asMaterialType(),
        "craft"
    )

    // display recipe specific
    val DISPLAY_RECIPES = InfinityExpansion2.localization.getGuiItem(
        Material.BOOK.asMaterialType(),
        "display_recipes"
    )
    val WORLD_NORMAL = InfinityExpansion2.localization.getGuiItem(
        Material.GRASS_BLOCK.asMaterialType(),
        "world_normal"
    )
    val WORLD_NETHER = InfinityExpansion2.localization.getGuiItem(
        Material.NETHERRACK.asMaterialType(),
        "world_nether"
    )
    val WORLD_THE_END = InfinityExpansion2.localization.getGuiItem(
        Material.END_STONE.asMaterialType(),
        "world_the_end"
    )
    val PRODUCES = InfinityExpansion2.localization.getGuiItem(
        Material.LIME_STAINED_GLASS_PANE.asMaterialType(),
        "produces"
    )
    val RECIPES = InfinityExpansion2.localization.getGuiItem(
        Material.LIME_STAINED_GLASS_PANE.asMaterialType(),
        "recipes"
    )

    fun tickRate(tickRate: Int) = InfinityExpansion2.localization.getGuiItem(
        Material.CLOCK.asMaterialType(),
        "tick_rate",
        "&7${tickRate}"
    )

    fun energyConsumptionPerTick(energyPerTick: Int, tickRate: Int = 1) = InfinityExpansion2.localization.getGuiItem(
        Material.REDSTONE.asMaterialType(),
        "energy_consumption",
        MachineLore.powerPerTick(energyPerTick),
        MachineLore.powerPerSecond(energyPerTick / tickRate)
    )

    fun energyConsumptionPerUse(energyPerUse: Int) = InfinityExpansion2.localization.getGuiItem(
        Material.REDSTONE.asMaterialType(),
        "energy_consumption",
        MachineLore.powerPerUse(energyPerUse)
    )

    fun energyProductionPerTick(energyPerTick: Int, tickRate: Int = 1) = InfinityExpansion2.localization.getGuiItem(
        Material.GLOWSTONE_DUST.asMaterialType(),
        "energy_production",
        MachineLore.powerPerTick(energyPerTick),
        MachineLore.powerPerSecond(energyPerTick, tickRate)
    )

    fun outputInterval(interval: Int) = InfinityExpansion2.localization.getGuiItem(
        Material.COMPASS.asMaterialType(),
        "output_interval",
        "&7${interval}"
    )

    fun totalProgress(total: Int) = InfinityExpansion2.localization.getGuiItem(
        Material.GOLD_INGOT.asMaterialType(),
        "progress_total",
        "&7${total}"
    )

    fun increaseProgress(increase: Int) = InfinityExpansion2.localization.getGuiItem(
        Material.GOLD_NUGGET.asMaterialType(),
        "progress_increase",
        "&7${increase}"
    )

    fun chance(chance: Double) = InfinityExpansion2.localization.getGuiItem(
        Material.WHEAT_SEEDS.asMaterialType(),
        "chance",
        "&7${String.format("%.6f", chance * 100.0).trimEnd('0').trimEnd(decimalPoint)}%"
    )

    fun sfItem(allowed: Boolean) = InfinityExpansion2.localization.getGuiItem(
        if (allowed) {
            // https://minecraft-heads.com/custom-heads/head/112527-slime
            "6061f98aaff1e406e06f6f13ffe06005873f3d1eaddb1b59e19dda0ed9ffb270".asMaterialType()
        } else {
            // https://minecraft-heads.com/custom-heads/head/104564-red-slime
            "412c23cbc89259c497afef5e4e622cf208f621e0272918151b28f1a99f28d561".asMaterialType()
        },
        "sf_item",
        if (allowed) Strings.CHECK else Strings.CROSS
    )

    fun experience(exp: Int) = InfinityExpansion2.localization.getGuiItem(
        Material.EXPERIENCE_BOTTLE.asMaterialType(),
        "experience",
        "&7${exp}"
    )

    val OSCILLATOR_SLOT = InfinityExpansion2.localization.getGuiItem(
        Material.LIME_STAINED_GLASS_PANE.asMaterialType(),
        "oscillator_slot"
    )

    fun potionEffects(effects: Collection<PotionEffect>): ItemStack {
        val item = InfinityExpansion2.localization.getGuiItem(
            Material.POTION.asMaterialType(),
            "potion_effects"
        )
        val meta = item.itemMeta as PotionMeta
        effects.forEach {
            meta.addCustomEffect(it, true)
        }
        item.itemMeta = meta
        return item
    }

    fun protectionTypes(types: Collection<ProtectionType>) =
        InfinityExpansion2.localization.getGuiItem(
            Material.SHIELD.asMaterialType(),
            "protection_types"
        ).edit {
            setLore(types.map { "&7${StringUtil.humanize(it.key.key)}" })
        }
}

/**
 * Add the PDC flag to the [ItemStack] to indicate it is a display item.
 *
 * To get a copy, use [asDisplayItem] instead.
 */
internal fun ItemStack.asDisplayItem(): ItemStack {
    val meta = itemMeta!!
    PersistentDataAPI.setByte(
        meta,
        Keys.DISPLAY_ITEM,
        Random.nextInt(Byte.MIN_VALUE.toInt(), Byte.MAX_VALUE.toInt() + 1).toByte()
    )
    itemMeta = meta
    return this
}

/**
 * Get a copy of the original [ItemStack] with display item flag.
 *
 * To perform the operation on the original [ItemStack], use [asDisplayItem] instead.
 */
internal fun ItemStack.toDisplayItem() = clone().asDisplayItem()

/**
 * Remove the PDC flag from the [ItemStack] to indicate it is not a display item.
 *
 * To get a copy, use [removeDisplayItem] instead.
 */
internal fun ItemStack.asNotDisplayItem(): ItemStack {
    val meta = itemMeta!!
    PersistentDataAPI.remove(meta, Keys.DISPLAY_ITEM)
    itemMeta = meta
    return this
}

/**
 * Get a copy of the original [ItemStack] without display item flag.
 *
 * To perform the operation on the original [ItemStack], use [asNotDisplayItem] instead.
 */
internal fun ItemStack.removeDisplayItem() = clone().asNotDisplayItem()

internal fun ItemStack.isDisplayItem() =
    hasItemMeta() && PersistentDataAPI.hasByte(itemMeta!!, Keys.DISPLAY_ITEM)
