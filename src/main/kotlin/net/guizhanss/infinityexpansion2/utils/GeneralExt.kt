package net.guizhanss.infinityexpansion2.utils

import net.guizhanss.guizhanlib.common.utils.StringUtil

inline fun <reified T : Enum<T>> valueOfOrNull(name: String): T? = enumValues<T>().firstOrNull { it.name == name }

fun String.toId() = StringUtil.dehumanize(this)

fun <T> Pair<T, T>.matches(obj1: T, obj2: T) =
    (first == obj1 && second == obj2) || (first == obj2 && second == obj1)

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
