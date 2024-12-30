package net.guizhanss.infinityexpansion2.core

import net.guizhanss.infinityexpansion2.api.mobsim.MobDataCardProps
import net.guizhanss.infinityexpansion2.core.recipes.MachineRecipe
import net.guizhanss.infinityexpansion2.implementation.items.materials.Singularity
import org.bukkit.inventory.ItemStack

internal object IERegistry {

    val itemMapping = mutableMapOf<String, ItemStack>() // ID to ItemStack, either vanilla or SlimefunItemStack
    val singularities = mutableListOf<Singularity>()
    val singularityIngredientMap = mutableMapOf<ItemStack, Singularity>() // ingredient to Singularity
    val infinityRecipes = mutableListOf<MachineRecipe>() // infinity workbench recipes
    val mobDataCards = mutableMapOf<String, MobDataCardProps>() // id to props
    val mobDataInfuserRecipes = mutableListOf<MachineRecipe>() // mob data infuser recipes
    val storageForgeRecipes = mutableListOf<MachineRecipe>() // storage forge recipes
}
