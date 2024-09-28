package net.guizhanss.infinityexpansion2.implementation.recipes

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.IERegistry
import net.guizhanss.infinityexpansion2.core.recipes.MachineRecipe
import net.guizhanss.infinityexpansion2.utils.bukkitext.createKey
import net.guizhanss.infinityexpansion2.utils.items.convert
import org.bukkit.Material

object IERecipeTypes {

    val VOID_HARVESTER = RecipeType(
        "void_harvester".createKey(),
        InfinityExpansion2.localization.getRecipeTypeItem(
            Material.OBSIDIAN.convert(),
            "void_harvester"
        )
    )

    val INFINITY_WORKBENCH = RecipeType(
        "infinity_workbench".createKey(),
        InfinityExpansion2.localization.getRecipeTypeItem(
            Material.SMITHING_TABLE.convert(),
            "infinity_workbench"
        ),
        { recipe, output ->
            IERegistry.infinityRecipes.add(MachineRecipe.of(recipe, output))
        }
    )

    val SINGULARITY_CONSTRUCTOR = RecipeType(
        "singularity_constructor".createKey(),
        InfinityExpansion2.localization.getRecipeTypeItem(
            Material.QUARTZ_BLOCK.convert(),
            "singularity_constructor"
        )
    )

    val MOB_DATA_INFUSER = RecipeType(
        "mob_data_infuser".createKey(),
        InfinityExpansion2.localization.getRecipeTypeItem(
            Material.LODESTONE.convert(),
            "mob_data_infuser"
        ),
        { recipe, output ->
            IERegistry.mobDataInfuserRecipes.add(MachineRecipe.of(recipe, output))
        }
    )
}
