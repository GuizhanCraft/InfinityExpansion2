package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.guizhanlib.kt.minecraft.extensions.toItem
import net.guizhanss.guizhanlib.kt.slimefun.items.edit
import net.guizhanss.infinityexpansion2.core.items.attributes.CustomWikiItem
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.HopperMachine
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class CobblePress(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
) : HopperMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick), CustomWikiItem {

    override val wikiUrl = "machines/cobble-press"

    init {
        addRecipe(
            Material.COBBLESTONE.toItem(64),
            IEItems.COMPRESSED_COBBLESTONE_1.edit { amount(8) }
        )
        addRecipe(
            IEItems.COMPRESSED_COBBLESTONE_1.edit { amount(64) },
            IEItems.COMPRESSED_COBBLESTONE_2.edit { amount(8) }
        )
        addRecipe(
            IEItems.COMPRESSED_COBBLESTONE_2.edit { amount(64) },
            IEItems.COMPRESSED_COBBLESTONE_3.edit { amount(8) }
        )
        addRecipe(
            IEItems.COMPRESSED_COBBLESTONE_3.edit { amount(64) },
            IEItems.COMPRESSED_COBBLESTONE_4.edit { amount(8) }
        )
        addRecipe(
            IEItems.COMPRESSED_COBBLESTONE_4.edit { amount(64) },
            IEItems.COMPRESSED_COBBLESTONE_5.edit { amount(8) }
        )
    }
}
