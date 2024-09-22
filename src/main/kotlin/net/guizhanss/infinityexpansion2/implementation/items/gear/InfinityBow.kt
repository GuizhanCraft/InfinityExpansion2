@file:Suppress("deprecation", "UnstableApiUsage")

package net.guizhanss.infinityexpansion2.implementation.items.gear

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable
import io.github.thebusybiscuit.slimefun4.core.attributes.Soulbound
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect
import net.guizhanss.guizhanlib.minecraft.utils.compatibility.ParticleX
import net.guizhanss.infinityexpansion2.core.items.attributes.BowItem
import net.guizhanss.infinityexpansion2.core.items.handlers.BowShootHandler
import net.guizhanss.infinityexpansion2.utils.bukkitext.buildPotionEffect
import org.bukkit.Bukkit
import org.bukkit.Effect
import org.bukkit.Material
import org.bukkit.SoundCategory
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector

/**
 * Explosive and freezing bow/crossbow.
 */
class InfinityBow(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
) : SlimefunItem(itemGroup, itemStack, recipeType, recipe), BowItem, NotPlaceable, Soulbound {

    private val explosionRangeSetting = IntRangeSetting(this, "explosion-range", 1, 3, Integer.MAX_VALUE)

    init {
        addItemSetting(explosionRangeSetting)
        addItemHandler(onShoot())
    }

    private fun onShoot(): BowShootHandler {
        return BowShootHandler { e, target ->
            // explosive part
            target.world.spawnParticle(ParticleX.EXPLOSION, target.location, 1)
            SoundEffect.EXPLOSIVE_BOW_HIT_SOUND.playAt(target.location, SoundCategory.PLAYERS)
            val radius = explosionRangeSetting.value.toDouble()

            target.world.getNearbyEntities(target.location, radius, radius, radius) { canDamage(it) }
                .forEach { nearby ->
                    val entity = nearby as LivingEntity

                    val distanceVector =
                        entity.location.toVector().subtract(target.location.toVector()).add(Vector(0.0, 0.75, 0.0))

                    val distanceSquared = distanceVector.lengthSquared()
                    val damage = e.damage * (1 - (distanceSquared / (2 * radius * radius)))

                    if (entity.uniqueId != target.uniqueId) {
                        val event = EntityDamageByEntityEvent(
                            e.damager,
                            entity,
                            EntityDamageEvent.DamageCause.ENTITY_EXPLOSION,
                            damage
                        )
                        Bukkit.getPluginManager().callEvent(event)

                        if (!event.isCancelled) {
                            distanceVector.setY(0.75)
                            val knockback = distanceVector.normalize().multiply(2)
                            entity.velocity = entity.velocity.add(knockback)
                            entity.damage(event.damage)
                        }
                    }
                }

            // freeze part
            if (target is Player) {
                if (target.isBlocking && e.finalDamage <= 0) {
                    return@BowShootHandler
                }

                target.setFreezeTicks(60)
            }

            target.world.playEffect(target.location, Effect.STEP_SOUND, Material.ICE)
            target.world.playEffect(target.eyeLocation, Effect.STEP_SOUND, Material.ICE)
            target.addPotionEffect(buildPotionEffect("slowness", 20 * 2, 10))
            target.addPotionEffect(buildPotionEffect("jump_boost", 20 * 2, -10))
        }
    }

    private fun canDamage(entity: Entity) =
        entity is LivingEntity && entity !is ArmorStand && entity.isValid()

}
