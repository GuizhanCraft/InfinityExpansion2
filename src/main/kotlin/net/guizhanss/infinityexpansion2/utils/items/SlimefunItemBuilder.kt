package net.guizhanss.infinityexpansion2.utils.items

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.guizhanlib.minecraft.utils.ChatUtil
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.emptyRecipe
import net.guizhanss.infinityexpansion2.utils.general.RequiredProperty
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.logging.Level
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

/**
 * Modified from [ItemBuilder](https://github.com/Slimefun-Addon-Community/Galactifun2/blob/master/plugin/src/main/kotlin/io/github/addoncommunity/galactifun/util/items/SlimefunItemBuilder.kt)
 * in project Slimefun-Addon-Community/Galactifun2
 */
class SlimefunItemBuilder {

    var id: String by RequiredProperty()
    var material: MaterialType by RequiredProperty()
    var amount: Int by RequiredProperty(1)

    var itemGroup: ItemGroup by RequiredProperty()
    var recipeType: RecipeType by RequiredProperty()
    var recipe: Array<out ItemStack?> by RequiredProperty(emptyRecipe())

    var postCreate: (SlimefunItemStack) -> Unit = {}
    var preRegister: (SlimefunItem) -> Unit = {}

    private val extraLore = mutableListOf<String>()

    operator fun String.unaryPlus() {
        extraLore += ChatUtil.color(this)
    }

    fun build(clazz: KClass<out SlimefunItem>, vararg otherArgs: Any?): SlimefunItemStack {
        val name = InfinityExpansion2.localization.getItemName(id)
        val lore = InfinityExpansion2.localization.getItemLore(id).toMutableList() + extraLore
        val sfItem = SlimefunItemStack(
            "${InfinityExpansion2.localization.idPrefix}$id",
            material.convert(),
            name,
            *lore.toTypedArray()
        )
        sfItem.amount = amount
        postCreate(sfItem)
        val constructor = clazz.primaryConstructor ?: error("Primary constructor not found for $clazz")
        val item = constructor.call(itemGroup, sfItem, recipeType, recipe, *otherArgs)
        preRegister(item)
        item.register(InfinityExpansion2.instance)
        return sfItem
    }
}

inline fun <reified I : SlimefunItem> buildSlimefunItem(
    vararg otherArgs: Any?,
    builder: SlimefunItemBuilder.() -> Unit
): SlimefunItemStack {
    try {
        return SlimefunItemBuilder().apply(builder).build(I::class, *otherArgs)
    } catch (e: Exception) {
        InfinityExpansion2.log(Level.SEVERE, e, "Failed to build Slimefun item")
        return SlimefunItemStack("ERROR", ItemStack(Material.AIR))
    }
}
