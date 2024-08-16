package net.guizhanss.infinityexpansion2.core.items.handlers

import io.github.thebusybiscuit.slimefun4.api.exceptions.IncompatibleItemHandlerException
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import org.bukkit.Material
import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntityDamageByEntityEvent
import java.util.Optional

/**
 * This [BowShootHandler] can be applied to a bow or a crossbow.
 */
fun interface BowShootHandler : ItemHandler {

    fun onHit(e: EntityDamageByEntityEvent, entity: LivingEntity)

    override fun validate(item: SlimefunItem): Optional<IncompatibleItemHandlerException> {
        if (item.item.type != Material.BOW && item.item.type != Material.CROSSBOW) {
            return Optional.of(
                IncompatibleItemHandlerException(
                    "Only bows and crossbows can have a BowShootHandler.", item,
                    this
                )
            )
        }

        return Optional.empty()
    }

    override fun getIdentifier() = BowShootHandler::class.java
}
