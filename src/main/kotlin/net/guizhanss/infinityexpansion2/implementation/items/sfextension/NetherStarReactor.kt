package net.guizhanss.infinityexpansion2.implementation.items.sfextension

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel
import me.mrCookieSlime.Slimefun.api.BlockStorage
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.bukkitext.toItem
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.reactors.NetherStarReactor as SlimefunNetherStarReactor

class NetherStarReactor(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    private val energyPerTick: Int,
    private val capacity: Int,
) : SlimefunNetherStarReactor(itemGroup, itemStack, recipeType, recipe) {

    override fun getEnergyProduction() = energyPerTick

    override fun getCapacity() = capacity

    override fun registerDefaultFuelTypes() {
        registerFuel(MachineFuel(600, Material.NETHER_STAR.toItem()))
    }

    override fun extraTick(l: Location) {
        if (InfinityExpansion2.sfTickCount() % 4 != 0) {
            return
        }

        InfinityExpansion2.scheduler().run {
            val locAbove = l.clone().add(0.0, 1.0, 0.0)
            val world = locAbove.world ?: return@run
            val checkWitherProof = locAbove.block.type == Material.AIR
            locAbove.getNearbyEntities(8.0, 8.0, 8.0).forEach { entity ->
                if (entity is LivingEntity && entity.isValid) {
                    if (!checkWitherProof) {
                        val result = world.rayTraceBlocks(locAbove, entity.location.subtract(locAbove).toVector(), 16.0)
                        if (result != null) {
                            val hit = result.hitBlock
                            if (hit != null) {
                                val id = BlockStorage.getLocationInfo(hit.location, "id")
                                if (id != null && id.contains("WITHER_PROOF")) {
                                    return@forEach
                                }
                            }
                        }
                    }
                    entity.addPotionEffect(PotionEffect(PotionEffectType.WITHER, 100, 2))
                }
            }
        }
    }
}
