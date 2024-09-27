package net.guizhanss.infinityexpansion2.implementation.items.mobsim

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.IERegistry
import net.guizhanss.infinityexpansion2.core.items.attributes.EnergyTickingConsumer
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingMachine
import net.guizhanss.infinityexpansion2.utils.bukkitext.isAir
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.items.isSlimefunItem
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack
import java.util.logging.Level

class MobSimChamber(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
) : AbstractTickingMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.SINGLE_INPUT, energyPerTick),
    EnergyTickingConsumer, InformationalRecipeDisplayItem {

    override fun postRegister() {
        super.postRegister()

        // check if consumption is too large (about 2.1b / 100)
        if (getEnergyConsumptionPerTick() > 20_000_000) {
            InfinityExpansion2.log(Level.WARNING, "Invalid item settings for $id:")
            InfinityExpansion2.log(Level.WARNING, "The energy consumption is too large.")
            InfinityExpansion2.log(Level.WARNING, "This field is reset to default, please update the config.")
            energyPerTickSetting.update(energyPerTickSetting.defaultValue)
        }
    }

    override fun getCapacity() = getEnergyConsumptionPerTick() * 100

    override fun process(b: Block, menu: BlockMenu): Boolean {
        val input = menu.getItemInSlot(layout.inputSlots[0])
        if (input.isAir || !input.isSlimefunItem<MobDataCard>()) {
            menu.setStatus { GuiItems.INVALID_INPUT }
            return false
        }

        val id = MobDataCard.getMobDataId(input) ?: run {
            menu.setStatus { GuiItems.INVALID_INPUT }
            return false
        }

        val props = IERegistry.mobDataCards[id] ?: run {
            menu.setStatus { GuiItems.INVALID_INPUT }
            return false
        }


        return false
    }

    override fun getInformationalItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyConsumptionPerTick(getEnergyConsumptionPerTick())
    )
}
