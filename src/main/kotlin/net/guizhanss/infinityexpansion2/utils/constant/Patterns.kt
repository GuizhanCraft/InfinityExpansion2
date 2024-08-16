package net.guizhanss.infinityexpansion2.utils.constant

import java.util.regex.Pattern

object Patterns {

    val MINECRAFT_NAMESPACEDKEY = Pattern.compile("minecraft:[a-z0-9/._-]+")
    val IE_TAG = Pattern.compile("#ie:[a-z_]+")
}
