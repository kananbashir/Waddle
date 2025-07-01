plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.firebase)
}

val waddleKeyAlias = project.properties["KEY_ALIAS"] as? String
val waddleKeyStorePass = project.properties["KEY_STORE_PASS"] as? String
val waddleKeyStoreFilePath = file("$rootDir/app/key.jks")

android {
    namespace = "com.waddleup.app"

    defaultConfig {
        applicationId = "com.waddleup.app"
        versionCode = 1
        versionName = "0.1.0"
    }

    signingConfigs {
        create("release") {
            storeFile = waddleKeyStoreFilePath
            storePassword = waddleKeyStorePass
            keyAlias = waddleKeyAlias
            keyPassword = waddleKeyStorePass
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }

        debug {
            resValue("string", "app_name", "Waddle debug")
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = ".debug"
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.theme)
    implementation(projects.core)
    implementation(projects.navigation)
    implementation(projects.feature.onboarding)
    implementation(projects.feature.auth)
    implementation(projects.feature.home)
    implementation(projects.feature.settings)
    implementation(projects.feature.addIncomeSource)

    implementation(libs.bundles.compose.ui)
    implementation(libs.bundles.core.ktx)
    implementation(libs.bundles.firebase)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.kotlinx.collections)
    implementation(libs.bundles.lifecycle.activity)
    implementation(libs.bundles.lifecycle.coroutines)
    implementation(libs.bundles.navigation.compose)
    implementation(libs.bundles.serialization)
    implementation(libs.bundles.splashscreen)
    implementation(libs.bundles.timber)

    testImplementation(libs.bundles.junit)

    androidTestImplementation(libs.bundles.android.test)
    androidTestImplementation(libs.bundles.compose.test)

    debugImplementation(libs.bundles.debug.ui.tooling)
}