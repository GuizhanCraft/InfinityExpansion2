package net.guizhanss.infinityexpansion2.utils.constant

import net.guizhanss.infinityexpansion2.InfinityExpansion2
import org.bukkit.NamespacedKey
import java.util.Locale

object Keys {
    val GROUP_MAIN = "main".createKey()
    val GROUP_MATERIALS = "materials".createKey()
    val GROUP_TOOLS = "tools".createKey()
    val GROUP_MACHINES = "machines".createKey()
    val GROUP_GENERATORS = "generators".createKey()
    val GROUP_SINGULARITIES = "singularities".createKey()
    val GROUP_MOB_SIMULATION = "mob_simulation".createKey()
    val GROUP_STORAGE = "storage".createKey()
    val GROUP_INFINITY = "infinity".createKey()
    val GROUP_INFINITY_DISPLAY = "infinity_display".createKey()

    val VEIN_MINER = "vein_miner".createKey()

    fun String.createKey() = NamespacedKey(InfinityExpansion2.instance, this.lowercase(Locale.getDefault()))
}
