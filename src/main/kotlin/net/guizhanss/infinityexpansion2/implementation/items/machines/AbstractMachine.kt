package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import net.guizhanss.guizhanlib.slimefun.machines.AbstractMachineBlock
import net.guizhanss.infinityexpansion2.api.items.interfaces.EnergyTickingConsumer
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.inventory.ItemStack

abstract class AbstractMachine(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    private val layout: MenuLayout
) : AbstractMachineBlock(itemGroup, itemStack, recipeType, recipe), EnergyTickingConsumer {
    override fun setup(preset: BlockMenuPreset) {
        layout.setupPreset(preset)
    }

    override fun getEnergyConsumptionPerTick() = energyPerTick

    override fun getInputSlots() = layout.inputSlots

    override fun getOutputSlots() = layout.outputSlots

    override fun getStatusSlot() = layout.statusSlot

    override fun getNoEnergyItem() = GuiItems.NO_POWER
}
