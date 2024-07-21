plugins {
    kotlin("jvm") version "1.9.25"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

group = "net.guizhanss"
version = "UNOFFICIAL"
description = "InfinityExpansion2"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    maven("https://jitpack.io")
}

val paperVersion = "1.20.4-R0.1-SNAPSHOT"
val slimefunVersion = "RC-37"
val slimefunTranslationVersion = "e03b01a7b7"
val guizhanLibVersion = "1.7.6"
val bstatsVersion = "3.0.2"

dependencies {
    library(kotlin("stdlib"))
    library(kotlin("reflect"))
    compileOnly("io.papermc.paper:paper-api:$paperVersion")
    compileOnly("com.github.Slimefun:Slimefun4:$slimefunVersion")
    compileOnly("net.guizhanss:SlimefunTranslation:$slimefunTranslationVersion")
    implementation("net.guizhanss:GuizhanLib-api:$guizhanLibVersion")
    implementation("org.bstats:bstats-bukkit:$bstatsVersion")
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
        relocate(from, "net.guizhanss.infinityexpansion2.libs.$last")
    }
    doRelocate("net.guizhanss.guizhanlib")
    doRelocate("org.bstats")
    minimize()
    archiveClassifier = ""
}

bukkit {
    main = "net.guizhanss.infinityexpansion2.InfinityExpansion2"
    apiVersion = "1.17"
    authors = listOf("ybw0014")
    description = "More Slimefun content"
    depend = listOf("Slimefun")
    softDepend = listOf("GuizhanLibPlugin", "SlimefunTranslation", "InfinityExpansion")
}
