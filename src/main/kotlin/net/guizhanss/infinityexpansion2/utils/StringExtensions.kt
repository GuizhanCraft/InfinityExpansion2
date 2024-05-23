package net.guizhanss.infinityexpansion2.utils

import net.guizhanss.guizhanlib.utils.StringUtil
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import org.bukkit.NamespacedKey
import java.util.Locale

internal fun String.createKey() = NamespacedKey(InfinityExpansion2.instance, this.lowercase(Locale.getDefault()))

fun String.toId() = StringUtil.dehumanize(this).uppercase()
