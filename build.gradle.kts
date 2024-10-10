import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hydraulic.conveyor)
}

group = "com.example"
version = "1.0.0"

kotlin {
    jvmToolchain(17)
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation(compose.material3)
    implementation(libs.sqlite.bundled)

    // Needed for Conveyor
    "linuxAmd64"(compose.desktop.linux_x64)
    "windowsAmd64"(compose.desktop.windows_x64)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ComposeBug"
            packageVersion = "1.0.0"
        }

        buildTypes {
            release {
                proguard {
                    configurationFiles.from("proguard-rules.pro")
                }
            }
        }
    }
}

tasks.wrapper {
    gradleVersion = "8.8"
}

// region Work around temporary Compose bugs.
configurations.all {
    attributes {
        // https://github.com/JetBrains/compose-jb/issues/1404#issuecomment-1146894731
        attribute(Attribute.of("ui", String::class.java), "awt")
    }
}
// endregion