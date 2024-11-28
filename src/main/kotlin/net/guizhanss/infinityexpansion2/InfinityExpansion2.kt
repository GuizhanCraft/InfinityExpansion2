package net.guizhanss.infinityexpansion2

import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.BlobBuildUpdater
import net.byteflux.libby.Library
import net.guizhanss.guizhanlib.libraries.BukkitLibraryManager
import net.guizhanss.guizhanlib.slimefun.addon.AbstractAddon
import net.guizhanss.guizhanlib.updater.GuizhanBuildsUpdater
import net.guizhanss.infinityexpansion2.core.commands.MainCommand
import net.guizhanss.infinityexpansion2.core.services.ConfigService
import net.guizhanss.infinityexpansion2.core.services.IntegrationService
import net.guizhanss.infinityexpansion2.core.services.LocalizationService
import net.guizhanss.infinityexpansion2.implementation.IEItems
import net.guizhanss.infinityexpansion2.implementation.groups.IEItemGroups
import net.guizhanss.infinityexpansion2.implementation.listeners.ArmorItemListener
import net.guizhanss.infinityexpansion2.implementation.listeners.BowItemListener
import net.guizhanss.infinityexpansion2.implementation.listeners.InfinityMatrixListener
import net.guizhanss.infinityexpansion2.implementation.listeners.SlimefunRegistryListener
import net.guizhanss.infinityexpansion2.implementation.listeners.TranslationsLoadListener
import net.guizhanss.infinityexpansion2.implementation.listeners.VeinMinerListener
import net.guizhanss.infinityexpansion2.implementation.setup.MobSimulationSetup
import net.guizhanss.infinityexpansion2.implementation.setup.ResearchSetup
import net.guizhanss.infinityexpansion2.implementation.tasks.InfinityMatrixTask
import net.guizhanss.infinityexpansion2.utils.tags.IETag
import org.bstats.bukkit.Metrics
import org.bstats.charts.SimplePie
import org.bukkit.plugin.Plugin
import java.io.File
import java.util.logging.Level

class InfinityExpansion2 : AbstractAddon(
    GITHUB_USER, GITHUB_REPO, GITHUB_BRANCH, AUTO_UPDATE_KEY
) {

    override fun load() {
        // check if there is central repo prop defined
        val centralRepo = System.getProperty("centralRepository") ?: "https://repo1.maven.org/maven2/"

        logger.info("Loading libraries, please wait...")
        logger.info("If you stuck here for a long time, try to specify a mirror repository.")
        logger.info("Add -DcentralRepository=<url> to the JVM arguments.")

        // download libs
        val manager = BukkitLibraryManager(this)
        manager.addRepository(centralRepo)
        manager.loadLibrary(
            Library.builder().groupId("org.jetbrains.kotlin").artifactId("kotlin-stdlib").version("2.0.21").build()
        )
        manager.loadLibrary(
            Library.builder().groupId("org.jetbrains.kotlin").artifactId("kotlin-reflect").version("2.0.21").build()
        )

        logger.info("Loaded all required libraries.")
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

        // tags
        IETag.reloadAll()

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
        MobSimulationSetup

        // researches setup
        if (configService.enableResearches) {
            ResearchSetup
        }

        // integrations
        integrationService = IntegrationService(this)

        // commands
        MainCommand(getPluginCommand("infinityexpansion2")).register()

        // listeners & tasks
        setupListeners()
        setupTasks()

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
                val clazz = Class.forName("net.guizhanss.minecraft.guizhanlib.updater.GuizhanUpdater")
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
                GuizhanBuildsUpdater.start(this, file, githubUser, githubRepo, githubBranch)
            }
        }
    }

    private fun setupListeners() {
        ArmorItemListener(this)
        BowItemListener(this)
        InfinityMatrixListener(this)
        SlimefunRegistryListener(this)
        TranslationsLoadListener(this)
        VeinMinerListener(this)
    }

    private fun setupTasks() {
        InfinityMatrixTask()
    }

    private fun setupMetrics() {
        val metrics = Metrics(this, 23025)

        metrics.addCustomChart(SimplePie("auto_update") { configService.autoUpdate.toString() })
    }

    companion object {

        private const val GITHUB_USER = "ybw0014"
        private const val GITHUB_REPO = "InfinityExpansion2"
        private const val GITHUB_BRANCH = "master"
        private const val AUTO_UPDATE_KEY = "auto-update"
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
}
