package net.guizhanss.infinityexpansion2.implementation.items.sfextension

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines.enchanting.AutoEnchanter as SlimefunAutoEnchanter

class AutoEnchanter(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    speed: Int,
    energyPerTick: Int,
) : SlimefunAutoEnchanter(itemGroup, itemStack, recipeType, recipe) {

    init {
        capacity = energyPerTick
        energyConsumption = energyPerTick
        setProcessingSpeed(speed)
    }

    override fun getProgressBar() = Material.NETHERITE_CHESTPLATE.toItem()
}
