package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.schntgaispock.slimehud.util.HudBuilder
import io.github.schntgaispock.slimehud.waila.HudRequest
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.guizhanlib.kt.slimefun.items.toItem
import net.guizhanss.guizhanlib.kt.slimefun.utils.getInt
import net.guizhanss.guizhanlib.kt.slimefun.utils.setInt
import net.guizhanss.infinityexpansion2.core.items.annotations.HudProvider
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingMachine
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

@HudProvider
class VoidHarvester(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    val speed: Int,
    energyPerTick: Int,
) : AbstractTickingMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.OUTPUT_ONLY_ONE_ROW, energyPerTick),
    InformationalRecipeDisplayItem {

    private val totalProgressSetting = IntRangeSetting(this, "total-progress", 1, 1024, 1_000_000_000)

    init {
        addItemSetting(totalProgressSetting)
    }

    val totalProgress: Int get() = totalProgressSetting.value

    override fun process(b: Block, menu: BlockMenu): Boolean {
        val progress = b.getInt(PROGRESS)

        // has reached the total progress
        if (progress >= totalProgress) {
            val output = IEItems.VOID_BIT.toItem()

            if (!menu.fits(output, *layout.outputSlots)) {
                menu.setStatus { GuiItems.NO_ROOM }
                return false
            }

            menu.pushItem(output.clone(), *layout.outputSlots)
            menu.setProgress(speed + progress - totalProgress)
        } else {
            menu.setProgress(progress + speed)
        }
        return true
    }

    private fun BlockMenu.setProgress(progress: Int) {
        location.setInt(PROGRESS, progress)
        setStatus { GuiItems.progressBar(progress, totalProgress) }
    }

    override fun getDefaultDisplayRecipes() = listOf(IEItems.VOID_BIT.item())

    override fun getInformationalItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyConsumptionPerTick(getEnergyConsumptionPerTick()),
        GuiItems.increaseProgress(speed),
        GuiItems.totalProgress(totalProgress),
    )

    override fun getDividerItem() = GuiItems.PRODUCES

    companion object {

        // block storage keys
        private const val PROGRESS = "progress"

        @Suppress("unused")
        fun hudHandler(request: HudRequest): String {
            val loc = request.location
            val machine = request.slimefunItem as VoidHarvester

            val progress = loc.getInt(PROGRESS)
            val totalProgress = machine.totalProgress

            return buildString {
                append("$progress / $totalProgress")
                append("&7 | ")

                append(HudBuilder.getProgressBar(progress, totalProgress))
                append("&7 | ")

                append(HudBuilder.formatEnergyStored(machine.getCharge(loc), machine.capacity))
            }
        }
    }
}
