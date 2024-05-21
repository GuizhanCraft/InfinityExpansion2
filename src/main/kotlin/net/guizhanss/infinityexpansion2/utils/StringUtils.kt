package net.guizhanss.infinityexpansion2.utils

import net.guizhanss.guizhanlib.utils.StringUtil

object StringUtils {
    fun String.toId() = StringUtil.dehumanize(this).uppercase()
}
