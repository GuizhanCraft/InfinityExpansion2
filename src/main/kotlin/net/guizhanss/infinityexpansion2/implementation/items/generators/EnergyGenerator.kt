@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.items.generators

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
import org.bukkit.Location
import org.bukkit.inventory.ItemStack

class EnergyGenerator(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    private val type: GeneratorType,
    defaultEnergyProduction: Int,
) : SlimefunItem(itemGroup, itemStack, recipeType, recipe), EnergyNetProvider {
    private val energyProductionSetting =
        IntRangeSetting(this, "energy-production", 1, defaultEnergyProduction, 16_777_215) // 2 ^ 24 - 1

    // notes for other developers:
    // use reflection to change this value to a positive integer,
    // to override the user defined value
    private var energyProductionOverride = -1

    init {
        addItemSetting(energyProductionSetting)
        addItemHandler(getBlockClickHandler())
    }

    override fun getCapacity() = energyProductionSetting.value * 128

    private fun getEnergyProduction() =
        if (energyProductionOverride > 0) energyProductionOverride
        else energyProductionSetting.value

    override fun getGeneratedOutput(l: Location, data: Config) =
        type.generate(l.world!!, l.block, getEnergyProduction())

    private fun getBlockClickHandler() = BlockUseHandler {
        // TODO: Implement right click info query
    }
}
