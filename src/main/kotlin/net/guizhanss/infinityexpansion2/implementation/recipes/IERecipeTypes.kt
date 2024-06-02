package net.guizhanss.infinityexpansion2.implementation.recipes

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.createKey
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

    val INFINITY_WORKBENCH = RecipeType(
        "infinity_workbench".createKey(),
        InfinityExpansion2.localization.getRecipeTypeItem(
            MaterialType.Material(Material.SMITHING_TABLE),
            "infinity_workbench"
        )
    )

    val SINGULARITY_CONSTRUCTOR = RecipeType(
        "singularity_constructor".createKey(),
        InfinityExpansion2.localization.getRecipeTypeItem(
            MaterialType.Material(Material.QUARTZ_BLOCK),
            "singularity_constructor"
        )
    )
}
