@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.items.tools

import com.jeff_media.morepersistentdatatypes.DataType
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.DistinctiveItem
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable
import io.github.thebusybiscuit.slimefun4.core.attributes.Soulbound
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.tasks.InfinityMatrixTask
import net.guizhanss.infinityexpansion2.utils.bukkitext.createKey
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.UUID

class InfinityMatrix(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
) : SimpleSlimefunItem<ItemUseHandler>(itemGroup, itemStack, recipeType, recipe), NotPlaceable, Soulbound,
    DistinctiveItem {

    override fun getItemHandler() = ItemUseHandler { e ->
        val p = e.player
        val item = e.item
        val meta = item.itemMeta

        if (PersistentDataAPI.has(meta, OWNER, DataType.UUID)) {
            val owner = PersistentDataAPI.get(meta, OWNER, DataType.UUID)
            InfinityExpansion2.debug("owner of the matrix: $owner")

            // check if the player is the owner
            if (owner != p.uniqueId) {
                InfinityExpansion2.localization.sendMessage(p, "infinity-matrix.not-owner")
                return@ItemUseHandler
            }

            // check if the player is holding shift, if so, unbind
            if (p.isSneaking) {
                PersistentDataAPI.remove(meta, OWNER)
                setLore(meta)
                item.itemMeta = meta
                InfinityExpansion2.localization.sendMessage(p, "infinity-matrix.ownership-removed")
                return@ItemUseHandler
            }
        } else {
            // unbind doesn't do anything when there is no owner
            if (p.isSneaking) return@ItemUseHandler

            // no owner, set owner
            PersistentDataAPI.set(meta, OWNER, DataType.UUID, p.uniqueId)
            setLore(meta, p.uniqueId)
            item.itemMeta = meta
        }

        // not gonna handle flight for these 2 game modes
        if (p.gameMode == GameMode.CREATIVE || p.gameMode == GameMode.SPECTATOR) return@ItemUseHandler

        // toggle the flight
        if (p.allowFlight) {
            p.allowFlight = false
            p.isFlying = false
            InfinityMatrixTask.ACTIVE_PLAYERS.remove(p.uniqueId)
            InfinityExpansion2.localization.sendMessage(p, "infinity-matrix.flight-disabled")
        } else {
            p.allowFlight = true
            InfinityMatrixTask.ACTIVE_PLAYERS.add(p.uniqueId)
            InfinityExpansion2.localization.sendMessage(p, "infinity-matrix.flight-enabled")
        }
    }

    private fun setLore(meta: ItemMeta, owner: UUID? = null) {
        val lore = meta.lore!!
        val lineIdx = lore.indexOfFirst { ChatColor.stripColor(it)!!.startsWith("Owner:") }
        val line = "${ChatColor.AQUA}Owner: ${ChatColor.WHITE}${owner ?: "None"}"
        if (lineIdx == -1) {
            lore.add(line)
        } else {
            lore[lineIdx] = line
        }
        meta.lore = lore
    }

    override fun canStack(meta1: ItemMeta, meta2: ItemMeta) =
        meta1.persistentDataContainer == meta2.persistentDataContainer

    companion object {

        private val OWNER = "owner".createKey()
    }
}
