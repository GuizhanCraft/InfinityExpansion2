package net.guizhanss.infinityexpansion2.implementation.items.gears

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable
import io.github.thebusybiscuit.slimefun4.core.attributes.Soulbound
import io.github.thebusybiscuit.slimefun4.core.handlers.BowShootHandler
import io.github.thebusybiscuit.slimefun4.implementation.items.weapons.ExplosiveBow
import net.guizhanss.infinityexpansion2.utils.buildPotionEffect
import org.bukkit.Effect
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class InfinityBow(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
) : ExplosiveBow(itemGroup, itemStack, recipe), NotPlaceable, Soulbound {
    init {
        setRecipeType(recipeType)
    }

    override fun onShoot(): BowShootHandler {
        val explosive = super.onShoot()
        return BowShootHandler { e, target ->
            explosive.onHit(e, target)

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
}
