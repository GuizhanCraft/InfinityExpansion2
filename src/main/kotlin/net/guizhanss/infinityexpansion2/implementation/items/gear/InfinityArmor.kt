package net.guizhanss.infinityexpansion2.implementation.items.gear

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectionType
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectiveArmor
import io.github.thebusybiscuit.slimefun4.core.attributes.Soulbound
import io.github.thebusybiscuit.slimefun4.implementation.items.armor.SlimefunArmorPiece
import net.guizhanss.infinityexpansion2.utils.constant.Keys
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect

open class InfinityArmor(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    effects: Array<PotionEffect> = arrayOf(),
    private val protectionTypes: Array<ProtectionType> = arrayOf(),
) : SlimefunArmorPiece(itemGroup, itemStack, recipeType, recipe, effects), ProtectiveArmor, Soulbound {
    override fun getProtectionTypes() = protectionTypes

    override fun isFullSetRequired() = false

    override fun getArmorSetId() = Keys.INFINITY_ARMOR
}
