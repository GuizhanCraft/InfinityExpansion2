package net.guizhanss.infinityexpansion2.implementation.recipes

import io.github.seggan.sf4k.item.builder.asMaterialType
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.IERegistry
import net.guizhanss.infinityexpansion2.core.recipes.MachineRecipe
import net.guizhanss.infinityexpansion2.utils.bukkitext.createKey
import org.bukkit.Material

object IERecipeTypes {

    val VOID_HARVESTER = RecipeType(
        "void_harvester".createKey(),
        InfinityExpansion2.localization.getRecipeTypeItem(
            Material.OBSIDIAN.asMaterialType(),
            "void_harvester"
        )
    )

    val INFINITY_WORKBENCH = RecipeType(
        "infinity_workbench".createKey(),
        InfinityExpansion2.localization.getRecipeTypeItem(
            Material.SMITHING_TABLE.asMaterialType(),
            "infinity_workbench"
        ),
        { recipe, output ->
            IERegistry.infinityRecipes.add(MachineRecipe.of(recipe, output))
        }
    )

    val SINGULARITY_CONSTRUCTOR = RecipeType(
        "singularity_constructor".createKey(),
        InfinityExpansion2.localization.getRecipeTypeItem(
            Material.QUARTZ_BLOCK.asMaterialType(),
            "singularity_constructor"
        )
    )

    val MOB_DATA_INFUSER = RecipeType(
        "mob_data_infuser".createKey(),
        InfinityExpansion2.localization.getRecipeTypeItem(
            Material.LODESTONE.asMaterialType(),
            "mob_data_infuser"
        ),
        { recipe, output ->
            IERegistry.mobDataInfuserRecipes.add(MachineRecipe.of(recipe, output))
        }
    )
}
