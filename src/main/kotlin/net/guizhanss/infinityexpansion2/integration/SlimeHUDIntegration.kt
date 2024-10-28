package net.guizhanss.infinityexpansion2.integration

import io.github.schntgaispock.slimehud.SlimeHUD
import net.guizhanss.infinityexpansion2.implementation.items.mobsim.MobSimulationChamber

object SlimeHUDIntegration {
    init {
        SlimeHUD.getHudController()
            .registerCustomHandler(MobSimulationChamber::class.java, MobSimulationChamber::getHudResponse)
    }
}
