package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.DoubleRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import net.guizhanss.guizhanlib.kt.minecraft.extensions.isAir
import net.guizhanss.guizhanlib.kt.minecraft.extensions.toItem
import net.guizhanss.guizhanlib.kt.minecraft.items.edit
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.CustomWikiItem
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingMachine
import net.guizhanss.infinityexpansion2.implementation.items.tools.Oscillator
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItemStack
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
    InformationalRecipeDisplayItem, CustomWikiItem {

    override val wikiUrl = "machines/quarry"

    private val chanceSetting = DoubleRangeSetting(this, "chance", 0.0, chance, 1.0)
    private val enabledInOverworldSetting = ItemSetting(this, "enabled-in.overworld", true)
    private val enabledInNetherSetting = ItemSetting(this, "enabled-in.nether", true)
    private val enabledInEndSetting = ItemSetting(this, "enabled-in.end", true)

    init {
        addItemSetting(chanceSetting, enabledInOverworldSetting, enabledInNetherSetting, enabledInEndSetting)
    }

    override fun setup(preset: BlockMenuPreset) {
        super.setup(preset)
        preset.drawBackground(GuiItems.OSCILLATOR_SLOT, layout.inputBorder)
    }

    override fun process(b: Block, menu: BlockMenu): Boolean {
        // check if the machine is enabled in the current world type
        val isEnabled = when (menu.location.world.environment) {
            World.Environment.NORMAL -> enabledInOverworldSetting.value
            World.Environment.NETHER -> enabledInNetherSetting.value
            World.Environment.THE_END -> enabledInEndSetting.value
            else -> true
        }
        if (!isEnabled) {
            menu.setStatus { GuiItems.DISABLED_IN_WORLD }
            return false
        }

        menu.setStatus { GuiItems.PRODUCING }
        if (!shouldProduce()) return true

        produce(menu)?.let {
            menu.pushItem(it, *outputSlots)
        }

        return true
    }

    private fun shouldProduce() =
        InfinityExpansion2.sfTickCount() % (getCustomTickRate() * InfinityExpansion2.configService.quarryInterval.value) == 0

    private fun produce(menu: BlockMenu): ItemStack? {
        val env = menu.location.world.environment
        val pool = InfinityExpansion2.configService.quarryPools.value[env] ?: return null
        if (Random.nextDouble() <= chanceSetting.value) { // base chance
            val oscillatorItem = menu.getItemInSlot(inputSlots[0])
            // check if oscillator doesn't exist, not applicable to current pool, or it doesn't activate
            return if (oscillatorItem == null || !Oscillator.isOscillator(oscillatorItem)
                || Oscillator.getTarget(oscillatorItem) !in pool.products.keys
                || Random.nextDouble() > Oscillator.getChance(oscillatorItem)
            ) {
                // normal product from pool
                pool.getRandomProduct().toItemStack().edit { amount(speed) }
            } else {
                // oscillator product
                Oscillator.getTarget(oscillatorItem)!!.toItemStack().edit { amount(speed) }
            }
        }

        // should produce base product
        val baseProduct = pool.baseProduct.toItemStack().let {
            if (it.isAir()) Material.COBBLESTONE.toItem() else it
        }
        return baseProduct.clone().apply { amount = speed }
    }

    override fun getDefaultDisplayRecipes(): List<ItemStack?> {
        val result = mutableListOf<ItemStack?>()
        InfinityExpansion2.configService.quarryPools.value.forEach { (env, pool) ->
            result.add(
                when (env) {
                    World.Environment.NORMAL -> GuiItems.WORLD_NORMAL
                    World.Environment.NETHER -> GuiItems.WORLD_NETHER
                    World.Environment.THE_END -> GuiItems.WORLD_THE_END
                    else -> Material.BARRIER.toItem().edit { name("&cUnknown") }
                }
            )
            result.add(pool.baseProduct.toItemStack().edit { amount(speed) })
            pool.products.forEach { (product, amount) ->
                result.add(product.toItemStack().edit {
                    amount(amount)
                    lore("&7$amount")
                })
            }
            if (pool.products.size % 2 != 0) {
                result.add(null)
            }
            result.addAll(listOf(null, null))
        }
        return result
    }

    override fun getInfoItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyConsumptionPerTick(getEnergyConsumptionPerTick()),
        GuiItems.chance(chanceSetting.value),
        GuiItems.outputInterval(InfinityExpansion2.configService.quarryInterval.value),
    )

    override fun getDividerItem() = GuiItems.RECIPES
}
