package net.guizhanss.infinityexpansion2.api.mobsim

import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.RandomizedSet
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * This class is used in API call.
 */
data class MobDataCardProps(
    val id: String,
    val name: String,
    val texture: ItemStack,
    val energy: Int,
    val experience: Int,
    val drops: List<Pair<ItemStack, Double>>,
    val recipe: Array<ItemStack?>,
) {

    private val dropSet = RandomizedSet<ItemStack>()

    init {
        drops.forEach { (item, chance) ->
            dropSet.add(item, chance.toFloat())
        }
    }

    fun getRandomDrop(): ItemStack = dropSet.random

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MobDataCardProps) return false

        return id == other.id && name == other.name && texture == other.texture && energy == other.energy && experience == other.experience && drops == other.drops && recipe.contentEquals(
            other.recipe
        )
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + texture.hashCode()
        result = 31 * result + energy
        result = 31 * result + experience
        result = 31 * result + drops.hashCode()
        result = 31 * result + recipe.contentHashCode()
        return result
    }

    companion object {

        val EMPTY = MobDataCardProps(
            id = "",
            name = "",
            texture = ItemStack(Material.AIR),
            energy = 0,
            experience = 0,
            drops = emptyList(),
            recipe = emptyArray()
        )
    }
}
