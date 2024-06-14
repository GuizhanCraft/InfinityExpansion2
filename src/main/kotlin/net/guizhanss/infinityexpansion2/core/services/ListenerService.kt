package net.guizhanss.infinityexpansion2.core.services

import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.listeners.SlimefunRegistryListener
import net.guizhanss.infinityexpansion2.implementation.listeners.TranslationsLoadListener
import net.guizhanss.infinityexpansion2.implementation.listeners.VeinMinerListener

class ListenerService(plugin: InfinityExpansion2) {
    init {
        SlimefunRegistryListener(plugin)
        TranslationsLoadListener(plugin)
        VeinMinerListener(plugin)
    }
}
