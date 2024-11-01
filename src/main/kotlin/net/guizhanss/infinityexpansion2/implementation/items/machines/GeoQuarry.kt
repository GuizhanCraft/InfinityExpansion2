package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingMachine
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.World.Environment
import org.bukkit.block.Biome
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

class GeoQuarry(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
    outputInterval: Int,
    val speed: Int, // the amount of output
) : AbstractTickingMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.OUTPUT_ONLY, energyPerTick),
    InformationalRecipeDisplayItem {

    private val outputIntervalSetting = IntRangeSetting(this, "output-interval", 1, outputInterval, 3600)

    init {
        addItemSetting(outputIntervalSetting)
    }

    override fun process(b: Block, menu: BlockMenu): Boolean {
        menu.setStatus { GuiItems.PRODUCING }
        if (!shouldProduce()) return true

        produce(menu).let {
            menu.pushItem(it.clone(), *outputSlots)
        }

        return true
    }

    private fun shouldProduce() =
        InfinityExpansion2.sfTickCount() % (getCustomTickRate() * outputIntervalSetting.value) == 0

    private fun produce(menu: BlockMenu): ItemStack {
        val env = menu.location.world.environment
        val biome = menu.location.block.biome

        return geoRecipes.getOrPut(Pair(biome, env)) {
            val pool = mutableListOf<ItemStack>()
            Slimefun.getRegistry().geoResources.values().filter { it.isObtainableFromGEOMiner }.forEach { resource ->
                val supply = resource.getDefaultSupply(env, biome)
                if (supply > 0) {
                    repeat(supply) {
                        pool.add(resource.item)
                    }
                }
            }
            pool
        }.random()
    }

    override fun getDefaultDisplayRecipes() =
        Slimefun.getRegistry().geoResources.values().filter { it.isObtainableFromGEOMiner }
            .map { it.item.clone().apply { amount = speed } }

    override fun getInformationalItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyConsumptionPerTick(getEnergyConsumptionPerTick()),
        GuiItems.outputInterval(outputIntervalSetting.value)
    )

    override fun getDividerItem() = GuiItems.RECIPES

    companion object {

        private val geoRecipes = mutableMapOf<Pair<Biome, Environment>, List<ItemStack>>()
    }
}
