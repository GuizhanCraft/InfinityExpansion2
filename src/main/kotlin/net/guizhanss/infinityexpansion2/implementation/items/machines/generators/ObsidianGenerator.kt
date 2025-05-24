package net.guizhanss.infinityexpansion2.implementation.items.machines.generators

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.core.items.attributes.CustomWikiItem
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ObsidianGenerator(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    material: Material,
    speed: Int,
    energyPerTick: Int,
) : MaterialGenerator(
    itemGroup, itemStack, recipeType, recipe, material, speed, energyPerTick
), CustomWikiItem {

    override val wikiUrl = "machines/obsidian-generator"
}
