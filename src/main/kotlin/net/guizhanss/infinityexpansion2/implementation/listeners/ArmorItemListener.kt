package net.guizhanss.infinityexpansion2.implementation.listeners

import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.ProtectionType
import net.guizhanss.infinityexpansion2.utils.slimefunext.hasFullProtectionAgainst
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
        if (e.entity !is Player || e.cause != EntityDamageEvent.DamageCause.FALL) return
        val p = e.entity as Player
        val pf = PlayerProfile.find(p)
        if (pf.isEmpty) {
            PlayerProfile.request(p)
            return
        }
        val profile = pf.get()
        if (profile.hasFullProtectionAgainst(ProtectionType.FALL)) {
            e.damage = 0.0
        }
    }
}
