@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.items.tools

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.DistinctiveItem
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.implementation.groups.IEItemGroups
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItemStack
import net.guizhanss.infinityexpansion2.utils.constant.Keys
import net.guizhanss.infinityexpansion2.utils.items.builder.recipes.buildRecipe
import org.bukkit.ChatColor
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * All the oscillators share the same id. The PDC contains the target of the oscillator.
 */
class Oscillator(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    recipeOutput: ItemStack,
) : UnplaceableBlock(itemGroup, itemStack, recipeType, recipe, recipeOutput), DistinctiveItem {

    override fun canStack(meta1: ItemMeta, meta2: ItemMeta) =
        meta1.persistentDataContainer == meta2.persistentDataContainer

    companion object {

        /**
         * Create an oscillator item with specified target.
         */
        fun getItem(target: String): SlimefunItemStack {
            val item = IEItems.OSCILLATOR.clone() as SlimefunItemStack
            val targetItem = target.toItemStack()
            item.type = targetItem.type
            val meta = item.itemMeta

            // set pdc
            PersistentDataAPI.setString(meta, Keys.OSCILLATOR_TARGET, target)

            // set lore
            meta.lore = mutableListOf(
                ChatColor.GRAY.toString() + ItemUtils.getItemName(targetItem),
                ChatColor.GRAY.toString() + String.format("%.2f%%", getChance(target) * 100)
            )

            item.itemMeta = meta
            return item
        }

        /**
         * Check if an [ItemStack] is an oscillator.
         */
        fun isOscillator(item: ItemStack) = SlimefunItem.getByItem(item) is Oscillator

        /**
         * Get the chance of an oscillator.
         */
        fun getChance(target: String): Double = InfinityExpansion2.configService.quarryOscillators[target] ?: 0.0

        /**
         * Get the chance of an oscillator.
         */
        fun getChance(item: ItemStack): Double = getTarget(item)?.let { getChance(it) } ?: 0.0

        /**
         * Get the target of an oscillator.
         */
        fun getTarget(item: ItemStack): String? = PersistentDataAPI.getString(item.itemMeta, Keys.OSCILLATOR_TARGET)

        /**
         * Register an oscillator with specified target.
         */
        fun register(target: String) {
            val targetItem = target.toItemStack()
            val item = getItem(target)
            // TODO: properly handle sf 1.21 changes here
            val sfItem = Oscillator(
                IEItemGroups.TOOLS,
                SlimefunItemStack("${IEItems.prefix}OSCILLATOR_$target", item),
                RecipeType.ENHANCED_CRAFTING_TABLE,
                buildRecipe {
                    +"FI "
                    +"   "
                    +"   "
                    'F' means IEItems.OSCILLATOR_FRAME
                    'I' means targetItem
                },
                item
            )
            sfItem.register(InfinityExpansion2.instance)
            // registering is post setup so need to call load manually.
            sfItem.load()
        }
    }
}
