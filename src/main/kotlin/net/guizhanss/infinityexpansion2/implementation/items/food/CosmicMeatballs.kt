package net.guizhanss.infinityexpansion2.implementation.items.food

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem
import net.guizhanss.infinityexpansion2.utils.bukkitext.buildPotionEffect
import net.guizhanss.infinityexpansion2.utils.bukkitext.getPotionEffectType
import org.bukkit.inventory.ItemStack

class CosmicMeatballs(
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
        p.addPotionEffect(buildPotionEffect("strength", 5 * 60 * 20, 1))
    }
}
