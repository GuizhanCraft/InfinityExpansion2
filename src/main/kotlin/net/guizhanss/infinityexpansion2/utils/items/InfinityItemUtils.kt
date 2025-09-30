package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import net.guizhanss.guizhanlib.kt.minecraft.extensions.getEnchantment
import net.guizhanss.guizhanlib.kt.minecraft.extensions.getPotionEffectType
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.debug.DebugCase
import net.guizhanss.infinityexpansion2.core.items.attributes.ProtectionType
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.utils.Debug
import net.guizhanss.infinityexpansion2.utils.bukkitext.ie2Key
import org.bukkit.potion.PotionEffect
import java.util.logging.Level

fun String.removePrefix() = this.replace(IEItems.prefix, "")

fun getInfinityGearPotionEffects(key: String): Array<PotionEffect> {
    val section = InfinityExpansion2.configService.infinityGear.value[key.lowercase()] ?: run {
        InfinityExpansion2.log(
            Level.WARNING, "Infinity gear \"$key\"'s config section is missing."
        )
        return arrayOf()
    }

    Debug.log(DebugCase.INFINITY_GEAR_CONFIG, "getting potion effects for $key")
    val potionEffectSection = section.getConfigurationSection("potion-effects") ?: run {
        InfinityExpansion2.log(
            Level.WARNING, "Infinity gear \"$key\"'s potion effects section is missing."
        )
        return arrayOf()
    }

    val effects = mutableListOf<PotionEffect>()

    potionEffectSection.getKeys(false).forEach { effectType ->
        val amplifier = potionEffectSection.getInt(effectType)
        Debug.log(DebugCase.INFINITY_GEAR_CONFIG, "effect: $effectType, amplifier: $amplifier")
        if (amplifier < 0) return@forEach

        val type = getPotionEffectType(effectType) ?: run {
            InfinityExpansion2.log(
                Level.WARNING,
                "Infinity gear \"$key\"'s potion effects section has an invalid effect type \"$effectType\"."
            )
            return@forEach
        }
        Debug.log(DebugCase.INFINITY_GEAR_CONFIG, "effect type is valid")

        effects.add(PotionEffect(type, 600, amplifier, false, false, false))
    }

    return effects.toTypedArray()
}

fun getInfinityGearProtectionTypes(key: String): Array<ProtectionType> {
    val section = InfinityExpansion2.configService.infinityGear.value[key.lowercase()] ?: run {
        InfinityExpansion2.log(
            Level.WARNING, "Infinity gear \"$key\"'s config section is missing."
        )
        return arrayOf()
    }
    val types = section.getStringList("protections").toList()
    return types.mapNotNull { ProtectionType.getByKey(ie2Key(it)) }.toTypedArray()
}

fun applyInfinityGearEnchantment(sfItem: SlimefunItemStack) {
    val key = sfItem.itemId.replace("${IEItems.prefix}INFINITY_", "").lowercase()
    val section = InfinityExpansion2.configService.infinityGear.value[key] ?: run {
        InfinityExpansion2.log(
            Level.WARNING, "Infinity gear \"$key\"'s config section is missing."
        )
        return
    }

    Debug.log(DebugCase.INFINITY_GEAR_CONFIG, "applying config for $key")
    val meta = sfItem.itemMeta
    meta.isUnbreakable = section.getBoolean("unbreakable")
    val enchantSection = section.getConfigurationSection("enchantments") ?: run {
        InfinityExpansion2.log(
            Level.WARNING, "Infinity gear \"$key\"'s enchantments section is missing."
        )
        return
    }
    Debug.log(DebugCase.INFINITY_GEAR_CONFIG, "begin enchantments")
    enchantSection.getKeys(false).forEach { enchant ->

        // lv 0 means disabled enchantment
        val level = enchantSection.getInt(enchant)
        Debug.log(DebugCase.INFINITY_GEAR_CONFIG, "enchantment: $enchant, level: $level")
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
    Debug.log(DebugCase.INFINITY_GEAR_CONFIG, "end enchantments")
    sfItem.itemMeta = meta
}
