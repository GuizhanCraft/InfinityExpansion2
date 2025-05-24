package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.schntgaispock.slimehud.util.HudBuilder
import io.github.schntgaispock.slimehud.waila.HudRequest
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.guizhanlib.kt.minecraft.extensions.drop
import net.guizhanss.guizhanlib.kt.slimefun.utils.getBlockMenu
import net.guizhanss.guizhanlib.kt.slimefun.utils.getInt
import net.guizhanss.guizhanlib.kt.slimefun.utils.getString
import net.guizhanss.guizhanlib.kt.slimefun.utils.setInt
import net.guizhanss.guizhanlib.kt.slimefun.utils.setString
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.IERegistry
import net.guizhanss.infinityexpansion2.core.items.annotations.HudProvider
import net.guizhanss.infinityexpansion2.core.items.attributes.CustomWikiItem
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingMachine
import net.guizhanss.infinityexpansion2.implementation.items.materials.Singularity
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.items.toDisplayItem
import org.bukkit.block.Block
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack

@HudProvider
class SingularityConstructor(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    val speed: Int,
    energyPerTick: Int,
) : AbstractTickingMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.SINGLE_INPUT_OUTPUT, energyPerTick),
    InformationalRecipeDisplayItem, CustomWikiItem {

    override val wikiUrl = "machines/singularity-constructor"

    override fun onBreak(e: BlockBreakEvent, menu: BlockMenu) {
        super.onBreak(e, menu)
        val l = menu.location
        val progress = menu.getProgress()
        val target = menu.getTarget()
        if (progress > 0 && target != null) {
            val singularity = getSingularityById(target)
            if (singularity != null) {
                val entry = singularity.ingredients.entries.first { it.value == 1 }
                entry.key.drop(l, progress)
            }
        }
    }

    override fun onNewInstance(menu: BlockMenu, b: Block) {
        // reset the progress if there is no target
        if (menu.getTarget() == null && menu.getProgress() > 0) {
            menu.setProgress(0)
        }

        menu.updateProgressDisplay()
    }

    override fun process(b: Block, menu: BlockMenu): Boolean {
        val input = menu.getItemInSlot(inputSlots[0])
        val inputTemplate = input?.clone()?.apply { amount = 1 }
        val singularity = getSingularityById(menu.getTarget()) ?: return menu.checkNewSingularity(inputTemplate)

        // target is valid
        var progress = menu.getProgress()

        // allow user to change singularity type
        if (progress == 0) {
            return menu.checkNewSingularity(inputTemplate)
        }

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
            if (singularity != getSingularityByIngredient(inputTemplate)) {
                menu.setStatus { GuiItems.INVALID_INPUT }
                return false
            }

            // calculate the amount of items to consume
            val amount = input.amount.coerceAtMost(speed)
            menu.consumeItem(inputSlots[0], amount)
            progress += singularity.getIngredientProgress(inputTemplate) * amount
        }

        menu.setProgress(progress)
        menu.updateProgressDisplay()
        menu.setStatus { GuiItems.PRODUCING }
        return true
    }

    private fun BlockMenu.checkNewSingularity(input: ItemStack?): Boolean {
        val singularity = getSingularityByIngredient(input) ?: run {
            setStatus { GuiItems.INVALID_INPUT }
            return false
        }

        // set target and progress
        val progress = singularity.getIngredientProgress(input) * speed
        consumeItem(inputSlots[0], speed)
        setTarget(singularity.id)
        setProgress(progress)
        updateProgressDisplay()
        setStatus { GuiItems.PRODUCING }

        return true
    }

    private fun BlockMenu.updateProgressDisplay() {
        if (!hasViewer()) return
        val progress = getProgress()
        val singularity = getSingularityById(getTarget())
        if (progress == 0 || singularity == null) {
            replaceExistingItem(PROGRESS_SLOT, ChestMenuUtils.getBackground())
            replaceExistingItem(TARGET_ITEM_DISPLAY_SLOT, ChestMenuUtils.getBackground())
        } else {
            replaceExistingItem(PROGRESS_SLOT, GuiItems.progressBar(progress, singularity.totalProgress))
            replaceExistingItem(TARGET_ITEM_DISPLAY_SLOT, singularity.item.toDisplayItem())
        }
    }

    override fun getInfoItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyConsumptionPerTick(getEnergyConsumptionPerTick()),
    )

    companion object {

        // block storage keys
        private const val PROGRESS = "progress"
        private const val TARGET = "target"

        // gui slots
        private const val PROGRESS_SLOT = 3
        private const val TARGET_ITEM_DISPLAY_SLOT = 5

        private fun BlockMenu.getTarget() = location.getString(TARGET)
        private fun BlockMenu.setTarget(target: String) = location.setString(TARGET, target)

        private fun BlockMenu.getProgress() = location.getInt(PROGRESS)
        private fun BlockMenu.setProgress(progress: Int) = location.setInt(PROGRESS, progress)

        private fun getSingularityByIngredient(item: ItemStack?): Singularity? {
            if (item == null) return null
            return IERegistry.singularityIngredientMap.entries.firstOrNull { (ingredient, _) ->
                SlimefunUtils.isItemSimilar(item, ingredient, false)
            }?.value
        }

        private fun getSingularityById(id: String?): Singularity? {
            return IERegistry.singularities.find { it.id == id }
        }

        @Suppress("unused")
        fun hudHandler(request: HudRequest): String {
            val loc = request.location
            val menu = loc.getBlockMenu()
            val machine = request.slimefunItem as SingularityConstructor

            val progress = menu.getProgress()

            return buildString {
                if (progress > 0) {
                    val singularity = getSingularityById(menu.getTarget()) ?: return@buildString

                    // target singularity
                    append(
                        InfinityExpansion2.integrationService.getTranslatedItemName(
                            request.player,
                            singularity.item
                        )
                    )
                    append("&7 | ")

                    // progress
                    val totalProgress = singularity.totalProgress
                    append("$progress / $totalProgress")
                    append("&7 | ")

                    // progress bar
                    append(HudBuilder.getProgressBar(progress, totalProgress))
                    append("&7 | ")
                }

                append(HudBuilder.formatEnergyStored(machine.getCharge(loc), machine.capacity))
            }
        }
    }
}
