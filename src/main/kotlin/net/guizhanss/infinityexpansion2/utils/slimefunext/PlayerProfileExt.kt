package net.guizhanss.infinityexpansion2.utils.slimefunext

import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import net.guizhanss.infinityexpansion2.core.items.attributes.ProtectionType
import net.guizhanss.infinityexpansion2.core.items.attributes.ProtectiveArmorExtra
import org.bukkit.NamespacedKey

/**
 * The adapted version for IE2's extra protection types.
 */
fun PlayerProfile.hasFullProtectionAgainst(type: ProtectionType): Boolean {
    var armorCount = 0
    var setId: NamespacedKey? = null

    armor.forEach { hashedPiece ->
        val armorPiece = hashedPiece.item
        if (armorPiece.isEmpty || armorPiece.get() !is ProtectiveArmorExtra) {
            return@forEach
        }
        val armor = armorPiece.get() as ProtectiveArmorExtra
        if (type !in armor.getAllProtectionTypes()) {
            return@forEach
        }
        if (!armor.isFullSetRequired) {
            return true
        } else if (setId == null || setId == armor.armorSetId) {
            armorCount++
            setId = armor.armorSetId
        }
    }

    return armorCount == 4
}
