package net.guizhanss.infinityexpansion2.utils

import me.mrCookieSlime.Slimefun.api.BlockStorage
import org.bukkit.Location
import org.bukkit.block.Block

// string
fun Location.getData(key: String): String? = BlockStorage.getLocationInfo(this, key)
fun Block.getData(key: String): String? = location.getData(key)
fun Location.getData(key: String, defaultValue: String): String = getData(key) ?: defaultValue
fun Block.getData(key: String, defaultValue: String): String = location.getData(key, defaultValue)
fun Location.hasData(key: String): Boolean = getData(key) != null
fun Block.hasData(key: String): Boolean = location.hasData(key)
fun Location.setData(key: String, value: String) = BlockStorage.addBlockInfo(this, key, value)
fun Block.setData(key: String, value: String) = location.setData(key, value)
fun Location.removeData(key: String) = BlockStorage.addBlockInfo(this, key, null)
fun Block.removeData(key: String) = location.removeData(key)

// boolean
fun Location.getBoolean(key: String) = getData(key).toBoolean()
fun Block.getBoolean(key: String) = location.getBoolean(key)
fun Location.setBoolean(key: String, value: Boolean) = setData(key, value.toString())
fun Block.setBoolean(key: String, value: Boolean) = location.setBoolean(key, value)

// int
fun Location.getInt(key: String, defaultValue: Int = 0) = getData(key)?.toIntOrNull() ?: defaultValue
fun Block.getInt(key: String, defaultValue: Int = 0) = location.getInt(key, defaultValue)
fun Location.setInt(key: String, value: Int) = setData(key, value.toString())
fun Block.setInt(key: String, value: Int) = location.setInt(key, value)
