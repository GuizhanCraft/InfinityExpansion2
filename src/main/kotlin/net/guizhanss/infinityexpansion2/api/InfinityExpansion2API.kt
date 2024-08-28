package net.guizhanss.infinityexpansion2.api

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import net.guizhanss.infinityexpansion2.utils.displayItem
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * This class provides API endpoints to access InfinityExpansion2's features.
 * Use any other classes outside api package is not recommended, as they are subject to change.
 */
object InfinityExpansion2API {

    /**
     * Open the InfinityExpansion2 guide of the specific [SlimefunItem] for the [Player].
     */
    @JvmStatic
    fun openGuide(p: Player, sfItem: SlimefunItem) {
        PlayerProfile.get(p) { profile ->
            displayItem(profile, sfItem, SlimefunGuideMode.SURVIVAL_MODE)
        }
    }

    /**
     * Register a mob data card with the given loot table.
     */
    @JvmStatic
    fun registerMobDataCard(type: EntityType, loot: Map<ItemStack, Double>) {
        // TODO: implement this api
    }
}
