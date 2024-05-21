package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class GrowingMachine(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
) : AbstractMachine(itemGroup, itemStack, recipeType, recipe, MenuLayout.SINGLE_INPUT), RecipeDisplayItem {
    private val tickRateSetting = IntRangeSetting(this, "tick-rate", 1, 1, 120)

    init {
        setEnergyPerTick(energyPerTick)
        addItemSetting(tickRateSetting)
    }

    override fun process(b: Block, menu: BlockMenu): Boolean {
        return InfinityExpansion2.sfTickCount() % tickRateSetting.value == 0

        // TODO
    }

    override fun getRecipeSectionLabel(p: Player) = InfinityExpansion2.integrationService.getLore(p, "produce")

    override fun getDisplayRecipes() = listOf<ItemStack>() // TODO
}
