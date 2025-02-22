package net.guizhanss.infinityexpansion2.core.items.annotations

/**
 * Indicates the SlimefunItem class provides SlimeHUD information.
 *
 * Must be annotated on a SlimefunItem class.
 *
 * The class must have a companion function named `hudHandler`,
 * which accepts a `HudRequest` and returns the string.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class HudProvider
