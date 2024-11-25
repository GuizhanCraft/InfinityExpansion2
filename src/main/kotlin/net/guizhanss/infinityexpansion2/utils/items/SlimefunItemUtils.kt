package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.utils.bukkitext.getEnchantment
import org.bukkit.inventory.ItemStack
import java.util.logging.Level

fun String.isSlimefunItem() = SlimefunItem.getById(this) != null

@JvmName("isSlimefunItemGeneric")
inline fun <reified T : SlimefunItem> String.isSlimefunItem() = SlimefunItem.getById(this) is T

fun ItemStack?.isSlimefunItem() = SlimefunItem.getByItem(this) != null

@JvmName("isSlimefunItemGeneric")
inline fun <reified T : SlimefunItem> ItemStack?.isSlimefunItem() = SlimefunItem.getByItem(this) is T

/**
 * This should be called only after [isSlimefunItem] check, or this will throw [IllegalStateException].
 * Cast to the base [SlimefunItem]. Use the generic one to set the class for casting to.
 */
fun ItemStack?.getSlimefunItem() = SlimefunItem.getByItem(this) ?: error("Not a SlimefunItem")

/**
 * This should be called only after [isSlimefunItem] check, or this will throw [IllegalStateException].
 * Cast to the specified [SlimefunItem] subclass.
 */
@JvmName("getSlimefunItemGeneric")
inline fun <reified T : SlimefunItem> ItemStack?.getSlimefunItem() =
    SlimefunItem.getByItem(this) as? T ?: error("Not a SlimefunItem")

fun String.removePrefix() = this.replace(IEItems.prefix, "")

fun applyInfinityGearEnchantment(sfItem: SlimefunItemStack) {
    val key = sfItem.itemId.replace("${IEItems.prefix}INFINITY_", "").lowercase()
    val section = InfinityExpansion2.configService.infinityGear[key] ?: run {
        InfinityExpansion2.log(
            Level.WARNING, "Infinity gear \"$key\"'s config section is missing."
        )
        return
    }

    InfinityExpansion2.debug("applying config for $key")
    val meta = sfItem.itemMeta
    meta.isUnbreakable = section.getBoolean("unbreakable")
    val enchantSection = section.getConfigurationSection("enchantments") ?: run {
        InfinityExpansion2.log(
            Level.WARNING, "Infinity gear \"$key\"'s enchantments section is missing."
        )
        return
    }
    InfinityExpansion2.debug("begin enchantments")
    enchantSection.getKeys(false).forEach { enchant ->

        // lv 0 means disabled enchantment
        val level = enchantSection.getInt(enchant)
        InfinityExpansion2.debug("enchantment: $enchant, level: $level")
        if (level <= 0) return@forEach

        val enchantment = getEnchantment(enchant) ?: run {
            InfinityExpansion2.log(
                Level.WARNING, "Infinity gear \"${sfItem.itemId}\" " +
                    "has an invalid enchantment \"$enchant\" in the config."
            )
            return@forEach
        }

        meta.addEnchant(enchantment, level, true)
    }
    InfinityExpansion2.debug("end enchantments")
    sfItem.itemMeta = meta
}
