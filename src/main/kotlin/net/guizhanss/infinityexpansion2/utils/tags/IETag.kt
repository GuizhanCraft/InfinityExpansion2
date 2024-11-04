package net.guizhanss.infinityexpansion2.utils.tags

import io.github.thebusybiscuit.slimefun4.api.exceptions.TagMisconfigurationException
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.bukkitext.createKey
import org.bukkit.Material
import org.bukkit.Tag
import java.util.logging.Level

enum class IETag : Tag<Material> {
    /**
     * All the swords.
     */
    SWORD,

    /**
     * All the pickaxes.
     */
    PICKAXE,

    /**
     * All the axes.
     */
    AXE,

    /**
     * All the shovels.
     */
    SHOVEL,

    /**
     * All the hoes.
     */
    HOE,

    /**
     * All the helmets.
     */
    HELMET,

    /**
     * All the chestplates.
     */
    CHESTPLATE,

    /**
     * All the leggings.
     */
    LEGGINGS,

    /**
     * All the boots.
     */
    BOOTS,

    /**
     * Short and tall grass.
     */
    GRASS,

    /**
     * All the tools that can be upgraded in Gear Transformer
     */
    UPGRADABLE_TOOL,

    /**
     * All the armors that can be upgraded in Gear Transformer
     */
    UPGRADABLE_ARMOR,

    /**
     * All the blocks that vein miner can break.
     */
    VEIN_MINER_BLOCKS,

    ;

    private val key = name.createKey()
    private val materials = mutableSetOf<Material>()
    private val additionalTags = mutableSetOf<Tag<Material>>()

    override fun getKey() = key

    override fun isTagged(material: Material) =
        if (materials.contains(material)) {
            true
        } else {
            additionalTags.any { it.isTagged(material) }
        }

    override fun getValues() =
        if (additionalTags.isEmpty()) {
            materials.toSet()
        } else {
            materials.union(additionalTags.flatMap { it.values }).toSet()
        }

    fun isEmpty() = materials.isEmpty() && values.isEmpty()

    /**
     * Reload this [IETag] from resources.
     */
    private fun reload() {
        try {
            TagParser(this).parse { materialSet, additionalTagSet ->
                materials.clear()
                materials.addAll(materialSet)

                additionalTags.clear()
                additionalTags.addAll(additionalTagSet)
            }
        } catch (ex: TagMisconfigurationException) {
            InfinityExpansion2.log(
                Level.SEVERE, ex,
                "An error has occurred while trying to load tag: $name"
            )
        }
    }

    companion object {

        /**
         * Get a tag by the name. null if not found.
         */
        fun getTag(name: String) = entries.firstOrNull { it.name == name }

        /**
         * Reload all tags.
         */
        fun reloadAll() {
            entries.forEach { it.reload() }
        }
    }
}
