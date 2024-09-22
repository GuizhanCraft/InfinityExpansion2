package net.guizhanss.infinityexpansion2.utils

fun Map<*, *>.flatten(parentKey: String = "", separator: String = "."): Map<String, Any> {
    val flatMap = mutableMapOf<String, Any>()

    entries.forEach { (key, value) ->
        val fullKey = if (parentKey.isEmpty()) key.toString() else "$parentKey$separator$key"

        when (value) {
            is Map<*, *> -> {
                flatMap.putAll(value.flatten(fullKey, separator))
            }

            null -> {
                // Skip null values
            }

            else -> flatMap[fullKey] = value
        }
    }

    return flatMap
}