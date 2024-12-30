package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.ProtectionType
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.utils.bukkitext.createKey
import net.guizhanss.infinityexpansion2.utils.bukkitext.getEnchantment
import net.guizhanss.infinityexpansion2.utils.bukkitext.getPotionEffectType
import org.bukkit.potion.PotionEffect
import java.util.logging.Level

fun String.removePrefix() = this.replace(IEItems.prefix, "")

fun getInfinityGearPotionEffects(key: String): Array<PotionEffect> {
    val section = InfinityExpansion2.configService.infinityGear[key.lowercase()] ?: run {
        InfinityExpansion2.log(
            Level.WARNING, "Infinity gear \"$key\"'s config section is missing."
        )
        return arrayOf()
    }

    InfinityExpansion2.debug("getting potion effects for $key")
    val potionEffectSection = section.getConfigurationSection("potion-effects") ?: run {
        InfinityExpansion2.log(
            Level.WARNING, "Infinity gear \"$key\"'s potion effects section is missing."
        )
        return arrayOf()
    }

    val effects = mutableListOf<PotionEffect>()

    potionEffectSection.getKeys(false).forEach { effectType ->
        val amplifier = potionEffectSection.getInt(effectType)
        InfinityExpansion2.debug("effect: $effectType, amplifier: $amplifier")
        if (amplifier < 0) return@forEach

        val type = getPotionEffectType(effectType) ?: run {
            InfinityExpansion2.log(
                Level.WARNING,
                "Infinity gear \"$key\"'s potion effects section has an invalid effect type \"$effectType\"."
            )
            return@forEach
        }
        InfinityExpansion2.debug("effect type is valid")

        effects.add(PotionEffect(type, 600, amplifier, false, false, false))
    }

    return effects.toTypedArray()
}

fun getInfinityGearProtectionTypes(key: String): Array<ProtectionType> {
    val section = InfinityExpansion2.configService.infinityGear[key.lowercase()] ?: run {
        InfinityExpansion2.log(
            Level.WARNING, "Infinity gear \"$key\"'s config section is missing."
        )
        return arrayOf()
    }
    val types = section.getStringList("protections").toList()
    return types.mapNotNull { ProtectionType.getByKey(it.createKey()) }.toTypedArray()
}

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
