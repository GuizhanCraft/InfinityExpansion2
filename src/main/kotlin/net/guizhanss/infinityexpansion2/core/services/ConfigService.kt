package net.guizhanss.infinityexpansion2.core.services

import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.config.QuarryPool
import net.guizhanss.infinityexpansion2.utils.clamp
import net.guizhanss.infinityexpansion2.utils.loadDoubleMap
import net.guizhanss.infinityexpansion2.utils.loadEnchantmentKeyMap
import net.guizhanss.infinityexpansion2.utils.loadEnumKeyMap
import net.guizhanss.infinityexpansion2.utils.loadSectionMap
import org.bukkit.World.Environment
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.enchantments.Enchantment

class ConfigService(plugin: InfinityExpansion2) {
    private val config = AddonConfig(plugin, "config.yml")

    var autoUpdate = true
        private set
    var debug = false
        private set
    var lang = "en"
        private set

    // balance options
    var singularityCostMultiplier = 1.0
        private set
    var allowSfItemTransform = false
        private set
    var enableResearches = false
        private set

    // resource synthesizer options
    var resourceSynthesizerRecipes: List<String> = listOf()
        private set

    // mob simulation options
    var mobSimInterval = 20
        private set
    var mobSimExpMultiplier = 1.0
        private set

    // quarry options
    var quarryInterval = 10
        private set
    var quarryPools: Map<Environment, QuarryPool> = mapOf()
        private set
    var quarryOcsillators: Map<String, Double> = mapOf()
        private set

    // advanced anvil options
    var advancedAnvilMaxLevels: Map<Enchantment, Int> = mapOf()
        private set

    // infinity gear enchantments
    var infinityGearEnchantLevels: Map<String, ConfigurationSection> = mapOf()
        private set

    init {
        reload()
    }

    fun reload() {
        config.reload()

        autoUpdate = config.getBoolean("auto-update", true)
        debug = config.getBoolean("debug", false)
        lang = config.getString("lang") ?: "en"
        singularityCostMultiplier = config.getDouble("balance.singularity-cost-multiplier", 1.0).clamp(0.0, 1000.0)
        allowSfItemTransform = config.getBoolean("balance.allow-sf-item-transform", false)
        enableResearches = config.getBoolean("balance.enable-researches", false)
        resourceSynthesizerRecipes = config.getStringList("resource-synthesizer.recipes")
        mobSimInterval = config.getInt("mob-simulation.output-interval", 20).clamp(1, 3600)
        mobSimExpMultiplier = config.getDouble("mob-simulation.exp-multiplier", 1.0).clamp(0.0, 1000.0)
        quarryInterval = config.getInt("quarry.output-interval", 10).clamp(1, 3600)
        quarryOcsillators = loadDoubleMap(config.getConfigurationSection("quarry.ocsillators"))
        quarryPools = loadEnumKeyMap<Environment, QuarryPool>(config.getConfigurationSection("quarry.pools"),
            { obj -> QuarryPool(obj as ConfigurationSection) })
        advancedAnvilMaxLevels = loadEnchantmentKeyMap(config.getConfigurationSection("advanced-anvil.max-levels"))
        infinityGearEnchantLevels = loadSectionMap(config.getConfigurationSection("infinity-gear-enchantments"))

        config.save()
    }
}
