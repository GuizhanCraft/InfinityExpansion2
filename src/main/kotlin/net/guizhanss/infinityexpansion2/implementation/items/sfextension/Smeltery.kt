package net.guizhanss.infinityexpansion2.implementation.items.sfextension

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines.ElectricSmeltery
import org.bukkit.inventory.ItemStack

class Smeltery(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    speed: Int,
    energyPerTick: Int,
) : ElectricSmeltery(itemGroup, itemStack, recipeType, recipe) {
    init {
        setCapacity(energyPerTick)
        setEnergyConsumption(energyPerTick)
        setProcessingSpeed(speed)
    }
}
