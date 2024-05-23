@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.utils.getData
import net.guizhanss.infinityexpansion2.utils.hasData
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.items.MaterialType
import net.guizhanss.infinityexpansion2.utils.setData
import net.guizhanss.infinityexpansion2.utils.valueOfOrNull
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class StoneworksFactory(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    val speed: Int,
    energyPerTick: Int,
) : AbstractMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.STONEWORKS_FACTORY), RecipeDisplayItem {
    private val tickRateSetting = IntRangeSetting(this, "tick-rate", 1, 1, 120)
    private val energyPerTickSetting = IntRangeSetting(this, "energy-per-tick", 1, energyPerTick, 1_000_000_000)

    init {
        addItemSetting(tickRateSetting, energyPerTickSetting)
    }

    override fun getEnergyConsumptionPerTick() = energyPerTickSetting.value

    override fun setup(preset: BlockMenuPreset) {
        super.setup(preset)
        preset.drawBackground(GuiItems.SW_CHANGE, CHOICE_INFO_SLOTS)
    }

    override fun onNewInstance(menu: BlockMenu, b: Block) {
        super.onNewInstance(menu, b)

        if (b.hasData("choice0")) {
            // new block, init choices
            menu.setChoice(0, Choice.NONE)
            menu.setChoice(1, Choice.NONE)
            menu.setChoice(2, Choice.NONE)
        }

        // set the choice item
        CHOICE_SLOTS.forEachIndexed { index, slot ->
            val choice = menu.getChoice(index)
            menu.replaceExistingItem(slot, choice.item)
            menu.addMenuClickHandler(slot) { _, _, _, action ->
                val nextChoiceIndex =
                    (choice.ordinal + action.choiceOffset() + Choice.entries.size) % Choice.entries.size
                val nextChoice = Choice.entries[nextChoiceIndex]

                menu.replaceExistingItem(slot, nextChoice.item)
                menu.setChoice(index, nextChoice)
                false
            }
        }
    }

    override fun process(b: Block, menu: BlockMenu): Boolean {
        if (InfinityExpansion2.sfTickCount() % tickRateSetting.value != 0) return true

        if (menu.hasViewer()) {
            menu.replaceExistingItem(layout.statusSlot, GuiItems.PRODUCING)
        }

        val tick = InfinityExpansion2.sfTickCount() / tickRateSetting.value % (CHOICE_SLOTS.size + 1)
        if (tick == CHOICE_SLOTS.size) {
            menu.pushItem(ItemStack(Material.COBBLESTONE, speed), ITEM_SLOTS[0])
        } else {
            process(menu, tick)
        }

        return true
    }

    private fun BlockMenu.getChoice(index: Int) =
        location.getData("choice$index", Choice.NONE.name)
            .let { valueOfOrNull<Choice>(it) ?: Choice.NONE }

    private fun BlockMenu.setChoice(index: Int, choice: Choice) {
        location.setData("choice$index", choice.name)
    }

    private fun ClickAction.choiceOffset() = if (isRightClicked) -1 else 1

    private fun process(menu: BlockMenu, tick: Int) {
        val slot = ITEM_SLOTS[tick]
        val currentItem = menu.getItemInSlot(slot)
        // no need to move item if item's amount is less than speed
        if (currentItem == null || currentItem.amount < speed) return

        val nextSlot = ITEM_SLOTS.getOrElse(tick + 1) { layout.outputSlots[0] }
        val nextItem = menu.getItemInSlot(nextSlot)
        val choice = menu.getChoice(tick)
        val targetItem = ItemStack(choice.map[currentItem.type] ?: currentItem.type, speed)
        // check if the item in the next slot isn't empty and isn't the target item
        if (nextItem != null && !SlimefunUtils.isItemSimilar(nextItem, targetItem, false)) return

        if (nextItem == null) {
            // no item in the next slot, just move the item
            menu.consumeItem(slot, speed)
            menu.pushItem(targetItem, nextSlot)
        } else {
            // similar item, calculate the amount to move
            val amount = if (nextItem.amount + speed <= targetItem.maxStackSize) {
                speed
            } else {
                targetItem.maxStackSize - nextItem.amount
            }

            // item has maxed amount, no more room
            if (amount == 0) return

            menu.consumeItem(slot, amount)
            nextItem.amount += amount
        }
    }

    override fun getRecipeSectionLabel(p: Player) = InfinityExpansion2.integrationService.getLore(p, "info")

    override fun getDisplayRecipes(): List<ItemStack?> {
        val result = mutableListOf<ItemStack?>(
            GuiItems.tickRate(tickRateSetting.value),
            GuiItems.energyConsumption(energyPerTickSetting.value),
            GuiItems.RECIPES,
            GuiItems.RECIPES,
        )
        Choice.entries.forEach {
            if (it == Choice.NONE) return@forEach
            result.addAll(listOf(it.item, null))

            it.map.entries.forEach { (input, output) ->
                result.addAll(
                    listOf(
                        ItemStack(input),
                        ItemStack(output),
                    )
                )
            }

            result.addAll(listOf(null, null))
        }
        return result
    }

    companion object {
        private val CHOICE_INFO_SLOTS = intArrayOf(2, 4, 6)
        private val CHOICE_SLOTS = intArrayOf(11, 13, 15)
        private val ITEM_SLOTS = intArrayOf(10, 12, 14)
    }

    private enum class Choice(private val material: Material, val map: Map<Material, Material>) {
        NONE(
            Material.BARRIER, mapOf()
        ),
        FURNACE(
            Material.FURNACE, mapOf(
                Material.COBBLESTONE to Material.STONE,
                Material.STONE to Material.SMOOTH_STONE,
                Material.SAND to Material.GLASS,
                Material.STONE_BRICKS to Material.CRACKED_STONE_BRICKS,
            )
        ),
        CRUSH(
            Material.DIAMOND_PICKAXE, mapOf(
                Material.COBBLESTONE to Material.GRAVEL,
                Material.GRAVEL to Material.SAND,
            )
        ),
        COMPACT(
            Material.PISTON, mapOf(
                Material.STONE to Material.STONE_BRICKS,
                Material.GRANITE to Material.POLISHED_GRANITE,
                Material.DIORITE to Material.POLISHED_DIORITE,
                Material.ANDESITE to Material.POLISHED_ANDESITE,
                Material.SAND to Material.SANDSTONE,
            )
        ),
        TRANSFORM(
            Material.ANDESITE, mapOf(
                Material.COBBLESTONE to Material.ANDESITE,
                Material.ANDESITE to Material.DIORITE,
                Material.DIORITE to Material.GRANITE,
            )
        );

        val item: ItemStack
            get() = InfinityExpansion2.localization.getGuiItem(
                MaterialType.Material(material),
                "sw_${name}"
            )
    }
}
