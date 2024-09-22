package net.guizhanss.infinityexpansion2.utils.bukkitext

import net.guizhanss.guizhanlib.utils.StringUtil
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import org.bukkit.NamespacedKey
import java.util.Locale

/**
 * Create a InfinityExpansion2 [NamespacedKey] from the string.
 */
internal fun String.createKey() =
    NamespacedKey(InfinityExpansion2.instance, StringUtil.dehumanize(this).lowercase(Locale.getDefault()))

/**
 * Create a Minecraft [NamespacedKey] from the string.
 */
fun String.toMinecraftKey() =
    NamespacedKey.minecraft(StringUtil.dehumanize(this).lowercase(Locale.getDefault()))
