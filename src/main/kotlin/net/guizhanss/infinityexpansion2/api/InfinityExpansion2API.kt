package net.guizhanss.infinityexpansion2.api

import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

/**
 * This class provides API endpoints to access InfinityExpansion2's features.
 * Use any other classes outside api package is not recommended, as they are subject to change.
 */
object InfinityExpansion2API {
    /**
     * Register a mob data card with the given loot table.
     */
    fun registerMobSimCard(type: EntityType, loot: Map<ItemStack, Double>) {
        // TODO
    }
}
