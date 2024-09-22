@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.api

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.api.mobsim.MobDataCardProps
import net.guizhanss.infinityexpansion2.implementation.items.mobsim.MobDataCard
import net.guizhanss.infinityexpansion2.utils.constant.Keys
import net.guizhanss.infinityexpansion2.utils.displayItem
import net.guizhanss.infinityexpansion2.utils.items.convert
import org.bukkit.ChatColor
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
        val prefix = "MOB_DATA_CARD"
        val displayItem = SlimefunItemStack(
            "${InfinityExpansion2.localization.idPrefix}${prefix}_${props.id.uppercase()}",
            props.texture,
            InfinityExpansion2.localization.getItemName(prefix),
            "${ChatColor.GRAY}${props.name}",
        )
        val meta = displayItem.itemMeta
        PersistentDataAPI.setString(meta, Keys.MOB_DATA_ID, props.id)
        displayItem.itemMeta = meta

        val outputItem = SlimefunItemStack(
            "${InfinityExpansion2.localization.idPrefix}${prefix}",
            displayItem
        )

        // register item
        MobDataCard(
            displayItem,
            outputItem,
            recipe = props.recipe,
            energyPerTick = props.energy,
            experience = props.experience,
            drops = props.drops
        ).register(addon)
    }
}
