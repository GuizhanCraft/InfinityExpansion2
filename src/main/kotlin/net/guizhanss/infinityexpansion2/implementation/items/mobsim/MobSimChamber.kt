package net.guizhanss.infinityexpansion2.implementation.items.mobsim

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.core.items.attributes.EnergyTickingConsumer
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingMachine
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

class MobSimChamber(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
) : AbstractTickingMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.SINGLE_INPUT, energyPerTick),
    EnergyTickingConsumer, InformationalRecipeDisplayItem {

    override fun getEnergyConsumptionPerTick(): Int {
        TODO("Not yet implemented")
    }

    override fun process(b: Block, menu: BlockMenu): Boolean {
        TODO("Not yet implemented")
    }
}
