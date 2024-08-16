package net.guizhanss.infinityexpansion2.utils.general

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * [RequiredProperty](https://github.com/Slimefun-Addon-Community/Galactifun2/blob/master/plugin/src/main/kotlin/io/github/addoncommunity/galactifun/util/general/RequiredProperty.kt)
 * from Slimefun-Addon-Community/Galactifun2
 */
class RequiredProperty<T>(
    private var value: T? = null,
    private val getter: (T) -> T = { it },
    private val setter: (T) -> T = { it }
) : ReadWriteProperty<Any, T> {

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return getter(value ?: error("${property.name} must be set"))
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        this.value = setter(value)
    }
}
