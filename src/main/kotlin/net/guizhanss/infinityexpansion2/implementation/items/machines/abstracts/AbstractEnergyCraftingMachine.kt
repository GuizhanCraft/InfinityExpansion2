package net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.core.items.attributes.EnergyActionConsumer
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * A crafting machine that consumes energy on every craft.
 */
abstract class AbstractEnergyCraftingMachine(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    layout: MenuLayout,
    energyPerUse: Int,
) : AbstractCraftingMachine(itemGroup, itemStack, recipeType, recipe, layout), EnergyNetComponent,
    EnergyActionConsumer {

    // should avoid accessing this directly
    protected val energyPerUseSetting = IntRangeSetting(this, "energy-per-use", 1, energyPerUse, 1_000_000_000)

    init {
        addItemSetting(energyPerUseSetting)
    }

    override fun getEnergyConsumptionPerAction() = energyPerUseSetting.value

    override fun getCapacity() = getEnergyConsumptionPerAction()

    override fun getEnergyComponentType() = EnergyNetComponentType.CONSUMER

    override fun craft(menu: BlockMenu, p: Player) {
        if (getCharge(menu.location) < getEnergyConsumptionPerAction()) {
            menu.setStatus { GuiItems.NO_POWER }
        } else {
            super.craft(menu, p)
        }
    }

    override fun onSuccessfulCraft(menu: BlockMenu, output: ItemStack, p: Player) {
        removeCharge(menu.location, getEnergyConsumptionPerAction())
        super.onSuccessfulCraft(menu, output, p)
    }
}
