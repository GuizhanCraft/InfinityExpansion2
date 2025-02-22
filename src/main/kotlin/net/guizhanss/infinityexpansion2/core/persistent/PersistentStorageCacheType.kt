package net.guizhanss.infinityexpansion2.core.persistent

import com.jeff_media.morepersistentdatatypes.DataType
import net.guizhanss.infinityexpansion2.implementation.items.storage.StorageCache
import net.guizhanss.infinityexpansion2.utils.bukkitext.createKey
import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType

class PersistentStorageCacheType : PersistentDataType<PersistentDataContainer, StorageCache> {

    override fun getPrimitiveType() = PersistentDataContainer::class.java

    override fun getComplexType() = StorageCache::class.java

    override fun fromPrimitive(
        container: PersistentDataContainer,
        context: PersistentDataAdapterContext
    ): StorageCache {
        val item = container.get(ITEM_KEY, DataType.ITEM_STACK)
        val amount = container.getOrDefault(AMOUNT_KEY, PersistentDataType.INTEGER, 0)
        val limit = container.getOrDefault(MAX_AMOUNT_KEY, PersistentDataType.INTEGER, 0)
        val voidExcess = container.getOrDefault(VOID_EXCESS_KEY, PersistentDataType.BOOLEAN, false)

        return StorageCache(item, amount, limit, voidExcess)
    }

    override fun toPrimitive(
        cache: StorageCache,
        context: PersistentDataAdapterContext
    ): PersistentDataContainer {
        val container = context.newPersistentDataContainer()

        cache.itemStack?.let { container.set(ITEM_KEY, DataType.ITEM_STACK, it) }
        container.set(AMOUNT_KEY, PersistentDataType.INTEGER, cache.amount)
        container.set(MAX_AMOUNT_KEY, PersistentDataType.INTEGER, cache.limit)
        container.set(VOID_EXCESS_KEY, PersistentDataType.BOOLEAN, cache.voidExcess)

        return container
    }

    companion object {

        private val ITEM_KEY = "item".createKey()
        private val AMOUNT_KEY = "amount".createKey()
        private val MAX_AMOUNT_KEY = "max_amount".createKey()
        private val VOID_EXCESS_KEY = "void_excess".createKey()

        @JvmStatic
        val TYPE = PersistentStorageCacheType()
    }

}
