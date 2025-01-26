package net.guizhanss.infinityexpansion2.utils.slimefunext

import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu

val BlockMenu.blockPosition: BlockPosition
    get() = BlockPosition(location)
