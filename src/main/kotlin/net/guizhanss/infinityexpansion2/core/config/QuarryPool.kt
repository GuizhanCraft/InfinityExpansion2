package net.guizhanss.infinityexpansion2.core.config

import net.guizhanss.guizhanlib.kt.minecraft.extensions.loadIntMap
import org.bukkit.configuration.ConfigurationSection

/**
 * A [ConfigurationSection] that represents a quarry pool.
 */
data class QuarryPool(
    val baseProduct: String,
    val products: Map<String, Int>
) : SerializableSection {

    private val productPool = mutableListOf<String>()

    init {
        products.forEach { (product, amount) ->
            repeat(amount) {
                productPool.add(product)
            }
        }
    }

    fun getRandomProduct() = productPool.random()

    override fun serialize(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["base-product"] = baseProduct
        products.forEach { (product, amount) ->
            map["products.$product"] = amount
        }
        return map
    }

    companion object {

        @Suppress("unused")
        fun deserialize(section: ConfigurationSection): QuarryPool {
            val baseProduct = section.getString("base-product")!!
            val products = section.getConfigurationSection("products").loadIntMap()
            return QuarryPool(baseProduct, products)
        }
    }
}
