package net.guizhanss.infinityexpansion2.implementation.listeners

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.FallDamageProtection
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class ArmorItemListener(plugin: InfinityExpansion2) : Listener {
    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    @EventHandler
    fun onFall(e: EntityDamageEvent) {
        if (e.entity !is Player) return
        val p = e.entity as Player

        val boots = SlimefunItem.getByItem(p.inventory.boots) ?: return
        if (boots.canUse(p, true)) return

        if (boots is FallDamageProtection) {
            e.isCancelled = true
        }
    }
}
