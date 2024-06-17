package net.guizhanss.infinityexpansion2.implementation.items.gear

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectionType
import net.guizhanss.infinityexpansion2.core.items.attributes.FallDamageProtection
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect

class InfinityBoots(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    effects: Array<PotionEffect> = arrayOf(),
    protectionTypes: Array<ProtectionType> = arrayOf(),
) : InfinityArmor(itemGroup, itemStack, recipeType, recipe, effects, protectionTypes), FallDamageProtection
