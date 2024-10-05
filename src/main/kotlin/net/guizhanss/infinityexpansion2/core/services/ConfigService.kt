package net.guizhanss.infinityexpansion2.core.services

import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config
import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.config.QuarryPool
import net.guizhanss.infinityexpansion2.core.config.ResourceSynthesizerRecipe
import net.guizhanss.infinityexpansion2.utils.bukkitext.getAsSerializable
import net.guizhanss.infinityexpansion2.utils.bukkitext.getAsSerializableList
import net.guizhanss.infinityexpansion2.utils.bukkitext.loadDoubleMap
import net.guizhanss.infinityexpansion2.utils.bukkitext.loadEnchantmentKeyMap
import net.guizhanss.infinityexpansion2.utils.bukkitext.loadEnumKeyMap
import net.guizhanss.infinityexpansion2.utils.bukkitext.loadSectionMap
import net.guizhanss.infinityexpansion2.utils.clamp
import org.bukkit.World.Environment
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.enchantments.Enchantment

class ConfigService(plugin: InfinityExpansion2) {

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
    var resourceSynthesizerRecipes: List<ResourceSynthesizerRecipe> = listOf()
        private set

    // mob simulation options
    var mobSimInterval = 20
        private set
    var mobSimAllowStackedCard = false
        private set
    var mobSimExpMultiplier = 1.0
        private set
    var mobSimLegacyOutput = false
        private set

    // quarry options
    var quarryInterval = 10
        private set
    var quarryPools: Map<Environment, QuarryPool> = emptyMap()
        private set
    var quarryOscillators: Map<String, Double> = emptyMap()
        private set

    // advanced anvil options
    var advancedAnvilMaxLevels: Map<Enchantment, Int> = emptyMap()
        private set

    // infinity gear section
    var infinityGear: Map<String, ConfigurationSection> = emptyMap()
        private set

    private val config = AddonConfig(plugin, "config.yml")
    val mobSimConfig = Config(plugin, "mob-simulation.yml")

    init {
        if (!mobSimConfig.file.exists()) {
            plugin.saveResource("mob-simulation.yml", false)
        }
        reload()
    }

    fun reload() {
        config.reload()
        mobSimConfig.reload()

        autoUpdate = config.getBoolean("auto-update", true)
        debug = config.getBoolean("debug", false)
        lang = config.getString("lang") ?: "en"
        singularityCostMultiplier = config.getDouble("balance.singularity-cost-multiplier", 1.0).clamp(0.0, 1000.0)
        allowSfItemTransform = config.getBoolean("balance.allow-sf-item-transform", false)
        enableResearches = config.getBoolean("balance.enable-researches", false)
        resourceSynthesizerRecipes = config.getMapList("resource-synthesizer.recipes")
            .getAsSerializableList<ResourceSynthesizerRecipe>()
        mobSimInterval = config.getInt("mob-simulation.output-interval", 20).clamp(1, 3600)
        mobSimAllowStackedCard = config.getBoolean("mob-simulation.allow-stacked-card", false)
        mobSimExpMultiplier = config.getDouble("mob-simulation.exp-multiplier", 1.0).clamp(0.0, 1000.0)
        mobSimLegacyOutput = config.getBoolean("mob-simulation.legacy-output", false)
        quarryInterval = config.getInt("quarry.output-interval", 10).clamp(1, 3600)
        quarryOscillators = config.getConfigurationSection("quarry.oscillators").loadDoubleMap()
        quarryPools = config.getConfigurationSection("quarry.pools")
            .loadEnumKeyMap<Environment, QuarryPool>({ obj -> (obj as ConfigurationSection).getAsSerializable() })
        advancedAnvilMaxLevels = config.getConfigurationSection("advanced-anvil.max-levels").loadEnchantmentKeyMap()
        infinityGear = config.getConfigurationSection("infinity-gear").loadSectionMap()

        config.save()
    }
}
