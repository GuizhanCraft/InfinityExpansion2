package net.guizhanss.infinityexpansion2.implementation.items.food

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.guizhanlib.kt.minecraft.extensions.buildPotionEffect
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class UltimateStew(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
) : InfinityFood(itemGroup, itemStack, recipeType, recipe) {

    override fun applyEffects(p: Player) {
        p.addPotionEffect(buildPotionEffect("regeneration", 5 * 60 * 20, 1))
    }
}
