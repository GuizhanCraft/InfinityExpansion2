package net.guizhanss.infinityexpansion2.implementation.listeners

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.implementation.items.tools.VeinMinerRune
import net.guizhanss.infinityexpansion2.utils.VeinMinerUtils
import org.bukkit.Sound
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.inventory.ItemStack

class VeinMinerListener(plugin: InfinityExpansion2) : Listener {
    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    @EventHandler
    fun onRuneDrop(e: PlayerDropItemEvent) {
        val item = e.itemDrop
        if (item.itemStack.amount == 1 && isRune(e.player, item.itemStack)) {
            InfinityExpansion2.scheduler().run(20) {
                useRune(e.player, item)
            }
        }
    }

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        // TODO: implement vein miner break
    }

    private fun isRune(p: Player, item: ItemStack): Boolean {
        val rune = SlimefunItem.getByItem(item)
        return rune is VeinMinerRune
            && !rune.isDisabledIn(p.world)
            && !IEItems.VEIN_MINER_RUNE.item!!.isDisabledIn(p.world)
    }

    private fun useRune(p: Player, rune: Item) {
        if (!rune.isValid) return
        if (rune.itemStack.amount != 1) return

        val loc = rune.location
        if (!loc.isWorldLoaded) return
        val world = loc.world!!

        val range = VeinMinerRune.RANGE
        val targetItem = world.getNearbyEntities(loc, range, range, range) {
            it is Item && isValidItem(it.itemStack)
        }.firstOrNull() ?: return

        world.strikeLightningEffect(loc)

        world.createExplosion(loc, 0F)
        world.playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 0.3F, 1F)

        // TODO
    }

    private fun isValidItem(item: ItemStack): Boolean {
        if (item.amount != 1) return false

        // TODO
        return false
    }
}
