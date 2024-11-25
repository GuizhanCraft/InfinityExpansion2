package net.guizhanss.infinityexpansion2.utils.items.builder

import io.github.seggan.sf4k.item.builder.MaterialType
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture

fun String.asMaterialType() = MaterialType.Head(this)
fun HeadTexture.asMaterialType() = MaterialType.Head(this.texture)
