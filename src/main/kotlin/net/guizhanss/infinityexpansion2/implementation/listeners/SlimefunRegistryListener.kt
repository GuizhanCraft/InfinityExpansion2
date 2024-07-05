@file:Suppress("unused")

package net.guizhanss.infinityexpansion2.implementation.listeners

import io.github.thebusybiscuit.slimefun4.api.events.SlimefunItemRegistryFinalizedEvent
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.items.tools.Oscillator
import net.guizhanss.infinityexpansion2.utils.bukkitext.isAir
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItemStack
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class SlimefunRegistryListener(plugin: InfinityExpansion2) : Listener {
    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    @EventHandler
    @Suppress("unused_parameter")
    fun onLoad(e: SlimefunItemRegistryFinalizedEvent) {
        // load oscillators
        InfinityExpansion2.debug("Loading oscillators...")
        InfinityExpansion2.configService.quarryOcsillators.forEach { (id, chance) ->
            InfinityExpansion2.debug("Loading oscillator: $id")
            // id check
            id.toItemStack().apply { if (isAir) return@forEach }

            // chance check
            if (chance <= 0 || chance > 1) return@forEach

            InfinityExpansion2.debug("Registering...")
            Oscillator.register(id)
        }
    }
}
