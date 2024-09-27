package net.guizhanss.infinityexpansion2.core.items.attributes

import io.github.thebusybiscuit.slimefun4.core.attributes.ItemAttribute

/**
 * The delayed task is executed after Slimefun registry finishes loading.
 */
interface DelayedTaskItem : ItemAttribute {

    fun delayedTask()

    val isSync: Boolean
        get() = true
}
