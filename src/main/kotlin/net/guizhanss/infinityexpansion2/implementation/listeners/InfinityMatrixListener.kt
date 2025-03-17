package net.guizhanss.infinityexpansion2.implementation.listeners

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import net.guizhanss.guizhanlib.kt.minecraft.extensions.isAir
import net.guizhanss.guizhanlib.kt.slimefun.extensions.isSlimefunItem
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.items.tools.InfinityMatrix
import net.guizhanss.infinityexpansion2.implementation.tasks.InfinityMatrixTask
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerDropItemEvent

class InfinityMatrixListener(plugin: InfinityExpansion2) : Listener {

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    @EventHandler
    fun onDropItem(e: PlayerDropItemEvent) {
        val item = e.itemDrop.itemStack
        val p = e.player

        // check if player is being tracked
        if (!InfinityMatrixTask.ACTIVE_PLAYERS.contains(p.uniqueId)) return

        // if they drop items in these modes, remove tracking
        if (p.gameMode == GameMode.CREATIVE || p.gameMode == GameMode.SPECTATOR) {
            InfinityMatrixTask.ACTIVE_PLAYERS.remove(p.uniqueId)
            return
        }

        if (item.isAir() || SlimefunItem.getByItem(item) !is InfinityMatrix) return

        // check if there are any other matrix in the inventory
        var found = false
        p.inventory.contents.firstOrNull { it.isSlimefunItem<InfinityMatrix>() }?.let { found = true }

        if (!found) {
            InfinityMatrixTask.ACTIVE_PLAYERS.remove(p.uniqueId)
            InfinityExpansion2.localization.sendMessage(p, "infinity-matrix.flight-disabled")
            p.allowFlight = false
            p.isFlying = false
        }
    }
}
