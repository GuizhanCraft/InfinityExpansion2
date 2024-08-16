package net.guizhanss.infinityexpansion2.core

import net.guizhanss.infinityexpansion2.implementation.items.materials.Singularity
import org.bukkit.inventory.ItemStack

internal object IERegistry {

    val singularities = mutableListOf<Singularity>()
    val singularityIngredientMap = mutableMapOf<ItemStack, Singularity>()
    val itemMapping = mutableMapOf<String, ItemStack>()
}
