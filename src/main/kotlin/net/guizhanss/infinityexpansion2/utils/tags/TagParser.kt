@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.utils.tags

import com.google.gson.JsonParser
import com.google.gson.stream.JsonReader
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion
import io.github.thebusybiscuit.slimefun4.api.exceptions.TagMisconfigurationException
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.CommonPatterns
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.constant.Patterns
import org.bukkit.Keyed
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Tag
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

/**
 * The [TagParser] is responsible for parsing a JSON file into a [IETag].
 */
internal class TagParser(tag: IETag) : Keyed {
    /**
     * Every [Tag] has a [NamespacedKey].
     * This is the [NamespacedKey] for the resulting [Tag].
     */
    private val tagKey = tag.key

    override fun getKey() = tagKey

    /**
     * This will parse the JSON and run the provided callback with [Sets][Set] of
     * matched [Materials][Material] and [Tags][Tag].
     *
     * Throws [TagMisconfigurationException] if the JSON is malformed or no adequate [Material] or [Tag] could be found.
     */
    fun parse(callback: (Set<Material>, Set<Tag<Material>>) -> Unit) {
        val path = "/tags/" + key.key + ".json"
        val materials = mutableSetOf<Material>()
        val tags = mutableSetOf<Tag<Material>>()

        try {
            val stream = InfinityExpansion2::class.java.getResourceAsStream(path)
                ?: error("Tag file not found within jar: $path")
            val reader = JsonReader(InputStreamReader(stream, StandardCharsets.UTF_8))
            val root = parseReader(reader).asJsonObject
            root["values"].apply {
                if (!isJsonArray) error("No values array found in tag file")
            }.asJsonArray.forEach {
                if (it.isJsonPrimitive && it.asJsonPrimitive.isString) {
                    parseString(it.asString, materials, tags)
                } else {
                    error("Unexpected value format: ${it.javaClass.simpleName} - $it")
                }
            }

            callback(materials, tags)
        } catch (ex: Exception) {
            throw TagMisconfigurationException(key, ex)
        }
    }

    private fun parseString(value: String, materials: MutableSet<Material>, tags: MutableSet<Tag<Material>>) {
        if (Patterns.MINECRAFT_NAMESPACEDKEY.matcher(value).matches()) {
            Material.matchMaterial(value)?.apply {
                materials.add(this)
            }
        } else if (Patterns.IE_TAG.matcher(value).matches()) {
            val keyValue = CommonPatterns.COLON.split(value)[1].uppercase()
            IETag.getTag(keyValue)?.apply {
                tags.add(this)
            } ?: error("Tag not exist: $keyValue")
        }
    }

    companion object {
        private fun parseReader(reader: JsonReader) =
            if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_18)) {
                JsonParser.parseReader(reader)
            } else {
                JsonParser().parse(reader)
            }
    }
}
