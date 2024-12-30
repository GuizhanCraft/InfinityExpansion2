package net.guizhanss.infinityexpansion2.core.items.attributes

import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectiveArmor

interface ProtectiveArmorExtra : ProtectiveArmor {

    fun getAllProtectionTypes(): Array<ProtectionType>

    /**
     * The implementing class should never override this, use [getAllProtectionTypes] instead.
     */
    override fun getProtectionTypes() =
        getAllProtectionTypes().mapNotNull { it.asSlimefun() }.toTypedArray()
}
