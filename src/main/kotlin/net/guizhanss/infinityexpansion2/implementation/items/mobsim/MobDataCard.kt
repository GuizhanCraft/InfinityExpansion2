@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.items.mobsim

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.DistinctiveItem
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.api.mobsim.MobDataCardProps
import net.guizhanss.infinityexpansion2.core.IERegistry
import net.guizhanss.infinityexpansion2.core.items.attributes.EnergyTickingConsumer
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.implementation.groups.IEItemGroups
import net.guizhanss.infinityexpansion2.implementation.recipes.IERecipeTypes
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import net.guizhanss.infinityexpansion2.utils.constant.Keys
import net.guizhanss.infinityexpansion2.utils.items.GuiItems
import net.guizhanss.infinityexpansion2.utils.toId
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import kotlin.math.floor

/**
 * Data card may have different ids (based on how they are obtained, like from crafting or from cheating menu).
 * Only the fields in the PDC determines the type of mob data card.
 */
class MobDataCard(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    private val props: MobDataCardProps,
) : UnplaceableBlock(itemGroup, itemStack, recipeType, recipe, buildOutputItem(props.id, props.name, props.texture)),
    EnergyTickingConsumer, InformationalRecipeDisplayItem, DistinctiveItem {

    override fun postRegister() {
        super.postRegister()

        if (!isDisabled) {
            IERegistry.mobDataCards[props.id] = props
        }
    }

    override fun getEnergyConsumptionPerTick() = props.energy

    override fun getDefaultDisplayRecipes(): List<ItemStack?> {
        val result = mutableListOf<ItemStack?>()
        props.drops.forEach { (item, chance) ->
            result.add(item.clone())
            result.add(GuiItems.chance(chance))
        }
        return result
    }

    override fun getInformationalItems() = listOf(
        GuiItems.energyConsumptionPerTick(getEnergyConsumptionPerTick()),
        GuiItems.experience(floor(props.experience * InfinityExpansion2.configService.mobSimExpMultiplier).toInt())
    )

    override fun getDividerItem() = GuiItems.PRODUCES

    override fun canStack(meta: ItemMeta, sfMeta: ItemMeta) =
        meta.persistentDataContainer == sfMeta.persistentDataContainer

    companion object {

        internal fun create(itemStack: SlimefunItemStack, recipe: Array<out ItemStack?>, props: MobDataCardProps) =
            MobDataCard(IEItemGroups.MOB_SIMULATION, itemStack, IERecipeTypes.MOB_DATA_INFUSER, recipe, props)

        // should be called after the plain mob data card is registered
        fun buildDisplayItem(id: String, name: String, texture: ItemStack): SlimefunItemStack {
            val item = SlimefunItemStack(
                "${IEItems.MOB_DATA_CARD.itemId}_${id.toId()}",
                texture,
                InfinityExpansion2.localization.getItemName("MOB_DATA_CARD"),
                "${ChatColor.GRAY}${name}"
            )
            val meta = item.itemMeta
            PersistentDataAPI.setString(meta, Keys.MOB_DATA_ID, id)
            item.itemMeta = meta
            return item
        }

        fun buildOutputItem(displayItem: SlimefunItemStack) = SlimefunItemStack(
            IEItems.MOB_DATA_CARD.itemId, displayItem
        )

        fun buildOutputItem(id: String, name: String, texture: ItemStack) = if (id.isEmpty()) {
            Material.AIR.toItem()
        } else {
            buildOutputItem(
                buildDisplayItem(id, name, texture)
            )
        }

        fun getMobDataId(item: ItemStack) =
            if (item.hasItemMeta()) PersistentDataAPI.getString(item.itemMeta, Keys.MOB_DATA_ID) else null
    }
}
