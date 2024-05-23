package net.guizhanss.infinityexpansion2.core.services

import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.listeners.TranslationsLoadListener
import net.guizhanss.infinityexpansion2.utils.items.MaterialType
import net.guizhanss.slimefuntranslation.api.SlimefunTranslationAPI
import net.guizhanss.slimefuntranslation.api.config.TranslationConfiguration
import net.guizhanss.slimefuntranslation.api.config.TranslationConfigurationDefaults
import net.guizhanss.slimefuntranslation.api.config.TranslationConfigurationFields
import net.guizhanss.slimefuntranslation.utils.FileUtils
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File

class IntegrationService(private val plugin: InfinityExpansion2) {
    var slimefunTranslationEnabled = isPluginEnabled("SlimefunTranslation")
        private set

    private fun isPluginEnabled(pluginName: String) = plugin.server.pluginManager.isPluginEnabled(pluginName)

    /**
     * This function should be called by [TranslationsLoadListener] to load translations.
     */
    fun loadTranslations() {
        val fields = TranslationConfigurationFields.builder().items("items").lore("lores").build()
        val defaults = TranslationConfigurationDefaults.builder().name("InfinityExpansion2").build()
        val languages = FileUtils.listYamlFiles(File(plugin.dataFolder, "lang"))
        for (langFile in languages) {
            val file = File(plugin.dataFolder, "lang" + File.separator + langFile)
            val lang = langFile.replace(".yml", "")
            val fileConfig = YamlConfiguration.loadConfiguration(file)
            val cfg = TranslationConfiguration.fromFileConfiguration(lang, fileConfig, fields, defaults)
            cfg.ifPresent { it.register(plugin) }
        }
    }

    /**
     * Get the translated lore line for [Player].
     */
    fun getLore(p: Player, id: String) =
        if (slimefunTranslationEnabled) {
            SlimefunTranslationAPI.getLore(SlimefunTranslationAPI.getUser(p), id, true)
        } else {
            InfinityExpansion2.localization.getLore(id)
        }

    /**
     * Get the translated item group name for [Player].
     */
    fun getItemGroupName(p: Player, id: String): String {
        return if (slimefunTranslationEnabled) {
            val item = InfinityExpansion2.localization.getItemGroupItem(MaterialType.Material(Material.BARRIER), id)
            SlimefunTranslationAPI.translateItem(SlimefunTranslationAPI.getUser(p), item)
            item.displayName!!
        } else {
            InfinityExpansion2.localization.getItemGroupName(id)
        }
    }
}
