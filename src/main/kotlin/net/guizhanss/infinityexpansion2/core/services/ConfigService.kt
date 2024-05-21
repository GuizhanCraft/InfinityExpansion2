package net.guizhanss.infinityexpansion2.core.services

import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.utils.ConfigUtils
import org.bukkit.configuration.ConfigurationSection

class ConfigService(private val plugin: InfinityExpansion2) {
    private var config: AddonConfig = AddonConfig(plugin, "config.yml")

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

    // mob simulation options
    var mobSimTickRate = 20
        private set
    var mobSimExpMultiplier = 1.0
        private set

    // quarry options
    var quarryOcsillators: Map<String, Double> = mapOf()
        private set
    var quarryTickRate = 10
        private set
    var quarryAllowNether = false
        private set
    var quarryResources: Map<String, Boolean> = mapOf()
        private set

    // advanced anvil options
    var advancedAnvilMaxLevels: Map<String, Int> = mapOf()
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
        lang = config.getString("lang", "en")!!
        singularityCostMultiplier = config.getDouble("balance.singularity-cost-multiplier", 1.0)
        allowSfItemTransform = config.getBoolean("balance.allow-sf-item-transform", false)
        enableResearches = config.getBoolean("balance.enable-researches", false)
        mobSimTickRate = config.getInt("mob-simulation.tick-rate", 20)
        mobSimExpMultiplier = config.getDouble("mob-simulation.exp-multiplier", 1.0)
        quarryOcsillators = ConfigUtils.loadDoubleMap(config.getConfigurationSection("quarry.ocsillators"))
        quarryTickRate = config.getInt("quarry.tick-rate", 10)
        quarryAllowNether = config.getBoolean("quarry.nether-materials-in-overworld", false)
        quarryResources = ConfigUtils.loadBooleanMap(config.getConfigurationSection("quarry.resources"))
        advancedAnvilMaxLevels = ConfigUtils.loadIntMap(config.getConfigurationSection("advanced-anvil.max-levels"))
        infinityGearEnchantLevels =
            ConfigUtils.loadSectionMap(config.getConfigurationSection("infinity-gear-enchantments"))

        config.save()
    }
}
