@file:Suppress("deprecation")

package net.guizhanss.infinityexpansion2.implementation.setup

import net.guizhanss.guizhanlib.common.utils.StringUtil
import net.guizhanss.guizhanlib.kt.minecraft.extensions.isAir
import net.guizhanss.guizhanlib.kt.slimefun.items.toItem
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.api.InfinityExpansion2API
import net.guizhanss.infinityexpansion2.api.mobsim.MobDataCardProps
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.utils.items.toItemStack
import org.bukkit.ChatColor
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.inventory.ItemStack
import java.util.logging.Level

/**
 * Read the mob simulation config and load the data cards.
 */
internal object MobSimulationSetup {

    init {
        val cfg = InfinityExpansion2.configService.mobSimConfig
        InfinityExpansion2.log(Level.INFO, "Loading mob simulation data cards...")
        if (!InfinityExpansion2.configService.debug.value) {
            InfinityExpansion2.log(Level.INFO, "If you encounter any issues, enabling debug mode may help.")
        }
        cfg.keys.forEach cfg@{ key ->
            val section = cfg.configuration.getConfigurationSection(key) ?: return@cfg

            // check for enabled
            if (!section.getBoolean("enabled", false)) return@cfg

            InfinityExpansion2.debug("====================")
            InfinityExpansion2.debug("Loading mob data card: $key")

            // load data
            val name = section.getString("name", "${ChatColor.BLUE}${StringUtil.humanize(key)}")!!
            val texture = section.getString("texture", "IRON_CHESTPLATE")!!.toItemStack()
            val energy = section.getInt("energy", 75).coerceIn(0, 1_000_000)
            val experience = section.getInt("experience").coerceIn(0, Int.MAX_VALUE)

            InfinityExpansion2.debug("name=$name, texture=$texture, energy=$energy, experience=$experience")

            // drops
            val drops = section.getMapList("drops").mapNotNull { it.getAsItemWithChance() }
            InfinityExpansion2.debug("drops=$drops")

            // recipe
            val recipePattern = section.getStringList("recipe.pattern")

            // validate pattern
            if (recipePattern.size != 3 || recipePattern.any { it.length != 3 }) {
                InfinityExpansion2.log(Level.WARNING, "Invalid recipe pattern for $key, must be in a 3x3 shape.")
                return@cfg
            }

            // find if there is 1 and only 1 X in the pattern
            if (recipePattern.sumOf { it.count { c -> c == 'X' } } != 1) {
                InfinityExpansion2.log(
                    Level.WARNING, "Invalid recipe pattern for $key, must have only 1 'X' in the whole pattern."
                )
                return@cfg
            }

            // read the ingredients and build recipe
            val ingredients = mutableMapOf<Char, ItemStack>()
            val ingredientSection = section.getConfigurationSection("recipe.ingredient") ?: run {
                InfinityExpansion2.log(Level.WARNING, "No ingredients found for $key.")
                return@cfg
            }
            ingredientSection.getKeys(false).forEach { ingredient ->
                if (ingredient.length != 1) {
                    InfinityExpansion2.log(
                        Level.WARNING, "Invalid ingredient \"$ingredient\" for $key: Must be a single character."
                    )
                    return@cfg
                }
                val item = ingredientSection.getConfigurationSection(ingredient).getAsItem()
                if (item == null) {
                    InfinityExpansion2.log(
                        Level.WARNING, "Invalid ingredient \"$ingredient\" for $key: Invalid item section."
                    )
                    return@cfg
                }
                ingredients[ingredient[0]] = item
            }
            InfinityExpansion2.debug("pattern=$recipePattern, ingredients=$ingredients")

            val recipe = arrayOfNulls<ItemStack?>(9)
            for (i in recipePattern.indices) {
                for (j in recipePattern[i].indices) {
                    val char = recipePattern[i][j]
                    val index = i * 3 + j
                    if (char == ' ') continue
                    if (char == 'X') {
                        recipe[index] = IEItems.MOB_DATA_CARD_EMPTY.toItem()
                        continue
                    }

                    if (char !in ingredients) {
                        InfinityExpansion2.log(
                            Level.WARNING, "Invalid recipe pattern for $key: Unknown ingredient \"$char\"."
                        )
                        return@cfg
                    } else {
                        recipe[index] = ingredients[char]
                    }
                }
            }

            // register the mob data card
            InfinityExpansion2API.registerMobDataCard(
                MobDataCardProps(
                    key, name, texture, energy, experience, drops, recipe
                ), InfinityExpansion2.instance
            )
        }
    }

    private fun Map<*, *>.getAsItem(): ItemStack? {
        val mat = this["item"] as? String ?: return null
        val amount = this["amount"] as? Int ?: 1

        val item = mat.toItemStack().let { if (it.isAir()) return null else it }.clone()
        item.amount = amount
        return item
    }

    private fun ConfigurationSection?.getAsItem() = this?.getValues(false)?.getAsItem()

    private fun Map<*, *>.getAsItemWithChance(): Pair<ItemStack, Double>? {
        val item = this.getAsItem() ?: return null
        val chance = this["chance"] as? Double ?: 1.0
        return item to chance
    }
}
