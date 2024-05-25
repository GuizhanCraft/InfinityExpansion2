package net.guizhanss.infinityexpansion2

import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.BlobBuildUpdater
import net.guizhanss.guizhanlib.slimefun.addon.AbstractAddon
import net.guizhanss.guizhanlib.updater.GuizhanBuildsUpdater
import net.guizhanss.infinityexpansion2.core.services.ConfigService
import net.guizhanss.infinityexpansion2.core.services.IntegrationService
import net.guizhanss.infinityexpansion2.core.services.ListenerService
import net.guizhanss.infinityexpansion2.core.services.LocalizationService
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.implementation.items.groups.IEItemGroups
import net.guizhanss.infinityexpansion2.implementation.setup.ResearchSetup
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File
import java.util.logging.Level

class InfinityExpansion2 : AbstractAddon {
    constructor() : super(
        "ybw0014", "InfinityExpansion2", "master", "auto-update"
    )

    constructor(loader: JavaPluginLoader, description: PluginDescriptionFile, dataFolder: File, file: File) : super(
        loader, description, dataFolder, file,
        "ybw0014", "InfinityExpansion2", "master", "auto-update"
    )

    companion object {
        const val DEFAULT_LANG = "en"

        lateinit var instance: InfinityExpansion2
            private set
        lateinit var configService: ConfigService
            private set
        lateinit var localization: LocalizationService
            private set
        lateinit var integrationService: IntegrationService
            private set

        fun scheduler() = getScheduler()

        fun sfTickCount() = getSlimefunTickCount()

        fun log(level: Level, message: String) {
            instance.logger.log(level, message)
        }

        fun log(level: Level, ex: Throwable, message: String) {
            instance.logger.log(level, ex) { message }
        }

        fun debug(message: String) {
            if (!Companion::configService.isInitialized || !configService.debug) {
                return
            }
            log(Level.INFO, "[DEBUG] $message")
        }
    }

    override fun enable() {
        instance = this

        // conflict check
        // TODO: enable this when releasing
//        if (server.pluginManager.isPluginEnabled("InfinityExpansion")) {
//            log(Level.SEVERE, "InfinityExpansion2 is not compatible with InfinityExpansion.")
//            log(Level.SEVERE, "Please remove InfinityExpansion before enabling InfinityExpansion2.")
//            server.pluginManager.disablePlugin(this)
//            return
//        }

        // config
        configService = ConfigService(this)

        // localization
        log(Level.INFO, "Loading language...")
        val lang = configService.lang
        localization = LocalizationService(this, file)
        localization.idPrefix = "IE_"
        localization.addLanguage(lang)
        if (lang != DEFAULT_LANG) {
            localization.addLanguage(DEFAULT_LANG)
        }
        log(Level.INFO, "Loaded language {0}.", lang)

        // item groups setup
        IEItemGroups

        // item setup
        IEItems

        // researches setup
        if (configService.enableResearches) {
            ResearchSetup
        }

        // integrations
        integrationService = IntegrationService(this)

        // listeners
        ListenerService(this)

        // Metrics setup
        setupMetrics()
    }

    override fun disable() {
        // no disable
    }

    override fun autoUpdate() {
        if (pluginVersion.startsWith("Dev")) {
            BlobBuildUpdater(this, file, githubRepo).start()
        } else if (pluginVersion.startsWith("Build")) {
            try {
                // use updater in lib plugin
                val clazz = Class.forName("net.guizhanss.guizhanlibplugin.updater.GuizhanUpdater")
                val updaterStart = clazz.getDeclaredMethod(
                    "start",
                    Plugin::class.java,
                    File::class.java,
                    String::class.java,
                    String::class.java,
                    String::class.java
                )
                updaterStart.invoke(null, this, file, githubUser, githubRepo, githubBranch)
            } catch (ignored: Exception) {
                // use updater in lib
                GuizhanBuildsUpdater(this, file, githubUser, githubRepo, githubBranch).start()
            }
        }
    }

    private fun setupMetrics() {
//        val metrics = Metrics(this, 14870)
    }
}
