package net.guizhanss.infinityexpansion2.utils.items.builder

import io.github.seggan.sf4k.item.builder.ItemProvider
import io.github.seggan.sf4k.item.builder.ItemRegistry
import io.github.seggan.sf4k.util.RequiredProperty
import io.github.seggan.sf4k.util.findConstructorFromArgs
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.guizhanlib.minecraft.utils.ChatUtil
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import org.bukkit.inventory.ItemStack
import java.util.logging.Level
import kotlin.properties.PropertyDelegateProvider
import kotlin.reflect.KClass

/**
 * The main DSL class for constructing a [SlimefunItem].
 *
 * Modified from [sf4k by Seggan](https://github.com/Seggan/sf4k/blob/master/src/main/kotlin/io/github/seggan/sf4k/item/builder/ItemBuilder.kt).
 */
class SlimefunItemBuilder(private val registry: ItemRegistry) {

    var id: String by RequiredProperty(setter = { it.uppercase() })
    var material: MaterialType by RequiredProperty()
    var amount: Int by RequiredProperty(1)

    var itemGroup: ItemGroup by RequiredProperty()
    var recipeType: RecipeType by RequiredProperty()
    var recipe: Array<out ItemStack?> by RequiredProperty(arrayOfNulls<ItemStack>(9))

    /**
     * This function is called after the [SlimefunItemStack] is created.
     */
    var itemModifier: (SlimefunItemStack) -> Unit = {}

    private val extraLore = mutableListOf<String>()

    operator fun String.unaryPlus() {
        extraLore += ChatUtil.color(this)
    }

    fun <T : SlimefunItem> build(clazz: KClass<T>, vararg otherArgs: Any?): Pair<SlimefunItemStack, T> {
        // SlimefunItemStack
        val name = InfinityExpansion2.localization.getItemName(id)
        val lore = InfinityExpansion2.localization.getItemLore(id).toMutableList() + extraLore
        val sfis = SlimefunItemStack(
            "${registry.prefix}$id",
            material.convert(),
            name,
            *lore.toTypedArray()
        )
        sfis.amount = amount
        itemModifier(sfis)

        // SlimefunItem
        val args = arrayOf(itemGroup, sfis, recipeType, recipe, *otherArgs)
        val constructor = clazz.findConstructorFromArgs(*args) ?: error("Primary constructor not found for $clazz")
        val item = try {
            constructor.call(*args)
        } catch (e: Exception) {
            InfinityExpansion2.log(Level.SEVERE, e, "Failed to create SlimefunItem")
            throw e
        }
        item.register(InfinityExpansion2.instance)
        return sfis to item
    }
}

inline fun <reified I : SlimefunItem> ItemRegistry.buildSlimefunItem(
    vararg otherArgs: Any?,
    crossinline builder: SlimefunItemBuilder.() -> Unit
) = PropertyDelegateProvider<Any?, ItemProvider<I>> { _, property ->
    val itemBuilder = SlimefunItemBuilder(this)
    itemBuilder.id = property.name.uppercase()
    itemBuilder.apply(builder)
    val item = itemBuilder.build(I::class, *otherArgs)
    ItemProvider(item.first, item.second)
}
