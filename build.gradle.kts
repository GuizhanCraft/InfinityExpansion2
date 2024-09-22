import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.0.20"
    id("com.gradleup.shadow") version "8.3.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

group = "net.guizhanss"
description = "InfinityExpansion2"

val mainPackage = "net.guizhanss.infinityexpansion2"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    maven("https://repo.alessiodp.com/releases/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly(kotlin("reflect"))
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    compileOnly("com.github.Slimefun:Slimefun4:e02a0f61d1")
    compileOnly("net.guizhanss:SlimefunTranslation:e03b01a7b7")
    implementation("net.byteflux:libby-bukkit:1.3.1")
    implementation("net.guizhanss:GuizhanLib-api:1.8.1")
    implementation("org.bstats:bstats-bukkit:3.1.0")
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
    fun doRelocate(from: String) {
        val last = from.split(".").last()
        relocate(from, "$mainPackage.libs.$last")
    }

    doRelocate("net.byteflux.libby")
    doRelocate("net.guizhanss.guizhanlib")
    doRelocate("org.bstats")
    minimize()
    archiveClassifier = ""
}

bukkit {
    main = "$mainPackage.InfinityExpansion2"
    apiVersion = "1.18"
    authors = listOf("ybw0014")
    description = "More Slimefun content"
    depend = listOf("Slimefun")
    softDepend = listOf("GuizhanLibPlugin", "SlimefunTranslation", "InfinityExpansion")

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
