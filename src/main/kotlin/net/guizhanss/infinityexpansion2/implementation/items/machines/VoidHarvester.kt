package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingMachine
import net.guizhanss.infinityexpansion2.utils.getInt
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.setInt
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

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
        val progress = b.getInt("progress")

        // has reached the total progress
        if (progress >= totalProgress) {
            val output = IEItems.VOID_BIT

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
        location.setInt("progress", progress)
        setStatus(GuiItems.progressBar(progress, totalProgress))
    }

    override fun getRecipeSectionLabel(p: Player) = InfinityExpansion2.integrationService.getLore(p, "info")


    override fun getDefaultDisplayRecipes() = listOf(IEItems.VOID_BIT)

    override fun getInformationalItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyConsumptionPerTick(getEnergyConsumptionPerTick()),
        GuiItems.increaseProgress(speed),
        GuiItems.totalProgress(totalProgress),
    )

    override fun getDividerItem() = GuiItems.PRODUCES
}
