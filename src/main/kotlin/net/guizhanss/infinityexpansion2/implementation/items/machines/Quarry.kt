package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.DoubleRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingMachine
import net.guizhanss.infinityexpansion2.implementation.items.tools.Oscillator
import net.guizhanss.infinityexpansion2.utils.bukkitext.isAir
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItemStack
import net.guizhanss.infinityexpansion2.utils.bukkitext.withAmount
import net.guizhanss.infinityexpansion2.utils.bukkitext.withName
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

class Quarry(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
    val speed: Int, // the amount of output
    chance: Double
) : AbstractTickingMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.SINGLE_INPUT, energyPerTick),
    InformationalRecipeDisplayItem {

    private val chanceSetting = DoubleRangeSetting(this, "chance", 0.0, chance, 1.0)

    init {
        addItemSetting(chanceSetting)
    }

    override fun setup(preset: BlockMenuPreset) {
        super.setup(preset)
        preset.drawBackground(GuiItems.OSCILLATOR_SLOT, layout.inputBorder)
    }

    override fun process(b: Block, menu: BlockMenu): Boolean {
        menu.setStatus { GuiItems.PRODUCING }
        if (!shouldProduce()) return true

        produce(menu)?.let {
            menu.pushItem(it, *outputSlots)
        }

        return true
    }

    private fun shouldProduce() =
        InfinityExpansion2.sfTickCount() % (getCustomTickRate() * InfinityExpansion2.configService.quarryInterval) == 0

    private fun produce(menu: BlockMenu): ItemStack? {
        val env = menu.location.world.environment
        val pool = InfinityExpansion2.configService.quarryPools[env] ?: return null
        if (Random.nextDouble() <= chanceSetting.value) { // base chance
            val oscillatorItem = menu.getItemInSlot(inputSlots[0])
            // check if oscillator doesn't exist, not applicable to current pool, or it doesn't activate
            return if (oscillatorItem == null || !Oscillator.isOscillator(oscillatorItem)
                || Oscillator.getTarget(oscillatorItem) !in pool.products.keys
                || Random.nextDouble() > Oscillator.getChance(oscillatorItem)
            ) {
                // normal product from pool
                pool.getRandomProduct().toItemStack().withAmount(speed)
            } else {
                // oscillator product
                Oscillator.getTarget(oscillatorItem)!!.toItemStack().withAmount(speed)
            }
        }

        // should produce base product
        val baseProduct = pool.baseProduct.toItemStack().let {
            if (it.isAir) Material.COBBLESTONE.toItem() else it
        }
        return baseProduct.clone().apply { amount = speed }
    }

    override fun getDefaultDisplayRecipes(): List<ItemStack?> {
        val result = mutableListOf<ItemStack?>()
        InfinityExpansion2.configService.quarryPools.forEach { (env, pool) ->
            result.add(
                when (env) {
                    World.Environment.NORMAL -> GuiItems.WORLD_NORMAL
                    World.Environment.NETHER -> GuiItems.WORLD_NETHER
                    World.Environment.THE_END -> GuiItems.WORLD_THE_END
                    else -> Material.BARRIER.toItem().withName("&cUnknown")
                }
            )
            result.add(pool.baseProduct.toItemStack().withAmount(speed))
            pool.products.forEach { (product, amount) ->
                for (i in 1..amount) {
                    result.add(product.toItemStack().withAmount(speed))
                }
            }
            if (pool.products.size % 2 != 0) {
                result.add(null)
            }
            result.addAll(listOf(null, null))
        }
        return result
    }

    override fun getInformationalItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyConsumptionPerTick(getEnergyConsumptionPerTick()),
        GuiItems.chance(chanceSetting.value),
        GuiItems.outputInterval(InfinityExpansion2.configService.quarryInterval),
    )

    override fun getDividerItem() = GuiItems.RECIPES
}
