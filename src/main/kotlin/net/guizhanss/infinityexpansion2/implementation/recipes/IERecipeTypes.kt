package net.guizhanss.infinityexpansion2.implementation.recipes

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.constant.Keys.createKey
import net.guizhanss.infinityexpansion2.utils.items.MaterialType
import org.bukkit.Material

object IERecipeTypes {
    val VOID_HARVESTER = RecipeType(
        "void_harvester".createKey(),
        InfinityExpansion2.localization.getRecipeTypeItem(
            MaterialType.Material(Material.OBSIDIAN),
            "void_harvester"
        )
    )
}
