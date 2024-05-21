package net.guizhanss.infinityexpansion2.implementation.items.groups.infinity

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import net.guizhanss.infinityexpansion2.implementation.items.groups.IEItemGroups
import net.guizhanss.infinityexpansion2.implementation.items.groups.SubGroup
import net.guizhanss.infinityexpansion2.implementation.recipes.IERecipeTypes
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack

class InfinityDisplayGroup(
    key: NamespacedKey,
    item: ItemStack
) : SubGroup(key, item) {
    override fun add(item: SlimefunItem) {
        if (item.recipeType != IERecipeTypes.INFINITY_WORKBENCH) return

        super.add(item)

        IEItemGroups.INFINITY.addItem(item)
    }
}
