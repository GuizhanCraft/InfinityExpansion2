@file:Suppress("unused")

package net.guizhanss.infinityexpansion2.utils.slimefunext

import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition
import me.mrCookieSlime.Slimefun.api.BlockStorage
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu
import org.bukkit.Location
import org.bukkit.block.Block

// string
fun Location.getString(key: String): String? = BlockStorage.getLocationInfo(this, key)
fun Block.getString(key: String): String? = location.getString(key)
fun Location.getString(key: String, defaultValue: String): String = getString(key) ?: defaultValue
fun Block.getString(key: String, defaultValue: String): String = location.getString(key, defaultValue)
fun Location.setString(key: String, value: String) = BlockStorage.addBlockInfo(this, key, value)
fun Block.setString(key: String, value: String) = location.setString(key, value)
fun Location.hasData(key: String): Boolean = getString(key) != null
fun Block.hasData(key: String): Boolean = location.hasData(key)
fun Location.removeData(key: String) = BlockStorage.addBlockInfo(this, key, null)
fun Block.removeData(key: String) = location.removeData(key)

// boolean
fun Location.getBoolean(key: String) = getString(key).toBoolean()
fun Block.getBoolean(key: String) = location.getBoolean(key)
fun Location.setBoolean(key: String, value: Boolean) = setString(key, value.toString())
fun Block.setBoolean(key: String, value: Boolean) = location.setBoolean(key, value)

// int
fun Location.getInt(key: String, defaultValue: Int = 0) = getString(key)?.toIntOrNull() ?: defaultValue
fun Block.getInt(key: String, defaultValue: Int = 0) = location.getInt(key, defaultValue)
fun Location.setInt(key: String, value: Int) = setString(key, value.toString())
fun Block.setInt(key: String, value: Int) = location.setInt(key, value)

// inventory
fun Location.getBlockMenu(): BlockMenu? = BlockStorage.getInventory(this)
fun Block.getBlockMenu() = location.getBlockMenu()

// BlockPosition
val Location.blockPosition: BlockPosition
    get() = BlockPosition(this)
val Block.blockPosition: BlockPosition
    get() = BlockPosition(this)
