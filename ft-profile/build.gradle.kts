
val pluginVersion = "1.0.0"
val pluginName = "ft-profile"
val pluginNamePackage = "ftprofile"
val pluginMain = "FTProfile"
val packageName = "io.lightstudios.farmingtycon.$pluginNamePackage.$pluginMain"

plugins {
    `java-library`
}

dependencies {
    api(project(":ft-common"))
    paperweight.paperDevBundle("1.21.5-R0.1-SNAPSHOT")
}

tasks {
    processResources {
        from(sourceSets.main.get().resources.srcDirs()) {
            filesMatching("plugin.yml") {
                expand(
                    "name" to pluginName,
                    "version" to pluginVersion,
                    "main" to packageName,
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