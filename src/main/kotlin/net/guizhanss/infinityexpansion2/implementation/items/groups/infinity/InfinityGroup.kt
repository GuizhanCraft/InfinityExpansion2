package net.guizhanss.infinityexpansion2.implementation.items.groups.infinity

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.menu.FlexGroup
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class InfinityGroup(
    key: NamespacedKey,
    item: ItemStack
) : FlexGroup(key, item) {
    companion object {
        private const val GUIDE_BACK = 0
        private val BACKGROUND = arrayOf(9, 18, 27, 36, 45, 52, 53)
        private val INPUT_BORDER = arrayOf(7, 16, 17)
        private val OUTPUT_BORDER = arrayOf(25, 26, 34, 43, 44)
        private const val RECIPE_TYPE_SLOT = 8
        private const val OUTPUT_SLOT = 35
        private val RECIPE_AREA = arrayOf(
            1, 2, 3, 4, 5, 6,
            10, 11, 12, 13, 14, 15,
            19, 20, 21, 22, 23, 24,
            28, 29, 30, 31, 32, 33,
            37, 38, 39, 40, 41, 42,
            46, 47, 48, 49, 50, 51
        )
    }

    private val items: MutableList<SlimefunItem> = mutableListOf()

    override fun getGuideTitle(p: Player) = InfinityExpansion2.integrationService.getItemGroupName(p, "infinity")

    fun addItem(sfItem: SlimefunItem) {
        // TODO
    }
}
