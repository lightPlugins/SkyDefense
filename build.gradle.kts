plugins {
    `java-library`
    id("com.gradleup.shadow") version "8.3.5" apply false
    id("maven-publish")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.16" apply false
    id("io.freefair.lombok") version "8.11" apply false
}

allprojects {
    group = "io.lightstudios.skydefense"
    version = "1.0.0"

    repositories {
        mavenCentral()
        maven("https://papermc.io/repo/repository/maven-public/")
        // SuperiorSkyblock2
        maven("https://repo.bg-software.com/repository/api/")
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "com.gradleup.shadow")
    apply(plugin = "maven-publish")
    apply(plugin = "io.freefair.lombok")

    if (name != "sd-common") {
        apply(plugin = "io.papermc.paperweight.userdev")
    }

    tasks {
        jar {
            archiveBaseName.set(project.name)
        }
    }
}