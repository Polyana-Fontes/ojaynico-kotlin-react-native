plugins {
    alias(libs.plugins.kotlin.multiplatform)
    `maven-publish`
}

group = "com.github.ojaynico"
version = "1.2.1"

kotlin {
    compilerOptions {
        optIn.add("kotlin.js.ExperimentalWasmJsInterop")
    }

    js {
        nodejs()
        binaries.library()
        useCommonJs()
    }

    sourceSets {
        jsMain.dependencies {
            implementation(kotlinWrappers.js)
            implementation(kotlinWrappers.browser)
            implementation(kotlinWrappers.react)
            implementation(libs.kotlinx.coroutines.core)
            implementation(npm("react", libs.versions.reactVersion.get()))
            implementation(npm("react-native", libs.versions.reactNativeVersion.get()))
        }
    }
}

publishing {
    publications.withType<MavenPublication>().configureEach {
        pom {
            name.set(rootProject.name)
            description.set("Kotlin wrappers for React Native components and APIs")
            url.set("https://github.com/ojaynico/ojaynico-kotlin-react-native")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/mit-license.php")
                    distribution.set("repo")
                }
            }

            developers {
                developer {
                    id.set("ojaynico")
                    name.set("Nicodemus Ojwee")
                    email.set("ojaynico@gmail.com")
                }
            }

            scm {
                connection.set("scm:git:https://github.com/ojaynico/ojaynico-kotlin-react-native.git")
                developerConnection.set("scm:git:https://github.com/ojaynico/ojaynico-kotlin-react-native.git")
                url.set("https://github.com/ojaynico/ojaynico-kotlin-react-native")
            }
        }
    }
}
