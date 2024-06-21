package net.guizhanss.infinityexpansion2.core.sound

import org.bukkit.Sound
import org.bukkit.entity.Player

enum class IESound(val sound: Sound, val volume: Float, val pitch: Float) {
    ADVANCED_ANVIL_DENY(Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f),
    ADVANCED_ANVIL_USE(Sound.BLOCK_ANVIL_USE, 1.0f, 1.0f),
    ;

    fun playFor(p: Player) {
        p.playSound(p, sound, volume, pitch)
    }
}
