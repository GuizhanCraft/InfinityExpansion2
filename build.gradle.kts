import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

plugins {
    kotlin("jvm") version "2.1.21"
    id("com.gradleup.shadow") version "8.3.9"
    id("de.eldoria.plugin-yml.bukkit") version "0.7.1"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "net.guizhanss"
description = "InfinityExpansion2"

val timestamp: String = DateTimeFormatter.ofPattern("yyMMddHHmm").withZone(ZoneOffset.UTC).format(Instant.now())
val mainPackage = "net.guizhanss.infinityexpansion2"

version = "preview-$timestamp"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://central.sonatype.com/repository/maven-snapshots/")
    maven("https://repo.alessiodp.com/releases/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly(kotlin("stdlib")) // loaded through library loader
    compileOnly(kotlin("reflect")) // loaded through library loader
    compileOnly("io.papermc.paper:paper-api:1.21.3-R0.1-SNAPSHOT")
    compileOnly("com.github.slimefun:Slimefun4:experimental-SNAPSHOT")
    compileOnly("net.guizhanss:SlimefunTranslation:e03b01a7b7")
    compileOnly("com.github.schntgaispock:SlimeHUD:1.3.0")
    implementation("net.guizhanss:guizhanlib-all:2.3.0")
    implementation("net.guizhanss:guizhanlib-kt-all:0.2.0")
    implementation("org.bstats:bstats-bukkit:3.1.0")
    implementation("com.jeff-media:MorePersistentDataTypes:2.4.0")
}

java {
    disableAutoTargetJvm()
    sourceCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        javaParameters = true
        jvmTarget = JvmTarget.JVM_17
    }
}

tasks.shadowJar {
    fun doRelocate(from: String, to: String? = null) {
        val last = to ?: from.split(".").last()
        relocate(from, "$mainPackage.libs.$last")
    }

    doRelocate("net.byteflux.libby")
    doRelocate("net.guizhanss.guizhanlib")
    doRelocate("org.bstats")
    doRelocate("io.github.seggan.sf4k")
    doRelocate("io.papermc.lib", "paperlib")
    doRelocate("com.jeff_media.morepersistentdatatypes")
    minimize()
    archiveClassifier = ""
}

bukkit {
    main = "$mainPackage.InfinityExpansion2"
    apiVersion = "1.20"
    authors = listOf("ybw0014")
    description = "More Slimefun content"
    depend = listOf("Slimefun")
    softDepend = listOf("GuizhanLibPlugin", "SlimefunTranslation", "InfinityExpansion", "SlimeHUD")
    loadBefore = listOf("SlimeCustomizer", "RykenSlimeCustomizer", "SlimeFunRecipe")

    commands {
        register("infinityexpansion2") {
            description = "InfinityExpansion2 command"
            aliases = listOf("ie", "ie2", "infinityexpansion")
        }
    }

    permissions {
        register("infinityexpansion2.command.giverecipe") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("infinityexpansion2.command.guide") {
            default = BukkitPluginDescription.Permission.Default.TRUE
        }
        register("infinityexpansion2.command.printitem") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("infinityexpansion2.command.id") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
    }
}

tasks {
    runServer {
        downloadPlugins {
            // Slimefun
            url("https://blob.build/dl/Slimefun4/Dev/latest")
            // SlimeHUD
            url("https://blob.build/dl/SlimeHUD/Dev/latest")
            // JustEnoughGuide
//            url("https://blob.build/dl/JustEnoughGuide/Dev/latest")
            // GuizhanCraft for testing convenient
            url("https://builds.guizhanss.com/api/download/ybw0014/GuizhanCraft/master/latest")
        }
        jvmArgs("-Dcom.mojang.eula.agree=true")
        minecraftVersion("1.20.6")
    }
}
