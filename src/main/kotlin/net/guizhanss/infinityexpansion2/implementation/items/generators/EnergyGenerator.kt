package net.guizhanss.infinityexpansion2.implementation.items.generators

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
import net.guizhanss.guizhanlib.common.utils.StringUtil
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.CustomTickRateMachine
import net.guizhanss.infinityexpansion2.core.items.attributes.EnergyProducer
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.utils.constant.Strings
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.slimefunext.powerPerTickToSecond
import org.bukkit.Location
import org.bukkit.inventory.ItemStack

class EnergyGenerator(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    private val type: GeneratorType,
    defaultProduction: Int,
) : SlimefunItem(itemGroup, itemStack, recipeType, recipe), EnergyNetProvider, CustomTickRateMachine, EnergyProducer,
    InformationalRecipeDisplayItem {

    private val tickRateSetting = IntRangeSetting(this, "tick-rate", 1, 1, 3600)
    private val energyProductionSetting =
        IntRangeSetting(this, "energy-production", 1, defaultProduction, 16_777_215) // 2 ^ 24 - 1

    init {
        addItemSetting(tickRateSetting, energyProductionSetting)
        addItemHandler(onBlockClick())
    }

    override fun getCapacity() = energyProductionSetting.value * 128

    override fun getEnergyProduction() = energyProductionSetting.value

    override fun getCustomTickRate() = tickRateSetting.value

    override fun getGeneratedOutput(l: Location, data: Config) =
        type.generate(l.world!!, l.block, getEnergyProduction())

    private fun onBlockClick() = BlockUseHandler { e ->
        if (e.clickedBlock.isEmpty) return@BlockUseHandler
        val sfItem = e.slimefunBlock.get()
        if (sfItem !is EnergyGenerator) return@BlockUseHandler
        val p = e.player
        val block = e.clickedBlock.get()
        val integration = InfinityExpansion2.integrationService

        p.sendMessage("", integration.getTranslatedItemName(p, sfItem.item))
        integration.sendMessage(p, "generators.type", StringUtil.humanize(type.name))
        integration.sendMessage(p, "generators.tick-rate", getCustomTickRate())

        val generation = type.generate(block.world, block, getEnergyProduction())
        integration.sendMessage(
            p,
            "generators.working",
            if (generation > 0) Strings.CHECK else Strings.CROSS
        )
        if (generation > 0) {
            integration.sendMessage(
                p,
                "generators.production",
                generation,
                powerPerTickToSecond(generation, getCustomTickRate())
            )
        } else {
            integration.sendMessage(
                p,
                "generators.expected-production",
                getEnergyProduction(),
                powerPerTickToSecond(getEnergyProduction(), getCustomTickRate())
            )
        }
    }

    override fun getInformationalItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyProductionPerTick(getEnergyProduction())
    )
}
