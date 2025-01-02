package net.guizhanss.infinityexpansion2.core.items.attributes

import net.guizhanss.infinityexpansion2.utils.bukkitext.createKey
import org.bukkit.Keyed
import org.bukkit.NamespacedKey
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectionType as SfProtectionType

/**
 * All the protection types supported in IE2, which also maps to the protection types in Slimefun.
 */
@Suppress("unused")
interface ProtectionType : Keyed {

    fun asSlimefun(): SfProtectionType?

    companion object {

        private val values = mutableListOf<ProtectionType>()

        @JvmStatic
        val RADIATION = create("radiation", SfProtectionType.RADIATION)

        @JvmStatic
        val BEES = create("bees", SfProtectionType.BEES)

        @JvmStatic
        val FLYING_INTO_WALL = create("flying_into_wall", SfProtectionType.FLYING_INTO_WALL)

        @JvmStatic
        val FALL = create("fall")

        private fun create(name: String, sfProtectionType: SfProtectionType? = null): ProtectionType {
            val result = object : ProtectionType {
                override fun asSlimefun() = sfProtectionType
                override fun getKey() = name.createKey()
            }
            values.add(result)
            return result
        }

        @JvmStatic
        fun getByKey(key: NamespacedKey) = values.find { it.key == key }
    }
}
