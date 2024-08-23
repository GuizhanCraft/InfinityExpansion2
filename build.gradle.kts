plugins {
    kotlin("jvm") version "2.0.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

group = "net.guizhanss"
description = "InfinityExpansion2"

val mainPackage = "net.guizhanss.infinityexpansion2"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    maven("https://jitpack.io")
}

dependencies {
    library(kotlin("stdlib"))
    library(kotlin("reflect"))
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("com.github.Slimefun:Slimefun4:RC-37")
    compileOnly("net.guizhanss:SlimefunTranslation:e03b01a7b7")
    implementation("net.guizhanss:GuizhanLib-api:1.7.6")
    implementation("org.bstats:bstats-bukkit:3.0.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.compileKotlin {
    kotlinOptions {
        javaParameters = true
        jvmTarget = "17"
    }
}

tasks.shadowJar {
    fun doRelocate(from: String) {
        val last = from.split(".").last()
        relocate(from, "$mainPackage.libs.$last")
    }

    doRelocate("net.guizhanss.guizhanlib")
    doRelocate("org.bstats")
    minimize()
    archiveClassifier = ""
}

bukkit {
    main = "$mainPackage.InfinityExpansion2"
    apiVersion = "1.17"
    authors = listOf("ybw0014")
    description = "More Slimefun content"
    depend = listOf("Slimefun")
    softDepend = listOf("GuizhanLibPlugin", "SlimefunTranslation", "InfinityExpansion")

    commands {
        register("infinityexpansion") {
            description = "InfinityExpansion2 command"
            aliases = listOf("ie", "ie2", "infinityexpansion2")
        }
    }
}
