package net.guizhanss.infinityexpansion2.implementation.recipes

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import net.guizhanss.guizhanlib.kt.slimefun.items.builder.MaterialType
import net.guizhanss.guizhanlib.kt.slimefun.items.builder.asMaterialType
import net.guizhanss.guizhanlib.kt.slimefun.items.toItem
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.IERegistry
import net.guizhanss.infinityexpansion2.core.recipes.MachineRecipe
import net.guizhanss.infinityexpansion2.utils.bukkitext.createKey
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object IERecipeTypes {

    val VOID_HARVESTER = createRecipeType(
        "void_harvester", Material.OBSIDIAN.asMaterialType()
    )

    val INFINITY_WORKBENCH = createRecipeType(
        "infinity_workbench", Material.SMITHING_TABLE.asMaterialType()
    ) { recipe, output ->
        IERegistry.infinityRecipes.add(MachineRecipe.of(recipe, output))
    }

    val SINGULARITY_CONSTRUCTOR = createRecipeType(
        "singularity_constructor", Material.QUARTZ_BLOCK.asMaterialType()
    )

    val MOB_DATA_INFUSER = createRecipeType(
        "mob_data_infuser", Material.LODESTONE.asMaterialType()
    ) { recipe, output ->
        IERegistry.mobDataInfuserRecipes.add(MachineRecipe.of(recipe, output))
    }

    val STORAGE_FORGE = createRecipeType(
        "storage_forge", Material.BEEHIVE.asMaterialType()
    ) { recipe, output ->
        IERegistry.storageForgeRecipes.add(MachineRecipe.of(recipe, output))
    }

    private fun createRecipeType(
        key: String, material: MaterialType, callback: ((Array<out ItemStack>, ItemStack) -> Unit)? = null
    ) = RecipeType(
        key.createKey(), InfinityExpansion2.localization.getRecipeTypeItem(
            material, key
        ).toItem(), callback
    )
}

