package net.guizhanss.infinityexpansion2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.guizhanlib.minecraft.utils.MinecraftVersionUtil
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import net.guizhanss.infinityexpansion2.utils.bukkitext.withAmount
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class SmithingTemplateRandomizer(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    energyPerTick: Int,
) : RandomHopperMachine(itemGroup, itemStack, recipeType, recipe, energyPerTick) {

    init {
        if (MinecraftVersionUtil.isAtLeast(20)) {
            val templates = arrayOf(
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
            // TODO: 1.21 new templates
            addRecipe(IEItems.UNKNOWN_SMITHING_TEMPLATE.withAmount(8), templates)
        }
    }
}
