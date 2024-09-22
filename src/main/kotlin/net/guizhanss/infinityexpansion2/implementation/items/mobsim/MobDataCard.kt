package net.guizhanss.infinityexpansion2.implementation.items.mobsim

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.DistinctiveItem
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock
import net.guizhanss.infinityexpansion2.core.items.attributes.EnergyTickingConsumer
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.implementation.groups.IEItemGroups
import net.guizhanss.infinityexpansion2.implementation.recipes.IERecipeTypes
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * Mob data cards are registered through API calls, so there is no need to have all the information in the constructor.
 * Data card may have different ids (based on how they are obtained, like from crafting or from cheating menu).
 * Only the fields in the PDC matters.
 */
class MobDataCard(
    displayItem: SlimefunItemStack,
    outputItem: SlimefunItemStack,
    recipe: Array<out ItemStack?>,
    private val energyPerTick: Int,
    val experience: Int,
    val drops: List<Pair<ItemStack, Double>>,
) : UnplaceableBlock(IEItemGroups.MOB_SIMULATION, displayItem, IERecipeTypes.MOB_DATA_INFUSER, recipe, outputItem),
    EnergyTickingConsumer, InformationalRecipeDisplayItem, DistinctiveItem {

    override fun getEnergyConsumptionPerTick() = energyPerTick

    override fun getDefaultDisplayRecipes(): List<ItemStack?> {
        val result = mutableListOf<ItemStack?>()
        drops.forEach { (item, chance) ->
            result.add(item.clone())
            result.add(GuiItems.chance(chance))
        }
        return result
    }

    override fun getInformationalItems() = listOf(
        GuiItems.energyConsumptionPerTick(getEnergyConsumptionPerTick()),
        GuiItems.experience(experience)
    )

    override fun getDividerItem() = GuiItems.PRODUCES

    override fun canStack(meta: ItemMeta, sfMeta: ItemMeta) =
        meta.persistentDataContainer == sfMeta.persistentDataContainer
}
