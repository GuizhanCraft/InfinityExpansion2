package net.guizhanss.infinityexpansion2.core.services

import io.github.seggan.sf4k.item.builder.asMaterialType
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils
import net.guizhanss.infinityexpansion2.InfinityExpansion2
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.implementation.listeners.TranslationsLoadListener
import net.guizhanss.infinityexpansion2.integration.SlimeHUDIntegration
import net.guizhanss.infinityexpansion2.utils.items.removePrefix
import net.guizhanss.slimefuntranslation.api.SlimefunTranslationAPI
import net.guizhanss.slimefuntranslation.api.config.TranslationConfiguration
import net.guizhanss.slimefuntranslation.api.config.TranslationConfigurationDefaults
import net.guizhanss.slimefuntranslation.api.config.TranslationConfigurationFields
import net.guizhanss.slimefuntranslation.utils.FileUtils
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.io.File
import java.text.MessageFormat

class IntegrationService(private val plugin: InfinityExpansion2) {

    var slimefunTranslationEnabled = isPluginEnabled("SlimefunTranslation")
        private set
    var slimeHudEnabled = isPluginEnabled("SlimeHUD")
        private set

    init {
        if (slimeHudEnabled) {
            SlimeHUDIntegration
        }
    }

    private fun isPluginEnabled(pluginName: String) = plugin.server.pluginManager.isPluginEnabled(pluginName)

    /**
     * This function should be called by [TranslationsLoadListener] to load translations.
     */
    fun loadTranslations() {
        val fields = TranslationConfigurationFields.builder().items("items").lore("lores").build()
        val defaults = TranslationConfigurationDefaults.builder().name("InfinityExpansion2")
            .prefix(IEItems.prefix).build()
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
            val item = InfinityExpansion2.localization.getItemGroupItem(Material.BARRIER.asMaterialType(), id)
            SlimefunTranslationAPI.translateItem(SlimefunTranslationAPI.getUser(p), item)
            item.displayName!!
        } else {
            InfinityExpansion2.localization.getItemGroupName(id)
        }
    }

    fun getItemName(p: Player, id: String, vararg extraLore: String): String {
        return if (slimefunTranslationEnabled) {
            SlimefunTranslationAPI.getItemName(SlimefunTranslationAPI.getUser(p), id)
        } else {
            InfinityExpansion2.localization.getItemName(id.removePrefix(), *extraLore)
        }
    }

    fun getTranslatedItemName(p: Player, item: ItemStack): String {
        val sfId = SlimefunItem.getByItem(item)?.id ?: return ItemUtils.getItemName(item)
        return if (slimefunTranslationEnabled) {
            SlimefunTranslationAPI.getItemName(SlimefunTranslationAPI.getUser(p), sfId).takeIf { it.isNotEmpty() }
                ?: ItemUtils.getItemName(item)
        } else {
            ItemUtils.getItemName(item)
        }
    }

    fun getMessage(sender: CommandSender, key: String, vararg args: Any): String {
        return if (slimefunTranslationEnabled) {
            SlimefunTranslationAPI.getMessageFactory(InfinityExpansion2.instance).getMessage(sender, key, *args)
        } else {
            MessageFormat.format(InfinityExpansion2.localization.getString("messages.$key"), *args)
        }
    }

    fun sendMessage(sender: CommandSender, key: String, vararg args: Any) {
        if (slimefunTranslationEnabled) {
            SlimefunTranslationAPI.getMessageFactory(InfinityExpansion2.instance).sendMessage(sender, key, *args)
        } else {
            InfinityExpansion2.localization.sendMessage(sender, key, *args)
        }
    }
}
