package net.guizhanss.infinityexpansion2.utils

import me.mrCookieSlime.Slimefun.api.BlockStorage
import org.bukkit.Location

object BlockStorageUtils {
    fun getBoolean(l: Location, key: String) = BlockStorage.getLocationInfo(l, key).toBoolean()
    fun setBoolean(l: Location, key: String, value: Boolean) = BlockStorage.addBlockInfo(l, key, value.toString())
}
