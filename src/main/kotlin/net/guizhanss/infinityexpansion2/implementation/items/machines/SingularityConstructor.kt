package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.core.IERegistry
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingMachine
import net.guizhanss.infinityexpansion2.implementation.items.materials.Singularity
import net.guizhanss.infinityexpansion2.utils.bukkitext.dropItem
import net.guizhanss.infinityexpansion2.utils.getInt
import net.guizhanss.infinityexpansion2.utils.getString
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.items.toDisplayItem
import net.guizhanss.infinityexpansion2.utils.setInt
import net.guizhanss.infinityexpansion2.utils.setString
import org.bukkit.block.Block
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack

class SingularityConstructor(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    val speed: Int,
    energyPerTick: Int,
) : AbstractTickingMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.SINGLE_INPUT_OUTPUT, energyPerTick),
    InformationalRecipeDisplayItem {

    override fun onBreak(e: BlockBreakEvent, menu: BlockMenu) {
        super.onBreak(e, menu)
        val l = menu.location
        val progress = menu.getProgress()
        val target = menu.getTarget()
        if (progress > 0 && target != null) {
            val singularity = getSingularity(target)
            if (singularity != null) {
                val entry = singularity.ingredients.entries.first { it.value == 1 }
                entry.key.dropItem(l, progress)
            }
        }
    }

    override fun onNewInstance(menu: BlockMenu, b: Block) {
        // reset the progress if there is no target
        if (menu.getTarget() == null && menu.getProgress() > 0) {
            menu.setProgress(0)
        }

        // set the target display item
        val singularity = getSingularity(menu.getTarget())
        if (singularity != null) {
            menu.setTargetItem(singularity.item)
        }
    }

    override fun process(b: Block, menu: BlockMenu): Boolean {
        val input = menu.getItemInSlot(inputSlots[0])
        val inputTemplate = input?.clone()?.apply { amount = 1 }
        val target = menu.getTarget()

        if (target == null || getSingularity(target) == null) {
            // not valid target, probably new machine
            return menu.checkNewSingularity(inputTemplate)
        }

        // target is valid
        var progress = menu.getProgress()

        // allow user to change singularity type
        if (progress == 0) {
            return menu.checkNewSingularity(inputTemplate)
        }

        val singularity = getSingularity(target)!!
        if (progress >= singularity.totalProgress) {
            // finish
            if (!menu.fits(singularity.item, *outputSlots)) {
                menu.setStatus { GuiItems.NO_ROOM }
                return false
            }

            progress -= singularity.totalProgress
            menu.pushItem(singularity.item.clone(), *outputSlots)
        } else {
            // check input and add progress
            if (singularity != getSingularity(inputTemplate)) {
                menu.setStatus { GuiItems.INVALID_INPUT }
                return false
            }

            menu.consumeItem(inputSlots[0], speed)
            progress += (singularity.ingredients[inputTemplate] ?: 0) * speed
        }

        menu.setProgress(progress)
        menu.setStatus { GuiItems.progressBar(progress, singularity.totalProgress) }
        return true
    }

    private fun BlockMenu.checkNewSingularity(input: ItemStack?): Boolean {
        val singularity = getSingularity(input) ?: run {
            setStatus { GuiItems.INVALID_INPUT }
            return false
        }

        // set target and progress
        val progress = singularity.ingredients[input] ?: 0
        setTarget(singularity.id)
        setProgress(progress)
        setTargetItem(singularity.item)
        setStatus { GuiItems.progressBar(progress, singularity.totalProgress) }

        return true
    }

    private fun BlockMenu.getTarget() = location.getString(TARGET)

    private fun BlockMenu.setTargetItem(item: ItemStack = ChestMenuUtils.getBackground()) = replaceExistingItem(
        TARGET_ITEM_DISPLAY_SLOT, item.toDisplayItem()
    )

    private fun BlockMenu.setTarget(target: String) = location.setString(TARGET, target)

    private fun BlockMenu.getProgress() = location.getInt(PROGRESS)

    private fun BlockMenu.setProgress(progress: Int) = location.setInt(PROGRESS, progress)

    private fun getSingularity(item: ItemStack?): Singularity? {
        if (item == null) return null
        return IERegistry.singularityIngredientMap[item]
    }

    private fun getSingularity(id: String?): Singularity? {
        return IERegistry.singularities.find { it.id == id }
    }

    override fun getInformationalItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyConsumptionPerTick(getEnergyConsumptionPerTick()),
    )

    companion object {

        private const val PROGRESS = "progress"
        private const val TARGET = "target"
        private const val TARGET_ITEM_DISPLAY_SLOT = 5
    }
}
