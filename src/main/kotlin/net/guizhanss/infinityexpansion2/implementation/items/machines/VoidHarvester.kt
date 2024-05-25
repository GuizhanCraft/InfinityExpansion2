package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.IEItems
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
) : AbstractMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.OUTPUT_ONLY_ONE_ROW, energyPerTick), RecipeDisplayItem {
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
                if (menu.hasViewer()) {
                    menu.replaceExistingItem(layout.statusSlot, GuiItems.NO_SPACE)
                }
                return false
            }

            if (shouldRun()) {
                menu.pushItem(output.clone(), *layout.outputSlots)

                menu.setProgress(speed + progress - totalProgress)
            }
        } else if (shouldRun()) {
            menu.setProgress(progress + speed)
        }
        return true
    }

    private fun BlockMenu.setProgress(progress: Int) {
        location.setInt("progress", progress)
        if (hasViewer()) {
            replaceExistingItem(layout.statusSlot, GuiItems.progressBar(progress, totalProgress))
        }
    }

    override fun getRecipeSectionLabel(p: Player) = InfinityExpansion2.integrationService.getLore(p, "info")

    override fun getDisplayRecipes() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyConsumptionPerTick(getEnergyConsumptionPerTick()),
        GuiItems.increaseProgress(speed),
        GuiItems.totalProgress(totalProgress),
        GuiItems.PRODUCES,
        GuiItems.PRODUCES,
        IEItems.VOID_BIT
    )
}
