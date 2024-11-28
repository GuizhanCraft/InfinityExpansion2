@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.seggan.sf4k.item.builder.asMaterialType
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import net.guizhanss.guizhanlib.minecraft.utils.MinecraftVersionUtil
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingMachine
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import net.guizhanss.infinityexpansion2.utils.getString
import net.guizhanss.infinityexpansion2.utils.hasData
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.setString
import net.guizhanss.infinityexpansion2.utils.valueOfOrNull
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

class StoneworksFactory(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    val speed: Int,
    energyPerTick: Int,
) : AbstractTickingMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.STONEWORKS_FACTORY, energyPerTick),
    InformationalRecipeDisplayItem {

    override fun setup(preset: BlockMenuPreset) {
        super.setup(preset)
        preset.drawBackground(CHANGE_ITEM, CHOICE_INFO_SLOTS)
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
            menu.replaceExistingItem(slot, menu.getChoice(index).item)
            menu.addMenuClickHandler(slot) { _, _, _, action ->
                val choice = menu.getChoice(index)
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
        menu.setStatus { GuiItems.PRODUCING }

        val tick = InfinityExpansion2.sfTickCount() / getCustomTickRate() % (CHOICE_SLOTS.size + 1)
        if (tick == CHOICE_SLOTS.size) {
            menu.pushItem(Material.COBBLESTONE.toItem(speed), ITEM_SLOTS[0])
        } else {
            process(menu, tick)
        }

        return true
    }

    private fun BlockMenu.getChoice(index: Int) =
        location.getString("choice$index", Choice.NONE.name)
            .let { valueOfOrNull<Choice>(it) ?: Choice.NONE }

    private fun BlockMenu.setChoice(index: Int, choice: Choice) {
        location.setString("choice$index", choice.name)
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
        val targetItem = (choice.map[currentItem.type] ?: currentItem.type).toItem(speed)
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

    override fun getDefaultDisplayRecipes(): List<ItemStack?> {
        val result = mutableListOf<ItemStack?>()
        Choice.entries.forEach {
            if (it == Choice.NONE) return@forEach
            result.addAll(listOf(it.item, null))

            it.map.entries.forEach { (input, output) ->
                result.add(input.toItem())
                result.add(output.toItem())
            }

            result.addAll(listOf(null, null))
        }
        return result
    }

    override fun getInformationalItems() = listOf(
        GuiItems.tickRate(getCustomTickRate()),
        GuiItems.energyConsumptionPerTick(getEnergyConsumptionPerTick()),
    )

    override fun getDividerItem() = GuiItems.RECIPES

    companion object {

        private val CHOICE_INFO_SLOTS = intArrayOf(2, 4, 6)
        private val CHOICE_SLOTS = intArrayOf(11, 13, 15)
        private val ITEM_SLOTS = intArrayOf(10, 12, 14)

        private val CHANGE_ITEM = InfinityExpansion2.localization.getGuiItem(
            Material.YELLOW_STAINED_GLASS_PANE.asMaterialType(),
            "sw_change"
        )
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
            Material.ANDESITE, buildMap {
                put(Material.COBBLESTONE, Material.ANDESITE)
                put(Material.ANDESITE, Material.DIORITE)
                put(Material.DIORITE, Material.GRANITE)

                if (MinecraftVersionUtil.isAtLeast(20)) {
                    put(Material.SAND, Material.SUSPICIOUS_SAND)
                    put(Material.GRAVEL, Material.SUSPICIOUS_GRAVEL)
                }
            }
        );

        val item: ItemStack
            get() = InfinityExpansion2.localization.getGuiItem(
                material.asMaterialType(),
                "sw_${name}"
            )
    }
}
