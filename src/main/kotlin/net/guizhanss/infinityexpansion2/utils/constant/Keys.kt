package net.guizhanss.infinityexpansion2.utils.constant

import net.guizhanss.infinityexpansion2.InfinityExpansion2
import org.bukkit.NamespacedKey
import java.util.*

object Keys {
    val VEIN_MINER = "vein_miner".createKey()

    fun String.createKey() = NamespacedKey(InfinityExpansion2.instance, this.lowercase(Locale.getDefault()))
}
