package net.guizhanss.infinityexpansion2.utils.bukkitext

import net.guizhanss.guizhanlib.common.utils.StringUtil
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import org.bukkit.NamespacedKey
import java.util.Locale

/**
 * Create a InfinityExpansion2 [NamespacedKey] from the string.
 */
fun ie2Key(key: String) =
    NamespacedKey(InfinityExpansion2.instance, StringUtil.dehumanize(key).lowercase(Locale.ENGLISH))
