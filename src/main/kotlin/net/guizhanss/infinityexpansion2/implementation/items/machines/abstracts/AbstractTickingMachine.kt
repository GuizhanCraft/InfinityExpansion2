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
import net.guizhanss.infinityexpansion2.core.items.attributes.CustomTickRateMachine
import net.guizhanss.infinityexpansion2.core.items.attributes.EnergyTickingConsumer
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

/**
 * A ticking machine consumes energy every tick, and runs with a custom tick rate.
 */
abstract class AbstractTickingMachine(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    protected val layout: MenuLayout,
    energyPerTick: Int,
    tickRate: Int = 1,
) : TickingMenuBlock(itemGroup, itemStack, recipeType, recipe), EnergyNetComponent, EnergyTickingConsumer,
    CustomTickRateMachine {

    // should avoid accessing this directly, use getCustomTickRate() instead
    protected val tickRateSetting = IntRangeSetting(this, "tick-rate", 1, tickRate, 3600)

    // should avoid accessing this directly, use getEnergyConsumptionPerTick() instead
    protected val energyPerTickSetting = IntRangeSetting(this, "energy-per-tick", 1, energyPerTick, 1_000_000_000)

    init {
        addItemSetting(tickRateSetting, energyPerTickSetting)
    }

    override fun getCustomTickRate() = tickRateSetting.value

    override fun getEnergyConsumptionPerTick() = energyPerTickSetting.value

    override fun setup(preset: BlockMenuPreset) {
        layout.setupPreset(preset)
    }

    override fun getInputSlots() = layout.inputSlots

    override fun getOutputSlots() = layout.outputSlots

    override fun getCapacity() = getEnergyConsumptionPerTick() * 2

    override fun getEnergyComponentType() = EnergyNetComponentType.CONSUMER

    override fun tick(b: Block, menu: BlockMenu) {
        if (getCharge(menu.location) < getEnergyConsumptionPerTick()) {
            menu.setStatus { GuiItems.NO_POWER }
        } else if (tickCount % getCustomTickRate() == 0 && process(b, menu)) {
            removeCharge(menu.location, getEnergyConsumptionPerTick())
        }
    }

    protected abstract fun process(b: Block, menu: BlockMenu): Boolean

    protected fun BlockMenu.setStatus(itemStack: () -> ItemStack) {
        if (hasViewer()) {
            replaceExistingItem(layout.statusSlot, itemStack())
        }
    }
}
