package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.guizhanlib.kt.minecraft.extensions.toItem
import net.guizhanss.guizhanlib.kt.slimefun.items.edit
import net.guizhanss.guizhanlib.minecraft.utils.MinecraftVersionUtil
import net.guizhanss.infinityexpansion2.core.items.attributes.CustomWikiItem
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.implementation.items.machines.abstracts.RandomHopperMachine
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class SmithingTemplateRandomizer(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
) : RandomHopperMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick), CustomWikiItem {

    override val wikiUrl = "machines/smithing-template-randomizer"

    init {
        var templates = arrayOf(
            Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE.toItem(),
            Material.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            Material.VEX_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            Material.WILD_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            Material.COAST_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            Material.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            Material.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            Material.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            Material.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            Material.HOST_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            Material.WARD_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            Material.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            Material.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            Material.RIB_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            Material.EYE_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE.toItem()
        )

        if (MinecraftVersionUtil.isAtLeast(21)) {
            templates += arrayOf(
                Material.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
                Material.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE.toItem(),
            )
        }

        addRecipe(IEItems.UNKNOWN_SMITHING_TEMPLATE.edit { amount(8) }, templates)
    }
}
