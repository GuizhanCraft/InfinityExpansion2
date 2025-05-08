package net.guizhanss.infinityexpansion2.api

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import net.guizhanss.infinityexpansion2.api.mobsim.MobDataCardProps
import net.guizhanss.infinityexpansion2.implementation.items.mobsim.MobDataCard
import net.guizhanss.infinityexpansion2.utils.slimefunext.displayItem
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
    fun openGuide(p: Player, sfItem: SlimefunItem, item: ItemStack? = null) {
        PlayerProfile.get(p) { profile ->
            displayItem(profile, sfItem, SlimefunGuideMode.SURVIVAL_MODE, item)
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
        val card = MobDataCard.create(displayItem, props.recipe, props)
        card.register(addon)
        // in case autoloading is not yet enabled but item is registered
        if (!Slimefun.getRegistry().isAutoLoadingEnabled) {
            card.load()
        }
    }
}
