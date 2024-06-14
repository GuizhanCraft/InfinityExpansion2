@file:Suppress("unused")

package net.guizhanss.infinityexpansion2.implementation.listeners

import io.github.thebusybiscuit.slimefun4.api.events.SlimefunItemRegistryFinalizedEvent
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.items.tools.Oscillator
import net.guizhanss.infinityexpansion2.utils.isAir
import net.guizhanss.infinityexpansion2.utils.toItemStack
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
            val item = id.toItemStack()
            if (item.isAir()) return@forEach
            InfinityExpansion2.debug("Item is valid")

            // chance check
            if (chance <= 0 || chance > 1) return@forEach
            InfinityExpansion2.debug("Chance is valid")

            Oscillator.register(id)
        }
    }
}
