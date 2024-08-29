package net.guizhanss.infinityexpansion2.utils.bukkitext

import org.bukkit.Registry
import org.bukkit.enchantments.Enchantment

/**
 * Retrieve the [Enchantment] by the name.
 */
fun getEnchantment(name: String) = Registry.ENCHANTMENT.get(name.toMinecraftKey())
