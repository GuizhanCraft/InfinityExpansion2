package net.guizhanss.infinityexpansion2.utils

inline fun <reified T : Enum<T>> valueOfOrNull(name: String): T? =
    enumValues<T>().firstOrNull { it.name == name }
