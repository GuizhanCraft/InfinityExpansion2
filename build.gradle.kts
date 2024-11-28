import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.0.21"
    id("com.gradleup.shadow") version "8.3.5"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "net.guizhanss"
description = "InfinityExpansion2"

val mainPackage = "net.guizhanss.infinityexpansion2"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc"
    }
    maven("https://s01.oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven("https://repo.alessiodp.com/releases/") {
        name = "alessiodp"
    }
    maven("https://jitpack.io") {
        name = "jitpack"
    }
}

dependencies {
    compileOnly(kotlin("stdlib")) // loaded through library loader
    compileOnly(kotlin("reflect")) // loaded through library loader
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    compileOnly("com.github.Slimefun:Slimefun4:d12ae8580b")
    compileOnly("net.guizhanss:SlimefunTranslation:e03b01a7b7")
    compileOnly("com.github.schntgaispock:SlimeHUD:1.3.0")
    implementation("net.guizhanss:guizhanlib-all:2.2.0-SNAPSHOT")
    implementation("org.bstats:bstats-bukkit:3.1.0")
    implementation("it.unimi.dsi:fastutil:8.5.15")
    implementation("io.github.seggan:sf4k:0.8.0") {
        exclude(group = "org.jetbrains.kotlin")
        exclude(group = "com.github.Slimefun")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        javaParameters = true
        jvmTarget = JvmTarget.JVM_21
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
    doRelocate("it.unimi.dsi.fastutil")
    minimize()
    archiveClassifier = ""
}

bukkit {
    main = "$mainPackage.InfinityExpansion2"
    apiVersion = "1.18"
    authors = listOf("ybw0014")
    description = "More Slimefun content"
    depend = listOf("Slimefun")
    softDepend = listOf("GuizhanLibPlugin", "SlimefunTranslation", "InfinityExpansion", "SlimeHUD")
    loadBefore = listOf("SlimeCustomizer", "RykenSlimeCustomizer")

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
    }
}

tasks {
    runServer {
        downloadPlugins {
            // Slimefun
            url("https://blob.build/dl/Slimefun4/Dev/latest")
            // SlimeHUD
            url("https://blob.build/dl/SlimeHUD/Dev/latest")
            // GuizhanCraft for testing convenient
            url("https://builds.guizhanss.com/api/download/ybw0014/GuizhanCraft/master/latest")
        }
        jvmArgs("-Dcom.mojang.eula.agree=true")
        minecraftVersion("1.20.6")
    }
}
