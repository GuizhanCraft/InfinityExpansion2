package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.bukkitext.getEnchantment
import org.bukkit.inventory.ItemStack
import java.util.logging.Level

fun String.isSlimefunItem() = SlimefunItem.getById(this) != null

@JvmName("isSlimefunItemGeneric")
inline fun <reified T : SlimefunItem> String.isSlimefunItem() = SlimefunItem.getById(this) is T

fun ItemStack?.isSlimefunItem() = SlimefunItem.getByItem(this) != null

@JvmName("isSlimefunItemGeneric")
inline fun <reified T : SlimefunItem> ItemStack?.isSlimefunItem() = SlimefunItem.getByItem(this) is T

fun String.removePrefix() = this.replace(InfinityExpansion2.localization.idPrefix, "")

fun applyInfinityGearEnchantment(sfItem: SlimefunItemStack) {
    val key = sfItem.itemId.replace("${InfinityExpansion2.localization.idPrefix}INFINITY_", "").lowercase()
    val section = InfinityExpansion2.configService.infinityGearEnchantLevels[key] ?: return

    val meta = sfItem.itemMeta
    meta.isUnbreakable = section.getBoolean("unbreakable")
    section.getKeys(false).forEach { enchant ->
        // unbreakable isn't an enchantment
        if (enchant == "unbreakable") return@forEach

        // lv 0 means disabled enchantment
        val level = section.getInt(enchant)
        if (level <= 0) return@forEach

        val enchantment = getEnchantment(enchant)
        if (enchantment == null) {
            InfinityExpansion2.log(
                Level.WARNING, "Infinity gear \"${sfItem.itemId}\" " +
                    "has an invalid enchantment \"$enchant\" in the config."
            )
            return@forEach
        }

        meta.addEnchant(enchantment, level, true)
    }
    sfItem.itemMeta = meta
}
