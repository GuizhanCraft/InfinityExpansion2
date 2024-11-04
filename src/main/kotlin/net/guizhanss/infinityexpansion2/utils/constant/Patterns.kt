package net.guizhanss.infinityexpansion2.utils.constant

import java.util.regex.Pattern

object Patterns {

    val MINECRAFT_NAMESPACEDKEY: Pattern = Pattern.compile("minecraft:[a-z0-9/._-]+")
    val MINECRAFT_TAG: Pattern = Pattern.compile("#minecraft:[a-z0-9/._-]+")
    val SLIMEFUN_TAG: Pattern = Pattern.compile("#slimefun:[a-z0-9/._-]+")
    val IE_TAG: Pattern = Pattern.compile("#ie:[a-z_]+")
}
