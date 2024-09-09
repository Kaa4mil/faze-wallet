plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "dev.kaa4mil"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()

    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    maven {
        url = uri("https://storehouse.okaeri.eu/repository/maven-public/")
    }
}

dependencies {
    // platform
    implementation("dev.kaa4mil:platform-bukkit:2.1")

    // spigot api
    compileOnly("org.spigotmc:spigot-api:1.20.2-R0.1-SNAPSHOT")

    // menu library
    implementation("dev.triumphteam:triumph-gui:3.1.10")

    // okaeri libraries
    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.5")
    implementation("eu.okaeri:okaeri-configs-serdes-bukkit:5.0.5")
    implementation("eu.okaeri:okaeri-configs-json-gson:5.0.5")
    implementation("eu.okaeri:okaeri-configs-serdes-commons:5.0.5")

    implementation("eu.okaeri:okaeri-injector:2.1.0")

    implementation("eu.okaeri:okaeri-persistence-jdbc:3.0.1-beta.2")
    implementation("eu.okaeri:okaeri-persistence-flat:3.0.1-beta.2")

    // lombok
    val lombok = "1.18.34"
    compileOnly("org.projectlombok:lombok:$lombok")
    annotationProcessor("org.projectlombok:lombok:$lombok")

    // jetbrains
    implementation("org.jetbrains:annotations:24.0.0")
}

tasks.shadowJar {
    archiveFileName.set("Faze-Wallet-1.0-Synchronized.jar")
    destinationDirectory.set(file("D:\\Program Files\\bukkit server\\plugins"))

    listOf(
        "eu.okaeri",
        "org.jetbrains",
        "net.kyori"
    ).forEach{ name ->
        relocate(name, "dev.kaa4mil.libs.$name")
    }

    minimize()
}