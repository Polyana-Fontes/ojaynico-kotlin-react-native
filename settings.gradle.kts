pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("kotlinWrappers") {
            from("org.jetbrains.kotlin-wrappers:kotlin-wrappers-catalog:2026.9.3")
        }
    }
}

rootProject.name = "ojaynico-kotlin-react-native"
