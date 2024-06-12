package net.guizhanss.infinityexpansion2.core.config

import net.guizhanss.infinityexpansion2.utils.loadIntMap
import org.bukkit.configuration.ConfigurationSection

data class QuarryPool(
    val baseProduct: String,
    val products: Map<String, Int>
) : ConfigurationSerializable {
    constructor(section: ConfigurationSection) : this(
        section.getString("base-product") ?: "COBBLESTONE",
        section.getConfigurationSection("products")?.let { loadIntMap(it) } ?: mapOf()
    )

    override fun serialize(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["base-product"] = baseProduct
        products.forEach { (product, amount) ->
            map["products.$product"] = amount
        }
        return map
    }
}
