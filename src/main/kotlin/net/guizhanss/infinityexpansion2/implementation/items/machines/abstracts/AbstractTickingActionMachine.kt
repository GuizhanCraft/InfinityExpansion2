package net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import net.guizhanss.guizhanlib.slimefun.machines.TickingMenuBlock
import net.guizhanss.infinityexpansion2.core.attributes.CustomTickRateMachine
import net.guizhanss.infinityexpansion2.core.attributes.EnergyActionConsumer
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

/**
 * A ticking machine consumes energy every tick, and runs with a custom tick rate.
 */
abstract class AbstractTickingActionMachine(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    protected val layout: MenuLayout,
    energyPerUse: Int,
    tickRate: Int = 1,
) : TickingMenuBlock(itemGroup, itemStack, recipeType, recipe), EnergyNetComponent, EnergyActionConsumer,
    CustomTickRateMachine {

    private val tickRateSetting = IntRangeSetting(this, "tick-rate", 1, tickRate, 3600)
    private val energyPerUseSetting = IntRangeSetting(this, "energy-per-use", 1, energyPerUse, 1_000_000_000)

    init {
        addItemSetting(tickRateSetting, energyPerUseSetting)
    }

    override fun getCustomTickRate() = tickRateSetting.value

    override fun getEnergyConsumptionPerAction() = energyPerUseSetting.value

    override fun setup(preset: BlockMenuPreset) {
        layout.setupPreset(preset)
    }

    override fun getInputSlots() = layout.inputSlots

    override fun getOutputSlots() = layout.outputSlots

    override fun getCapacity() = getEnergyConsumptionPerAction() * 2

    override fun getEnergyComponentType() = EnergyNetComponentType.CONSUMER

    override fun tick(b: Block, menu: BlockMenu) {
        if (!shouldRun()) return
        if (getCharge(menu.location) < getEnergyConsumptionPerAction()) {
            menu.setStatus(GuiItems.NO_POWER)
        } else if (process(b, menu)) {
            removeCharge(menu.location, getEnergyConsumptionPerAction())
        }
    }

    protected abstract fun process(b: Block, menu: BlockMenu): Boolean

    protected fun BlockMenu.setStatus(itemStack: ItemStack) {
        if (hasViewer()) {
            replaceExistingItem(layout.statusSlot, itemStack)
        }
    }
}
