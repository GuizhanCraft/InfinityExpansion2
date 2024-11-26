package net.guizhanss.infinityexpansion2.implementation.items.machines

import com.google.common.collect.MapDifference
import com.google.common.collect.Maps
import io.github.seggan.sf4k.item.builder.asMaterialType
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.core.sound.IESound
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.AbstractTickingActionMachine
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import net.guizhanss.infinityexpansion2.utils.bukkitext.withName
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.items.toDisplayItem
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.EnchantmentStorageMeta
import org.bukkit.inventory.meta.ItemMeta


class AdvancedAnvil(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerUse: Int,
) : AbstractTickingActionMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.ADVANCED_ANVIL, energyPerUse),
    InformationalRecipeDisplayItem {

    override fun setup(preset: BlockMenuPreset) {
        super.setup(preset)
        preset.drawBackground(ANVIL_BASE_ITEM, ANVIL_BASE_SLOTS)
    }

    override fun onNewInstance(menu: BlockMenu, b: Block) {
        menu.addMenuClickHandler(layout.statusSlot) { pl, _, _, _ ->
            craft(menu, pl)
            false
        }
    }

    override fun process(b: Block, menu: BlockMenu): Boolean {
        val input1 = menu.getItemInSlot(inputSlots[0])
        val input2 = menu.getItemInSlot(inputSlots[1])

        if (input1 == null || input2 == null || (input2.type != Material.ENCHANTED_BOOK && input1.type != input2.type)) {
            menu.setStatus { GuiItems.INVALID_INPUT }
            return false
        }

        val output = getOutput(input1, input2)
        if (output == null) {
            menu.setStatus { GuiItems.INVALID_INPUT }
        } else {
            menu.setStatus { output.toDisplayItem() }
        }

        return false
    }

    private fun craft(menu: BlockMenu, p: Player) {
        // energy check
        if (getCharge(menu.location) < getEnergyConsumptionPerAction()) return

        val input1 = menu.getItemInSlot(inputSlots[0])
        val input2 = menu.getItemInSlot(inputSlots[1])
        val item1 = SlimefunItem.getByItem(input1)
        val item2 = SlimefunItem.getByItem(input2)

        if (input1 == null || input2 == null || (input2.type != Material.ENCHANTED_BOOK && input1.type != input2.type)) {
            IESound.ADVANCED_ANVIL_DENY.playFor(p)
            return
        }

        if (item1 != null && !item1.isEnchantable) {
            IESound.ADVANCED_ANVIL_DENY.playFor(p)
            InfinityExpansion2.integrationService.sendMessage(p, "advanced-anvil.input1-not-enchantable")
            return
        }

        if (item2 != null && !item2.isDisenchantable) {
            IESound.ADVANCED_ANVIL_DENY.playFor(p)
            InfinityExpansion2.integrationService.sendMessage(p, "advanced-anvil.input2-not-disenchantable")
            return
        }

        val output = getOutput(input1, input2)

        if (output == null) {
            IESound.ADVANCED_ANVIL_DENY.playFor(p)
            InfinityExpansion2.integrationService.sendMessage(p, "advanced-anvil.no-output")
            return
        }

        if (!menu.fits(output, *outputSlots)) {
            IESound.ADVANCED_ANVIL_DENY.playFor(p)
            InfinityExpansion2.integrationService.sendMessage(p, "no-room")
            return
        }

        IESound.ADVANCED_ANVIL_USE.playFor(p)
        input1.amount--
        input2.amount--
        menu.pushItem(output, *outputSlots)
        removeCharge(menu.location, getEnergyConsumptionPerAction())
    }

    private fun getOutput(input1: ItemStack, input2: ItemStack): ItemStack? {
        val enchant1 = getEnchantments(input1.itemMeta)
        val enchant2 = getEnchantments(input2.itemMeta)
        if (enchant1.isEmpty() && enchant2.isEmpty()) return null
        return combineEnchantments(Maps.difference(enchant1, enchant2), input1, input2)
    }

    private fun getEnchantments(meta: ItemMeta): Map<Enchantment, Int> {
        if (meta is EnchantmentStorageMeta) {
            if (meta.hasStoredEnchants()) {
                return meta.storedEnchants
            }
        } else if (meta.hasEnchants()) {
            return meta.enchants
        }
        return emptyMap()
    }

    private fun combineEnchantments(
        diff: MapDifference<Enchantment, Int>,
        item1: ItemStack,
        item2: ItemStack
    ): ItemStack? {
        val item = item1.clone()
        item.amount = 1
        val meta = item.itemMeta

        val enchants = mutableMapOf<Enchantment, Int>()
        var changed = false

        // upgrades (same enchant and level)
        diff.entriesInCommon().forEach { (key, value) ->
            if (MAX_LEVELS[key]?.let { value < it } == true) {
                enchants[key] = value + 1
                changed = true
            }
        }

        // override (same enchant different level)
        diff.entriesDiffering().forEach { (key, value) ->
            if (value.rightValue() > value.leftValue()) {
                enchants[key] = value.rightValue()
                changed = true
            }
        }

        val bookToTool = item2.type == Material.ENCHANTED_BOOK && item1.type != Material.ENCHANTED_BOOK

        // unique (different enchants from 2nd item)
        diff.entriesOnlyOnRight().forEach { (key, value) ->
            if (bookToTool && !key.canEnchantItem(item)) {
                return@forEach
            }
            enchants[key] = value
            changed = true
        }

        return if (changed) {
            if (meta is EnchantmentStorageMeta) {
                enchants.forEach { (key, value) ->
                    meta.addStoredEnchant(key, value, true)
                }
                item.itemMeta = meta
            } else {
                enchants.forEach { (key, value) ->
                    item.addUnsafeEnchantment(key, value)
                }
            }
            item
        } else {
            null
        }
    }

    override fun getInformationalItems() = listOf(
        GuiItems.energyConsumptionPerUse(getEnergyConsumptionPerAction())
    )

    override fun getDefaultDisplayRecipes(): List<ItemStack?> {
        val result = mutableListOf<ItemStack?>()
        InfinityExpansion2.configService.advancedAnvilMaxLevels.forEach { (enchantment, maxLevel) ->
            if (maxLevel <= 0) return@forEach

            val item = InfinityExpansion2.localization.getGuiItem(
                Material.ENCHANTED_BOOK.asMaterialType(),
                "aa_max_enchantment_level"
            )
            item.addUnsafeEnchantment(enchantment, maxLevel)
            result.add(item)
        }
        return result
    }

    companion object {

        private val ANVIL_BASE_ITEM = Material.BLACK_STAINED_GLASS_PANE.toItem().withName(" ")
        private val ANVIL_BASE_SLOTS = intArrayOf(
            30, 31, 32, 39, 41, 47, 48, 49, 50, 51
        )
        private val MAX_LEVELS = InfinityExpansion2.configService.advancedAnvilMaxLevels.filterValues { it > 0 }
    }
}
