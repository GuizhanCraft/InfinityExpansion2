package net.guizhanss.infinityexpansion2.implementation.items.storage

import io.github.schntgaispock.slimehud.waila.HudRequest
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.DistinctiveItem
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu
import net.guizhanss.guizhanlib.common.Cooldown
import net.guizhanss.guizhanlib.kt.minecraft.extensions.isAir
import net.guizhanss.guizhanlib.kt.minecraft.items.edit
import net.guizhanss.guizhanlib.kt.slimefun.extensions.isSlimefunItem
import net.guizhanss.guizhanlib.kt.slimefun.extensions.position
import net.guizhanss.guizhanlib.kt.slimefun.items.builder.asMaterialType
import net.guizhanss.guizhanlib.kt.slimefun.utils.getBlockMenu
import net.guizhanss.guizhanlib.kt.slimefun.utils.getBoolean
import net.guizhanss.guizhanlib.kt.slimefun.utils.getInt
import net.guizhanss.guizhanlib.kt.slimefun.utils.setBoolean
import net.guizhanss.guizhanlib.kt.slimefun.utils.setInt
import net.guizhanss.guizhanlib.slimefun.machines.MenuBlock
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.debug.DebugCase
import net.guizhanss.infinityexpansion2.core.items.annotations.HudProvider
import net.guizhanss.infinityexpansion2.core.items.attributes.InformationalRecipeDisplayItem
import net.guizhanss.infinityexpansion2.core.menu.MenuLayout
import net.guizhanss.infinityexpansion2.core.persistent.PersistentStorageCacheType
import net.guizhanss.infinityexpansion2.utils.Debug
import net.guizhanss.infinityexpansion2.utils.bukkitext.ie2Key
import net.guizhanss.infinityexpansion2.utils.items.isSimilar
import net.guizhanss.infinityexpansion2.utils.items.removeDisplayItem
import net.guizhanss.infinityexpansion2.utils.items.toDisplayItem
import net.guizhanss.infinityexpansion2.utils.tags.IETag
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Tag
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.UUID

@HudProvider
class StorageUnit(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>,
    val capacity: Int,
) : MenuBlock(itemGroup, itemStack, recipeType, recipe), InformationalRecipeDisplayItem, DistinctiveItem {

    init {
        addItemHandler(object : BlockTicker() {
            override fun isSynchronized() = false

            override fun tick(b: Block, item: SlimefunItem, data: Config) {
                tick(b)
            }
        })
    }

    override fun setup(preset: BlockMenuPreset) {
        LAYOUT.setupPreset(preset)

        preset.drawBackground(ACTION_DEPOSIT_ITEM, intArrayOf(ACTION_DEPOSIT_SLOT))
        preset.drawBackground(ACTION_WITHDRAW_SINGLE_ITEM, intArrayOf(ACTION_WITHDRAW_SINGLE_SLOT))
        preset.drawBackground(ACTION_WITHDRAW_ALL_ITEM, intArrayOf(ACTION_WITHDRAW_ALL_SLOT))
        preset.addMenuClickHandler(AMOUNT_SLOT, ChestMenuUtils.getEmptyClickHandler())
        preset.addMenuClickHandler(ITEM_SLOT, ChestMenuUtils.getEmptyClickHandler())
    }

    override fun getInputSlots() = LAYOUT.inputSlots

    override fun getOutputSlots() = LAYOUT.outputSlots

    val caches: Map<BlockPosition, StorageCache>
        get() = _caches

    private val _caches = mutableMapOf<BlockPosition, StorageCache>()

    override fun onNewInstance(menu: BlockMenu, b: Block) {
        menu.addMenuClickHandler(ACTION_VOID_SLOT) { _, _, _, _ ->
            toggleVoid(menu)
            false
        }

        // inv interactions
        menu.addMenuClickHandler(ACTION_DEPOSIT_SLOT) { p, _, _, _ ->
            interaction(menu, p, InteractionMode.DEPOSIT)
            false
        }
        menu.addMenuClickHandler(ACTION_WITHDRAW_SINGLE_SLOT) { p, _, _, _ ->
            interaction(menu, p, InteractionMode.WITHDRAW_SINGLE)
            false
        }
        menu.addMenuClickHandler(ACTION_WITHDRAW_ALL_SLOT) { p, _, _, _ ->
            interaction(menu, p, InteractionMode.WITHDRAW_ALL)
            false
        }

        val cache = _caches[menu.position] ?: addCache(menu)
        menu.updateDisplay(cache)
    }

    override fun getInputSlots(menu: DirtyChestMenu, item: ItemStack): IntArray {
        val cache = _caches[(menu as BlockMenu).position]
        return if (cache != null &&
            ((cache.isEmpty() || cache.matches(item)) && isSimilar(item, menu.getItemInSlot(outputSlots[0])))
        ) {
            inputSlots
        } else {
            intArrayOf()
        }
    }

    private fun tick(b: Block) {
        val menu = b.getBlockMenu() ?: run {
            _caches.remove(b.position)
            return
        }

        val cache = _caches[menu.position] ?: return

        menu.updateDisplay(cache)

        // input item
        val inputItem = menu.getItemInSlot(inputSlots[0])
        if (!inputItem.isAir()) {
            val inputConsumption = menu.inputItem(cache, inputItem)
            if (inputConsumption > 0) {
                menu.consumeItem(inputSlots[0], inputConsumption)
                menu.markDirty()
                menu.updateDisplay(cache)
            }
        }

        // output item
        val outputItem = menu.getItemInSlot(outputSlots[0])
        var fetched: ItemStack? = null
        if (outputItem.isAir()) {
            // no item in output slot
            fetched = menu.outputItem(cache)
        } else if (outputItem.amount < outputItem.maxStackSize && cache.matches(outputItem)) {
            // matched item in output slot but not yet filled
            fetched = menu.outputItem(cache, outputItem.maxStackSize - outputItem.amount)
        }

        if (!fetched.isAir()) {
            Debug.log(DebugCase.STORAGE_UNIT, "output item: $fetched")
            menu.pushItem(fetched, *outputSlots)
            menu.markDirty()
            menu.updateDisplay(cache)
        }

        // TODO: try to update signs and holo through tasks
    }

    override fun onPlace(e: BlockPlaceEvent, b: Block) {
        val item = e.itemInHand
        val meta = item.itemMeta
        val cache = PersistentDataAPI.get(meta, STORAGE_KEY, PersistentStorageCacheType.TYPE) ?: return

        b.location.save(cache)
        _caches[BlockPosition(b)] = cache
    }

    override fun onBreak(e: BlockBreakEvent, menu: BlockMenu) {
        val loc = menu.location
        val dropLocation = loc.clone().add(0.5, 0.5, 0.5)

        menu.dropItems(loc, *inputSlots)

        val cache = _caches.remove(menu.position)
        if (cache != null && cache.amount > 0 && cache.itemStack != null) {
            val itemToDrop = item.clone()
            val meta = itemToDrop.itemMeta

            // attempt to add item into storage from output slot
            val outputItem = menu.getItemInSlot(outputSlots[0])
            Debug.log(DebugCase.STORAGE_UNIT, "Checking dropped storage's output slot")
            if (!outputItem.isAir()) {
                Debug.log(DebugCase.STORAGE_UNIT, "Output slot has item: $outputItem")
                if (!cache.matches(outputItem)) {
                    Debug.log(DebugCase.STORAGE_UNIT, "Output item not match, drop as is")
                    loc.world.dropItem(dropLocation, outputItem)
                } else {
                    Debug.log(DebugCase.STORAGE_UNIT, "Output item match, try to add into storage")
                    val leftover = cache.increaseAmount(outputItem.amount)
                    if (leftover > 0) {
                        val item = outputItem.edit { amount(leftover) }
                        loc.world.dropItem(dropLocation, item)
                    }
                }
            }

            PersistentDataAPI.set(meta, STORAGE_KEY, PersistentStorageCacheType.TYPE, cache)
            cache.addLore(meta)
            itemToDrop.itemMeta = meta
            loc.world.dropItem(loc.clone().add(0.5, 0.5, 0.5), itemToDrop)
            e.isDropItems = false
        }
    }

    private fun toggleVoid(menu: BlockMenu) {
        val cache = _caches[menu.position] ?: return
        cache.voidExcess = !cache.voidExcess
        menu.updateDisplay(cache)
        menu.location.save(cache)
    }

    /**
     * Handle interaction.
     */
    private fun interaction(menu: BlockMenu, p: Player, mode: InteractionMode) {
        val cache = _caches[menu.position] ?: return
        val pInv = p.inventory

        if (!INTERACTION_COOLDOWN.check(p.uniqueId)) {
            InfinityExpansion2.integrationService.sendMessage(p, "storage.interaction-cd")
        } else {
            INTERACTION_COOLDOWN.set(p.uniqueId, INTERACTION_CD)
        }

        when (mode) {
            InteractionMode.DEPOSIT -> {
                val outputItem = menu.getItemInSlot(outputSlots[0])

                // empty storage && no item in output
                if (cache.isEmpty() && outputItem.isAir()) return

                cache.itemStack = outputItem.edit { amount(1) }

                for (item in pInv.storageContents) {
                    if (item.isAir() || item.isBlacklisted()) continue

                    if (cache.matches(item)) {
                        val consumed = menu.inputItem(cache, item)
                        if (consumed > 0) {
                            item.amount -= consumed
                            menu.updateDisplay(cache)
                            menu.location.save(cache)
                        }
                    }
                }
            }

            InteractionMode.WITHDRAW_SINGLE -> {
                if (cache.isEmpty()) return

                val itemToGive = cache.itemStack!!.clone()
                val amount = itemToGive.maxStackSize.coerceAtMost(cache.amount)
                val item = itemToGive.edit { amount(amount) }
                val leftover = pInv.addItem(item)

                if (leftover.isEmpty()) {
                    cache.amount -= amount
                    menu.updateDisplay(cache)
                    menu.location.save(cache)
                }
            }

            InteractionMode.WITHDRAW_ALL -> {
                if (cache.isEmpty()) return

                val itemToGive = cache.itemStack!!.clone()
                val maxStackSize = itemToGive.maxStackSize

                val emptySlots = pInv.storageContents.count { it.isAir() }
                val partialSlots = pInv.storageContents.filter {
                    !it.isAir() &&
                        it.amount < it.maxStackSize &&
                        cache.matches(it)
                }.sumOf { it!!.maxStackSize - it.amount }

                val possibleAmount = (emptySlots * maxStackSize + partialSlots)
                    .coerceAtMost(cache.amount)

                if (possibleAmount <= 0) return

                // First fill existing stacks
                for (slot in 0 until pInv.size) {
                    val invItem = pInv.getItem(slot) ?: continue
                    if (!invItem.isAir() && cache.matches(invItem) && invItem.amount < invItem.maxStackSize) {
                        val canAdd = invItem.maxStackSize - invItem.amount
                        val toAdd = canAdd.coerceAtMost(cache.amount)
                        invItem.amount += toAdd
                        cache.amount -= toAdd
                        if (cache.amount <= 0) break
                    }
                }

                // Then fill empty slots
                while (cache.amount > 0) {
                    val amount = maxStackSize.coerceAtMost(cache.amount)
                    val item = itemToGive.edit { amount(amount) }
                    val leftover = pInv.addItem(item)
                    if (leftover.isEmpty()) {
                        cache.amount -= amount
                    } else {
                        // Inventory is full
                        break
                    }
                }

                menu.updateDisplay(cache)
                menu.location.save(cache)
            }
        }
    }

    /**
     * Create the [StorageCache] instance based on the given [menu] and add it to the cache map.
     */
    private fun addCache(menu: BlockMenu): StorageCache {
        val loc = menu.location
        val amount = loc.getInt(BS_AMOUNT)
        val voidExcess = loc.getBoolean(BS_VOID)
        val item: ItemStack? = menu.getItemInSlot(ITEM_SLOT)

        val cache = createCache(item, menu, amount, voidExcess)
        Debug.log(DebugCase.STORAGE_UNIT, "created cache at $loc: $cache")

        _caches[menu.position] = cache
        return cache
    }

    /**
     * Create a new [StorageCache] instance with the given [item], [amount], [capacity], and [voidExcess].
     */
    private fun createCache(item: ItemStack?, menu: BlockMenu, amount: Int, voidExcess: Boolean): StorageCache {
        return if (item.isAir() || isSimilar(item, NO_ITEM)) {
            menu.addItem(ITEM_SLOT, NO_ITEM)
            StorageCache(null, amount, capacity, voidExcess)
        } else {
            val cache = StorageCache(item.removeDisplayItem(), amount, capacity, voidExcess)

            menu.updateDisplay(cache, true)
            cache
        }
    }

    override fun canStack(meta1: ItemMeta, meta2: ItemMeta) =
        meta1.persistentDataContainer == meta2.persistentDataContainer

    override fun getInfoItems() = listOf(
        capacityItem(capacity)
    )

    companion object {

        // layout
        private val LAYOUT = MenuLayout.STORAGE_UNIT

        // slots
        const val AMOUNT_SLOT = 4
        const val ITEM_SLOT = 13
        const val ACTION_VOID_SLOT = 27
        const val ACTION_DEPOSIT_SLOT = 28
        const val ACTION_WITHDRAW_SINGLE_SLOT = 34
        const val ACTION_WITHDRAW_ALL_SLOT = 35

        // block storage keys
        const val BS_AMOUNT = "stored_amount"
        const val BS_VOID = "void_excess"

        // inv interaction modes
        enum class InteractionMode {

            DEPOSIT,
            WITHDRAW_SINGLE,
            WITHDRAW_ALL
        }

        // PDC key
        val STORAGE_KEY = ie2Key("storage")

        // gui items
        private val ACTION_DEPOSIT_ITEM = InfinityExpansion2.localization.getGuiItem(
            // https://minecraft-heads.com/custom-heads/head/106284-elevator-arrow-button-down-green
            "5815b66ce57add6abc82082cf81debed843d9ff36d920f1a482a038bf3cf1b6d".asMaterialType(),
            "storage_deposit"
        )
        private val ACTION_WITHDRAW_SINGLE_ITEM = InfinityExpansion2.localization.getGuiItem(
            // https://minecraft-heads.com/custom-heads/head/106285-elevator-arrow-button-up-red
            "a979cca43315030d76fa4112e0e6b809a4f463872cc1787c86659917fd94e8a0".asMaterialType(),
            "storage_withdraw_single"
        )
        private val ACTION_WITHDRAW_ALL_ITEM = InfinityExpansion2.localization.getGuiItem(
            // https://minecraft-heads.com/custom-heads/head/106283-elevator-arrow-button-up-green
            "5fa1c6c7ead75a04585395f63135dc96fa078fb920484699ef8e564e142d64cb".asMaterialType(),
            "storage_withdraw_all"
        )
        private val NO_ITEM = InfinityExpansion2.localization.getGuiItem(
            Material.BARRIER.asMaterialType(),
            "storage_no_item"
        ).toDisplayItem()
        private val VOID_ON_ITEM = InfinityExpansion2.localization.getGuiItem(
            // https://minecraft-heads.com/custom-heads/head/587-void-charge
            "32697929465950ccd3a4923a5e6eb15b4fe2ba2b28715f4a7996785d706158".asMaterialType(),
            "storage_void_excess_on"
        ).toDisplayItem()
        private val VOID_OFF_ITEM = InfinityExpansion2.localization.getGuiItem(
            // https://minecraft-heads.com/custom-heads/head/51667-storage-component
            "3dbbf5ef39cfe42775915a2494e1814ca123966c2b74bf5debc07195bc213795".asMaterialType(),
            "storage_void_excess_off"
        ).toDisplayItem()

        private fun amountItem(amount: Int, limit: Int) = InfinityExpansion2.localization.getGuiItem(
            Material.BARREL.asMaterialType(),
            "storage_amount",
            "&7$amount / $limit"
        ).toDisplayItem()

        private fun capacityItem(capacity: Int) = InfinityExpansion2.localization.getGuiItem(
            Material.BARREL.asMaterialType(),
            "storage_capacity",
            "&7$capacity"
        ).toDisplayItem()

        // per player interaction cd
        private val INTERACTION_COOLDOWN = Cooldown<UUID>()
        const val INTERACTION_CD = 1000L // 1s

        /**
         * Check if the [ItemStack] is not allowed in the storage unit.
         */
        fun ItemStack.isBlacklisted() = isAir()
            || type.maxDurability < 0
            || Tag.SHULKER_BOXES.isTagged(type)
            || IETag.BUNDLE.isTagged(type)
            || isSlimefunItem<StorageUnit>()

        /**
         * Handles [ItemStack] input.
         *
         * Return the amount of items that should be consumed
         */
        fun BlockMenu.inputItem(cache: StorageCache, item: ItemStack, amount: Int = item.amount): Int {
            if (item.isBlacklisted()) return 0
            Debug.log(DebugCase.STORAGE_UNIT, "storage unit input attempt with item: $item, cache: $cache")

            if (cache.isEmpty()) {
                cache.itemStack = item.edit { amount(1) }
                val leftover = cache.increaseAmount(amount)
                Debug.log(DebugCase.STORAGE_UNIT, "empty cache, updated: $cache, leftover: $leftover")
                location.save(cache)
                return item.amount - leftover
            } else if (cache.matches(item)) {
                val leftover = cache.increaseAmount(amount)
                Debug.log(DebugCase.STORAGE_UNIT, "item matches, updated cache: $cache, leftover: $leftover")
                location.save(cache)
                return item.amount - leftover
            }

            Debug.log(DebugCase.STORAGE_UNIT, "cache not empty and item not match")
            return 0
        }

        /**
         * Handle item output.
         *
         * A full [ItemStack] is expected.
         */
        fun BlockMenu.outputItem(cache: StorageCache): ItemStack? {
            if (cache.isEmpty()) return null
            return outputItem(cache, cache.itemStack!!.maxStackSize)
        }

        /**
         * Handle item output.
         *
         * Custom amount is expected, but only a single [ItemStack] is returned.
         */
        fun BlockMenu.outputItem(cache: StorageCache, amount: Int): ItemStack? {
            if (cache.isEmpty()) return null

            val decreaseAmount = amount.coerceAtMost(cache.amount)
            val item = cache.itemStack!!.edit { amount(decreaseAmount) }
            cache.amount -= decreaseAmount
            location.save(cache)
            return item
        }

        /**
         * Update all the display items in [BlockMenu].
         */
        private fun BlockMenu.updateDisplay(cache: StorageCache, forceUpdate: Boolean = false) {
            if (!forceUpdate && !hasViewer()) return

            // item slot
            if (cache.isEmpty()) {
                replaceExistingItem(ITEM_SLOT, NO_ITEM)
            } else {
                val item = cache.itemStack!!.toDisplayItem()
                item.amount = 1
                replaceExistingItem(ITEM_SLOT, item)
            }

            // amount slot
            replaceExistingItem(AMOUNT_SLOT, amountItem(cache.amount, cache.limit))

            // void slot
            replaceExistingItem(ACTION_VOID_SLOT, if (cache.voidExcess) VOID_ON_ITEM else VOID_OFF_ITEM)
        }

        /**
         * Save the [StorageCache] to block storage.
         */
        private fun Location.save(cache: StorageCache) {
            setInt(BS_AMOUNT, cache.amount)
            setBoolean(BS_VOID, cache.voidExcess)
        }

        @Suppress("unused")
        fun hudHandler(request: HudRequest): String {
            val menu = request.location.getBlockMenu()
            val storage = request.slimefunItem as StorageUnit
            val cache = storage.caches[menu.position] ?: return "Invalid Storage Unit"

            if (cache.isEmpty()) return "Empty"

            val response = mutableListOf<String>()
            response.add(InfinityExpansion2.integrationService.getTranslatedItemName(request.player, cache.itemStack!!))
            response.add("${cache.amount} / ${cache.limit}")

            return response.joinToString("&7 | ")
        }
    }
}
