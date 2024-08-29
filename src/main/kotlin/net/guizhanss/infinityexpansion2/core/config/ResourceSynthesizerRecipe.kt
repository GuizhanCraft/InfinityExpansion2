package net.guizhanss.infinityexpansion2.core.config

import org.bukkit.configuration.ConfigurationSection

/**
 * A [ConfigurationSection] that represents a resource synthesizer recipe.
 */
data class ResourceSynthesizerRecipe(
    val inputs: Pair<String, String>, // item id 1 and 2
    val output: Pair<String, Int>, // item id and amount
) : SerializableSection {

    override fun serialize(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["input.1"] = inputs.first
        map["input.2"] = inputs.second
        map["output.item"] = output.first
        map["output.amount"] = output.second
        return map
    }

    companion object {

        fun deserialize(map: Map<String, Any>): ResourceSynthesizerRecipe {
            val inputs = Pair(map["input.1"] as? String ?: "", map["input.2"] as? String ?: "")
            val output = Pair(map["output.item"] as? String ?: "", map["output.amount"] as? Int ?: 1)
            return ResourceSynthesizerRecipe(inputs, output)
        }
    }
}
