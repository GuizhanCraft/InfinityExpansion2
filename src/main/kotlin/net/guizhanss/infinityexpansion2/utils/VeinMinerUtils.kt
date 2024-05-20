package net.guizhanss.infinityexpansion2.utils

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import net.guizhanss.infinityexpansion2.implementation.IEItems
import org.bukkit.inventory.ItemStack

object VeinMinerUtils {
    fun isRune(item: ItemStack?) =
        Slimefun.getItemDataService().getItemData(item).map { it == IEItems.VEIN_MINER_RUNE.itemId }.orElse(false)

}
