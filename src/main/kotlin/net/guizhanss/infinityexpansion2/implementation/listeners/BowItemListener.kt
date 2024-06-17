package net.guizhanss.infinityexpansion2.implementation.listeners

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.BowItem
import net.guizhanss.infinityexpansion2.core.items.handlers.BowShootHandler
import org.bukkit.entity.Arrow
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.entity.ProjectileHitEvent
import java.util.UUID

class BowItemListener(plugin: InfinityExpansion2) : Listener {
    private val projectiles = mutableMapOf<UUID, SlimefunItem>()

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    @EventHandler
    fun onBowUse(e: EntityShootBowEvent) {
        if (e.entity is Player && e.projectile is Arrow) {
            val bow = SlimefunItem.getByItem(e.bow)

            if (bow is BowItem) {
                projectiles[e.projectile.uniqueId] = bow
            }
        }
    }

    @EventHandler
    fun onArrowHit(e: ProjectileHitEvent) {
        Slimefun.runSync({
            if (e.entity.isValid && e.entity is Arrow) {
                projectiles.remove(e.entity.uniqueId)
            }
        }, 4L)
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onArrowHit(e: EntityDamageByEntityEvent) {
        if (e.damager is Arrow && e.entity is LivingEntity && e.cause != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            val bow = projectiles.remove(e.damager.uniqueId)

            if (!e.isCancelled && bow != null) {
                bow.callItemHandler(BowShootHandler::class.java) { it.onHit(e, e.entity as LivingEntity) }
            }
        }
    }
}
