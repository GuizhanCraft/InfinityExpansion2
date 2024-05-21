package net.guizhanss.infinityexpansion2.implementation.listeners

import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.slimefuntranslation.api.events.TranslationsLoadEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class TranslationsLoadListener(plugin: InfinityExpansion2) : Listener {
    init {
        if (InfinityExpansion2.integrationService.slimefunTranslationEnabled) {
            plugin.server.pluginManager.registerEvents(this, plugin)
        }
    }

    @EventHandler
    @Suppress("unused_parameter")
    fun onLoad(e: TranslationsLoadEvent) {
        InfinityExpansion2.integrationService.loadTranslations()
    }
}
