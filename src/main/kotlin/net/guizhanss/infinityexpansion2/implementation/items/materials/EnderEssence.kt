package net.guizhanss.infinityexpansion2.implementation.items.materials

import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.utils.createKey
import org.bukkit.World
import org.bukkit.block.Biome
import org.bukkit.inventory.ItemStack

class EnderEssence(
    itemGroup: ItemGroup,
    private val itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>
) : SimpleMaterial(itemGroup, itemStack, recipeType, recipe), GEOResource {
    override fun getKey() = itemStack.itemId.createKey()

    override fun getDefaultSupply(environment: World.Environment, biome: Biome) =
        if (environment == World.Environment.THE_END) {
            12
        } else if (biome == Biome.THE_VOID) {
            8
        } else if (environment == World.Environment.NETHER) {
            4
        } else {
            0
        }

    override fun getMaxDeviation() = 4

    override fun getName() = itemName

    override fun isObtainableFromGEOMiner() = true
}
