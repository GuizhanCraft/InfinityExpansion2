package net.guizhanss.infinityexpansion2.implementation.tasks

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.items.tools.InfinityMatrix
import org.bukkit.Bukkit
import org.bukkit.GameMode
import java.util.UUID

class InfinityMatrixTask : Runnable {

    init {
        InfinityExpansion2.scheduler().repeat(20 * 20, this)
    }

    override fun run() {
        for (p in Bukkit.getOnlinePlayers()) {
            val uuid = p.uniqueId

            // only check for active players
            if (!ACTIVE_PLAYERS.contains(uuid)) continue

            // if active players are in creative or spectator mode, no need to track them
            if (p.gameMode == GameMode.CREATIVE || p.gameMode == GameMode.SPECTATOR) {
                ACTIVE_PLAYERS.remove(uuid)
                continue
            }

            // check if player's inv still have matrix
            var found = false
            p.inventory.contents.firstOrNull {
                it != null && !it.type.isAir && SlimefunItem.getByItem(it) is InfinityMatrix
            }?.let { found = true }

            // not found, disable flying
            if (!found) {
                ACTIVE_PLAYERS.remove(uuid)
                p.allowFlight = false
                p.isFlying = false
            }
        }
    }

    companion object {

        val ACTIVE_PLAYERS = mutableSetOf<UUID>()
    }
}
