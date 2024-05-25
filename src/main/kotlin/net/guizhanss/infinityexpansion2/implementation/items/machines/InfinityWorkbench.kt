package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import net.guizhanss.guizhanlib.slimefun.machines.MenuBlock
import net.guizhanss.infinityexpansion2.core.attributes.EnergyOneTimeConsumer
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

class InfinityWorkbench(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerUse: Int,
) : MenuBlock(itemGroup, itemStack, recipeType, recipe), EnergyNetComponent, EnergyOneTimeConsumer, RecipeDisplayItem {
    private val energyPerTickSetting = IntRangeSetting(this, "energy-per-tick", 1, energyPerUse, 1_000_000_000)

    init {
        addItemSetting(energyPerTickSetting)
    }

    override fun getEnergyComponentType() = EnergyNetComponentType.CONSUMER

    override fun getCapacity() = energyPerTickSetting.value

    override fun getEnergyConsumptionPerAction() = energyPerTickSetting.value

    override fun getInputSlots() = intArrayOf()

    override fun getOutputSlots() = intArrayOf()

    override fun setup(preset: BlockMenuPreset) {
        // TODO
    }

    override fun onNewInstance(menu: BlockMenu, b: Block) {
        // TODO
    }

    override fun getDisplayRecipes() = listOf(
        GuiItems.energyConsumptionPerUse(getEnergyConsumptionPerAction())
    )

    companion object {
        private val BACKGROUND = arrayOf(9, 36, 37, 38, 45, 46, 47)
        private val INPUT_BORDER = arrayOf(2, 10, 11)
        private val OUTPUT_BORDER = arrayOf(18, 20, 27, 28, 29)
        private const val OUTPUT_SLOT = 19
        private val RECIPE_AREA = arrayOf(
            // @formatter:off
            3, 4, 5, 6, 7, 8,
            12, 13, 14, 15, 16, 17,
            21, 22, 23, 24, 25, 26,
            30, 31, 32, 33, 34, 35,
            39, 40, 41, 42, 43, 44,
            48, 49, 50, 51, 52, 53,
            // @formatter:on
        )
        private val INFO_SLOTS = arrayOf(36, 37, 38, 45, 46, 47)
    }
}
