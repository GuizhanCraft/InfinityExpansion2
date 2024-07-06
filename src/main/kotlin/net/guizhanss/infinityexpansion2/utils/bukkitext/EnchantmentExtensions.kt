@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.utils.bukkitext

import org.bukkit.enchantments.Enchantment

/**
 * Retrieve the [Enchantment] by the name. For 1.20.5+ compatibility.
 */
fun getEnchantment(name: String) = Enchantment.getByKey(name.toMinecraftKey())
