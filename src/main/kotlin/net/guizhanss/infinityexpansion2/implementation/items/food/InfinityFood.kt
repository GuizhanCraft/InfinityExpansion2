package net.guizhanss.infinityexpansion2.implementation.items.food

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem
import net.guizhanss.guizhanlib.kt.minecraft.extensions.getPotionEffectType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

abstract class InfinityFood(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
) : SimpleSlimefunItem<ItemConsumptionHandler>(itemGroup, itemStack, recipeType, recipe) {

    override fun getItemHandler() = ItemConsumptionHandler { _, p, _ ->
        p.foodLevel = 20
        p.saturation = 20f
        p.exhaustion = 0f
        p.removePotionEffect(getPotionEffectType("hunger")!!)
        applyEffects(p)
    }

    abstract fun applyEffects(p: Player)
}
