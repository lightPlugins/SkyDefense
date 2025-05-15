
val pluginVersion = "1.0.0"
val pluginName = "SkyDefense-Hunt"

plugins {
    `java-library`
}

dependencies {
    api(project(":sd-common"))
    api(project(":sd-profile"))
    paperweight.paperDevBundle("1.21.5-R0.1-SNAPSHOT")
    compileOnly("com.bgsoftware:SuperiorSkyblockAPI:2025.1")
}

tasks {
    processResources {
        from(sourceSets.main.get().resources.srcDirs()) {
            filesMatching("plugin.yml") {
                expand(
                    "name" to pluginName,
                    "version" to pluginVersion,
                )

            }
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
        }
    }

    compileJava {
        options.encoding = "UTF-8"
    }

    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveClassifier.set("")
        archiveBaseName.set(pluginName)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifact(tasks.shadowJar.get()) {
                classifier = null
            }
            groupId = "com.github.lightPlugins"
            artifactId = pluginName
            version = rootProject.version.toString()
        }
    }
}

tasks.named("publishMavenPublicationToMavenLocal") {
    dependsOn(tasks.shadowJar)
    dependsOn(tasks.jar)
}

tasks.jar {
    enabled = false
}