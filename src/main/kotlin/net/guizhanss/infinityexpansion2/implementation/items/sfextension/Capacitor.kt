package net.guizhanss.infinityexpansion2.implementation.items.sfextension

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import org.bukkit.inventory.ItemStack
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.Capacitor as SlimefunCapacitor

class Capacitor(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    capacity: Int,
) : SlimefunCapacitor(itemGroup, capacity, itemStack, recipeType, recipe)
