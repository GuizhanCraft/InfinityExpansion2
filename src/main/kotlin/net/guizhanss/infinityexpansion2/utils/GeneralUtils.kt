package net.guizhanss.infinityexpansion2.utils

import net.guizhanss.guizhanlib.utils.StringUtil

inline fun <reified T : Enum<T>> valueOfOrNull(name: String): T? = enumValues<T>().firstOrNull { it.name == name }

fun String.toId() = StringUtil.dehumanize(this).uppercase()

fun Pair<String, String>.matches(str1: String, str2: String) =
    (first == str1 && second == str2) || (first == str2 && second == str1)

fun Int.clamp(min: Int, max: Int) = when {
    this < min -> min
    this > max -> max
    else -> this
}

fun Double.clamp(min: Double, max: Double) = when {
    this < min -> min
    this > max -> max
    else -> this
}
