package net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import net.guizhanss.guizhanlib.slimefun.machines.MenuBlock
import net.guizhanss.infinityexpansion2.core.items.attributes.EnergyActionConsumer
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import org.bukkit.inventory.ItemStack

/**
 * An action machine runs and consumes energy when an action is performed.
 */
abstract class AbstractActionMachine(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    protected val layout: MenuLayout,
    energyPerUse: Int,
) : MenuBlock(itemGroup, itemStack, recipeType, recipe), EnergyNetComponent, EnergyActionConsumer {

    // should avoid accessing this directly
    protected val energyPerUseSetting = IntRangeSetting(this, "energy-per-use", 1, energyPerUse, 1_000_000_000)

    init {
        addItemSetting(energyPerUseSetting)
    }

    override fun getEnergyConsumptionPerAction() = energyPerUseSetting.value

    override fun setup(preset: BlockMenuPreset) {
        layout.setupPreset(preset)
    }

    override fun getInputSlots() = layout.inputSlots

    override fun getOutputSlots() = layout.outputSlots

    override fun getCapacity() = getEnergyConsumptionPerAction()

    override fun getEnergyComponentType() = EnergyNetComponentType.CONSUMER

    protected fun BlockMenu.setStatus(itemStack: () -> ItemStack) {
        if (hasViewer()) {
            replaceExistingItem(layout.statusSlot, itemStack())
        }
    }
}
