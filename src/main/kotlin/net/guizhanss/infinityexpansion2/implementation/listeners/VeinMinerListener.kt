@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.listeners

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
import me.mrCookieSlime.Slimefun.api.BlockStorage
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.items.tools.VeinMinerRune
import net.guizhanss.infinityexpansion2.utils.bukkitext.isAir
import net.guizhanss.infinityexpansion2.utils.constant.Keys
import net.guizhanss.infinityexpansion2.utils.items.getSlimefunItem
import net.guizhanss.infinityexpansion2.utils.items.isSlimefunItem
import net.guizhanss.infinityexpansion2.utils.tags.IETag
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.block.Block
import org.bukkit.entity.ExperienceOrb
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import kotlin.random.Random

class VeinMinerListener(plugin: InfinityExpansion2) : Listener {
    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    @EventHandler
    fun onRuneDrop(e: PlayerDropItemEvent) {
        val item = e.itemDrop
        if (item.itemStack.amount == 1 && item.itemStack.isRune(e.player)) {
            InfinityExpansion2.scheduler().run(20) {
                useRune(e.player, item)
            }
        }
    }

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        val b = e.block
        if (processing.contains(b)) return

        val p = e.player
        if (!p.isSneaking) return

        val item = p.inventory.itemInMainHand
        if (!item.hasVeinMiner()) return

        if (p.foodLevel < 1) {
            InfinityExpansion2.integrationService.sendMessage(p, "vein-miner.no-hunger")
            return
        }

        // check if the block is allowed
        val blockType = b.type
        if (!IETag.VEIN_MINER_BLOCKS.isTagged(blockType)) return

        // no sf block vein mining
        val loc = b.location
        if (BlockStorage.hasBlockInfo(loc)) return

        val foundBlocks = mutableSetOf<Block>()
        val checkedLocations = mutableSetOf<Location>()
        checkedLocations.add(loc)
        getVein(checkedLocations, foundBlocks, loc, b)

        val world = b.world

        foundBlocks.forEach { foundBlock ->
            processing.add(foundBlock)
            val event = BlockBreakEvent(foundBlock, p)
            Bukkit.getPluginManager().callEvent(event)
            if (!event.isCancelled) {
                if (event.isDropItems) {
                    foundBlock.getDrops(item).forEach { drop ->
                        world.dropItemNaturally(loc, drop)
                    }
                }
                foundBlock.type = Material.AIR
            }
            processing.remove(foundBlock)
        }

        if (blockType.toString().endsWith("ORE")) {
            world.spawn(b.location, ExperienceOrb::class.java).experience = foundBlocks.size * 2
        }

        if (Random.nextBoolean()) {
            val event = FoodLevelChangeEvent(p, p.foodLevel - 1)
            Bukkit.getPluginManager().callEvent(event)
            if (!event.isCancelled) {
                p.foodLevel = event.foodLevel
            }
        }
    }

    /**
     * Check if an [ItemStack] is a vein miner rune, and can be used by [Player].
     */
    private fun ItemStack?.isRune(p: Player): Boolean {
        if (!this.isSlimefunItem<VeinMinerRune>()) return false
        val rune = this.getSlimefunItem<VeinMinerRune>()
        return rune.canUse(p, false)
    }

    /**
     * Try to use the rune. Find the target item and apply vein miner to it.
     */
    private fun useRune(p: Player, rune: Item) {
        InfinityExpansion2.debug("Vein miner rune activating")
        if (!rune.isValid) return
        if (rune.itemStack.amount != 1) return
        InfinityExpansion2.debug("Rune is valid")

        val loc = rune.location
        if (!loc.isWorldLoaded) return
        val world = loc.world!!
        InfinityExpansion2.debug("World is loaded")

        val range = VeinMinerRune.ACTIVATE_RANGE
        val targetItem = world.getNearbyEntities(loc, range, range, range) {
            it is Item && it.itemStack.isValidItem() && !it.itemStack.hasVeinMiner() && !it.itemStack.isRune(p)
        }.firstOrNull() ?: return
        targetItem as Item
        InfinityExpansion2.debug("Found a nearby item")

        world.strikeLightningEffect(loc)

        world.createExplosion(loc, 0F)
        world.playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 0.3F, 1F)

        val target = targetItem.itemStack.clone()
        rune.remove()
        targetItem.remove()
        applyVeinMiner(p, target)
        world.dropItem(loc, target)
        InfinityExpansion2.integrationService.sendMessage(p, "vein-miner.applied")
    }

    /**
     * Check if the item can be applied with vein miner.
     */
    private fun ItemStack.isValidItem(): Boolean {
        if (this.amount != 1) return false
        if (!this.hasItemMeta()) return false
        return this.itemMeta!! is Damageable
    }

    /**
     * Check if an item is applied with vein miner.
     */
    private fun ItemStack?.hasVeinMiner() =
        !this.isAir && this!!.hasItemMeta() && PersistentDataAPI.hasBoolean(itemMeta!!, Keys.VEIN_MINER)

    private fun applyVeinMiner(p: Player, item: ItemStack) {
        if (!item.hasItemMeta()) return
        val meta = item.itemMeta!!

        PersistentDataAPI.setBoolean(meta, Keys.VEIN_MINER, true)

        val lore = meta.lore ?: mutableListOf()
        lore.add(InfinityExpansion2.integrationService.getLore(p, "vein-miner"))
        meta.lore = lore

        item.itemMeta = meta
    }

    private fun getVein(checked: MutableSet<Location>, found: MutableSet<Block>, l: Location, block: Block) {
        if (found.size >= MAX) {
            return
        }

        getAdjacentLocations(l).forEach { loc ->
            if (checked.add(loc) && loc.block.type == block.type && !BlockStorage.hasBlockInfo(block)) {
                found.add(block)
                getVein(checked, found, loc, loc.block)
            }
        }
    }

    private fun getAdjacentLocations(l: Location): List<Location> {
        val list = mutableListOf<Location>()
        list.add(l.clone().add(1.0, 0.0, 0.0))
        list.add(l.clone().add(-1.0, 0.0, 0.0))
        list.add(l.clone().add(0.0, 1.0, 0.0))
        list.add(l.clone().add(0.0, -1.0, 0.0))
        list.add(l.clone().add(0.0, 0.0, 1.0))
        list.add(l.clone().add(0.0, 0.0, -1.0))
        list.shuffle()
        return list
    }

    companion object {

        private val processing = mutableSetOf<Block>()
        private const val MAX = 64
    }
}
