package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import net.guizhanss.guizhanlib.slimefun.machines.TickingMenuBlock
import net.guizhanss.infinityexpansion2.api.items.interfaces.EnergyTickingConsumer
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

abstract class AbstractMachine(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    protected val layout: MenuLayout
) : TickingMenuBlock(itemGroup, itemStack, recipeType, recipe), EnergyNetComponent, EnergyTickingConsumer {
    override fun setup(preset: BlockMenuPreset) {
        layout.setupPreset(preset)
    }

    override fun getInputSlots() = layout.inputSlots

    override fun getOutputSlots() = layout.outputSlots

    override fun getCapacity() = getEnergyConsumptionPerTick() * 2

    override fun getEnergyComponentType() = EnergyNetComponentType.CONSUMER

    override fun tick(b: Block, menu: BlockMenu) {
        if (getCharge(menu.location) < getEnergyConsumptionPerTick()) {
            if (menu.hasViewer()) {
                menu.replaceExistingItem(layout.statusSlot, GuiItems.NO_POWER)
            }
        } else if (process(b, menu)) {
            removeCharge(menu.location, getEnergyConsumptionPerTick())
        }
    }

    protected abstract fun process(b: Block, menu: BlockMenu): Boolean
}
