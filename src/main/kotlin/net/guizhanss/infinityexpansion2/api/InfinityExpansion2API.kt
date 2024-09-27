@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.api

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import net.guizhanss.infinityexpansion2.api.mobsim.MobDataCardProps
import net.guizhanss.infinityexpansion2.implementation.items.mobsim.MobDataCard
import net.guizhanss.infinityexpansion2.utils.displayItem
import org.bukkit.entity.Player

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
     * Register a mob data card.
     */
    @JvmStatic
    fun registerMobDataCard(props: MobDataCardProps, addon: SlimefunAddon) {
        // create item
        val displayItem = MobDataCard.buildDisplayItem(props.id, props.name, props.texture)

        // register item
        MobDataCard.create(displayItem, props.recipe, props).register(addon)
    }
}
