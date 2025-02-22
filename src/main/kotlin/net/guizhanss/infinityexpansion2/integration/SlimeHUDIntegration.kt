@file:Suppress("UNCHECKED_CAST")

package net.guizhanss.infinityexpansion2.integration

import io.github.schntgaispock.slimehud.SlimeHUD
import io.github.schntgaispock.slimehud.waila.HudRequest
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.annotations.HudProvider
import java.util.function.Function
import java.util.logging.Level
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.functions
import kotlin.reflect.full.hasAnnotation

object SlimeHUDIntegration { init {
    InfinityExpansion2.log(Level.INFO, "Initializing SlimeHUD integration...")
    Slimefun.getRegistry().enabledSlimefunItems.forEach { sfItem ->
        if (!sfItem::class.hasAnnotation<HudProvider>()) return@forEach

        // find the companion function
        val companion = sfItem::class.companionObject ?: run {
            report("${sfItem::class.simpleName} does not have a companion object.")
            return@forEach
        }
        val handler = companion.functions.find {
            it.name == "hudHandler" && it.parameters.size == 2 && it.parameters[1].type.classifier == HudRequest::class && it.returnType.classifier == String::class
        } ?: run {
            report("${sfItem::class.simpleName} does not have a companion function named 'hudHandler'.")
            return@forEach
        }

        SlimeHUD.getHudController().registerCustomHandler(sfItem.javaClass) { request ->
            handler.call(sfItem::class.companionObjectInstance, request) as String
        }
    }
}
}

private fun report(message: String) {
    InfinityExpansion2.log(Level.SEVERE, "An error has occurred while registering SlimeHUD integration:")
    InfinityExpansion2.log(Level.SEVERE, message)
    InfinityExpansion2.log(Level.SEVERE, "Please report this to ${InfinityExpansion2.instance.bugTrackerURL}")
}
