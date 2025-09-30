package net.guizhanss.infinityexpansion2.core.services

import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config
import net.guizhanss.guizhanlib.kt.minecraft.extensions.loadDoubleMap
import net.guizhanss.guizhanlib.kt.minecraft.extensions.loadEnchantmentKeyMap
import net.guizhanss.guizhanlib.kt.minecraft.extensions.loadEnumKeyMap
import net.guizhanss.guizhanlib.kt.minecraft.extensions.loadSectionMap
import net.guizhanss.guizhanlib.kt.slimefun.config.ConfigField
import net.guizhanss.guizhanlib.kt.slimefun.config.addonConfig
import net.guizhanss.guizhanlib.kt.slimefun.config.migration.configMigrations
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.core.config.QuarryPool
import net.guizhanss.infinityexpansion2.core.config.ResourceSynthesizerRecipe
import net.guizhanss.infinityexpansion2.utils.bukkitext.getAsSerializable
import net.guizhanss.infinityexpansion2.utils.bukkitext.getAsSerializableList
import org.bukkit.World.Environment
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.enchantments.Enchantment

class ConfigService(plugin: InfinityExpansion2) {

    lateinit var autoUpdate: ConfigField<Boolean>
    lateinit var lang: ConfigField<String>
    lateinit var enableResearches: ConfigField<Boolean>

    // debug options
    lateinit var debugEnabled: ConfigField<Boolean>
    lateinit var debugCases: ConfigField<List<String>>

    // singularity options
    lateinit var singularityCostMultiplier: ConfigField<Double>

    // gear transformer options
    lateinit var gearTransformerAllowSfItems: ConfigField<Boolean>

    // resource synthesizer options
    lateinit var resourceSynthesizerRecipes: ConfigField<List<ResourceSynthesizerRecipe>>

    // mob simulation options
    lateinit var mobSimInterval: ConfigField<Int>
    lateinit var mobSimAllowStackedCard: ConfigField<Boolean>
    lateinit var mobSimExpMultiplier: ConfigField<Double>
    lateinit var mobSimLegacyOutput: ConfigField<Boolean>

    // storage options
    lateinit var storageEnableSigns: ConfigField<Boolean>
    lateinit var storageSignUpdateInterval: ConfigField<Int>
    lateinit var storageEnableHolograms: ConfigField<Boolean>
    lateinit var storageHologramUpdateInterval: ConfigField<Int>

    // quarry options
    lateinit var quarryInterval: ConfigField<Int>
    lateinit var quarryPools: ConfigField<Map<Environment, QuarryPool>>
    lateinit var quarryOscillators: ConfigField<Map<String, Double>>

    // advanced anvil options
    lateinit var advancedAnvilMaxLevels: ConfigField<Map<Enchantment, Int>>

    // infinity gear section
    lateinit var infinityGear: ConfigField<Map<String, ConfigurationSection>>

    private val configMigrations = configMigrations {
        add(1, 2) {
            move("balance.enable-researches", "enable-researches")
            move("balance.singularity-cost-multiplier", "singularity.cost-multiplier")
            move("balance.allow-sf-item-transform", "gear-transformer.allow-sf-items")
        }
        add(2, 3) {
            move("debug", "debug.enabled")
        }
    }

    private val config = addonConfig(plugin, "config.yml", configMigrations) {
        autoUpdate = boolean("auto-update", true)
        lang = string("lang", InfinityExpansion2.DEFAULT_LANG)
        enableResearches = boolean("enable-researches", false)
        debugEnabled = boolean("debug.enabled", false)
        debugCases = custom { it.getStringList("debug.cases") }
        singularityCostMultiplier = double("singularity.cost-multiplier", 1.0, 0.0, 1000.0)
        gearTransformerAllowSfItems = boolean("gear-transformer.allow-sf-items", false)
        resourceSynthesizerRecipes =
            custom { it.getMapList("resource-synthesizer.recipes").getAsSerializableList<ResourceSynthesizerRecipe>() }
        mobSimInterval = int("mob-simulation.output-interval", 20, 1, 3600)
        mobSimAllowStackedCard = boolean("mob-simulation.allow-stacked-card", false)
        mobSimExpMultiplier = double("mob-simulation.exp-multiplier", 1.0, 0.0, 1000.0)
        mobSimLegacyOutput = boolean("mob-simulation.legacy-output", false)
        storageEnableSigns = boolean("storage.enable-signs", false)
        storageSignUpdateInterval = int("storage.sign-update-interval", 20, 1, 3600)
        storageEnableHolograms = boolean("storage.enable-holograms", false)
        storageHologramUpdateInterval = int("storage.hologram-update-interval", 20, 1, 3600)
        quarryInterval = int("quarry.output-interval", 10, 1, 3600)
        quarryOscillators = custom { it.getConfigurationSection("quarry.oscillators").loadDoubleMap() }
        quarryPools = custom {
            it.getConfigurationSection("quarry.pools")
                .loadEnumKeyMap<Environment, QuarryPool>({ obj -> (obj as ConfigurationSection).getAsSerializable() })
        }
        advancedAnvilMaxLevels = custom {
            it.getConfigurationSection("advanced-anvil.max-levels").loadEnchantmentKeyMap()
        }
        infinityGear = custom {
            it.getConfigurationSection("infinity-gear").loadSectionMap()
        }
    }

    internal val mobSimConfig = Config(plugin, "mob-simulation.yml")

    init {
        if (!mobSimConfig.file.exists()) {
            plugin.saveResource("mob-simulation.yml", false)
        }
        reload()
    }

    fun reload() {
        config.reload()
        mobSimConfig.reload()
    }
}
