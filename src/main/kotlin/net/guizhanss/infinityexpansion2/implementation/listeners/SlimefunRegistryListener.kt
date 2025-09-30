@file:Suppress("unused")

package net.guizhanss.infinityexpansion2.implementation.listeners

import io.github.thebusybiscuit.slimefun4.api.events.SlimefunItemRegistryFinalizedEvent
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import net.guizhanss.guizhanlib.kt.minecraft.extensions.isAir
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.debug.DebugCase
import net.guizhanss.infinityexpansion2.core.items.attributes.DelayedTaskItem
import net.guizhanss.infinityexpansion2.implementation.items.tools.Oscillator
import net.guizhanss.infinityexpansion2.implementation.setup.MobSimulationSetup
import net.guizhanss.infinityexpansion2.utils.Debug
import net.guizhanss.infinityexpansion2.utils.items.toItemStack
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import java.util.logging.Level

class SlimefunRegistryListener(plugin: InfinityExpansion2) : Listener {
    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @Suppress("unused_parameter")
    fun onLoad(e: SlimefunItemRegistryFinalizedEvent) {
        InfinityExpansion2.log(Level.INFO, "Executing delayed registering tasks...")

        // load oscillators
        loadQuarryOscillators()

        // load mob simulation
        MobSimulationSetup

        // delayed task items
        Slimefun.getRegistry().enabledSlimefunItems.forEach { item ->
            if (item !is DelayedTaskItem) return@forEach

            if (item.isSync) {
                InfinityExpansion2.scheduler().run(item::delayedTask)
            } else {
                InfinityExpansion2.scheduler().runAsync(item::delayedTask)
            }
        }
    }

    private fun loadQuarryOscillators() {
        InfinityExpansion2.log(Level.INFO, "Loading oscillators...")
        InfinityExpansion2.configService.quarryOscillators.value.forEach { (id, chance) ->
            Debug.log(DebugCase.OSCILLATOR, "Loading oscillator: $id")
            // id check
            id.toItemStack().apply { if (isAir()) return@forEach }

            // chance check
            if (chance <= 0 || chance > 1) return@forEach

            Debug.log(DebugCase.OSCILLATOR,"Registering...")
            Oscillator.register(id)
        }
    }
}
