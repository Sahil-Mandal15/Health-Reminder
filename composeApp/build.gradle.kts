import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.ktlint)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm()

    js {
        browser()
        binaries.executable()
    }

    // Removed due to compatibility issues by Sahil-Mandal15
//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        browser()
//        binaries.executable()
//    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)

            // SQLDelight
            implementation("app.cash.sqldelight:android-driver:2.2.1")
        }
        commonMain {
            dependencies {
                implementation(libs.compose.runtime)
                implementation(libs.compose.foundation)
                implementation(libs.compose.material3)
                implementation(libs.compose.ui)
                implementation(libs.compose.components.resources)
                implementation(libs.compose.uiToolingPreview)
                implementation(libs.androidx.lifecycle.viewmodelCompose)
                implementation(libs.androidx.lifecycle.runtimeCompose)
                implementation(libs.sqldelight.runtime)

                implementation("app.cash.sqldelight:coroutines-extensions:2.2.1")
            }
            kotlin.srcDirs(
                "build/generated/sqldelight/code/HealthReminderDatabase/commonMain",
            )
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutinesSwing)

                implementation("app.cash.sqldelight:sqlite-driver:2.2.1")
            }
        }
        nativeMain.dependencies {
            implementation("app.cash.sqldelight:native-driver:2.2.1")
        }
        iosMain.dependencies {
            implementation("app.cash.sqldelight:native-driver:2.2.1")
        }
        webMain.dependencies {
            implementation("app.cash.sqldelight:web-worker-driver:2.2.1")
            implementation("app.cash.sqldelight:coroutines-extensions:2.2.1")
            implementation(npm("@cashapp/sqldelight-sqljs-worker", "2.2.1"))
            implementation(npm("sql.js", "1.8.0"))
        }
    }
}

android {
    namespace = "com.dev.healthreminder"
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()

    defaultConfig {
        applicationId = "com.dev.healthreminder"
        minSdk =
            libs.versions.android.minSdk
                .get()
                .toInt()
        targetSdk =
            libs.versions.android.targetSdk
                .get()
                .toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    debugImplementation(libs.compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.dev.healthreminder.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.dev.healthreminder"
            packageVersion = "1.0.0"
        }
    }
}

sqldelight {
    databases {
        create("HealthReminderDatabase") {
            packageName.set("com.dev.healthreminder.database")
            srcDirs.setFrom("src/commonMain/sqldelight")
            generateAsync.set(true)
        }
    }
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    coloredOutput.set(true)

    filter {
        exclude("**/build/generated/**")
        exclude("com.dev.healthreminder.database")
        exclude { element -> element.file.path.contains("resourceGenerator") }
        exclude { entry ->
            entry.file.toString().contains("generated")
        }
    }
}
