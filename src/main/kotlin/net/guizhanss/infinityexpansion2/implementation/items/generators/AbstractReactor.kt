@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.items.generators

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.guizhanlib.slimefun.machines.MenuBlock
import net.guizhanss.infinityexpansion2.core.items.attributes.CustomTickRateMachine
import net.guizhanss.infinityexpansion2.core.items.attributes.EnergyProducer
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.utils.getInt
import net.guizhanss.infinityexpansion2.utils.setInt
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

abstract class AbstractReactor(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    defaultProduction: Int,
) : MenuBlock(itemGroup, itemStack, recipeType, recipe), EnergyNetProvider, CustomTickRateMachine, EnergyProducer,
    InformationalRecipeDisplayItem {

    private val tickRateSetting = IntRangeSetting(this, "tick-rate", 1, 1, 3600)
    private val energyProductionSetting =
        IntRangeSetting(this, "energy-production", 1, defaultProduction, Int.MAX_VALUE)

    init {
        addItemSetting(tickRateSetting, energyProductionSetting)
    }

    override fun onNewInstance(menu: BlockMenu, b: Block) {
        b.location.setProgress(0)
    }

    override fun getOutputSlots() = intArrayOf()

    override fun getCustomTickRate() = tickRateSetting.value

    override fun getCapacity() = getEnergyProduction()

    override fun getEnergyProduction() = energyProductionSetting.value

    protected fun Location.setProgress(progress: Int) {
        setInt(PROGRESS_KEY, progress)
    }

    protected fun Location.getProgress() = getInt(PROGRESS_KEY)

    companion object {

        private const val PROGRESS_KEY = "progress"
    }
}
