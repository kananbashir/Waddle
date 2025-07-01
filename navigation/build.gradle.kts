plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.waddleup.navigation"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.theme)
    implementation(projects.core)

    implementation(libs.bundles.compose.ui)
    implementation(libs.bundles.core.ktx)
    implementation(libs.bundles.lifecycle.activity)
    implementation(libs.bundles.lifecycle.coroutines)
    implementation(libs.bundles.navigation.compose)
    implementation(libs.bundles.serialization)
    implementation(libs.bundles.timber)

    testImplementation(libs.bundles.junit)

    androidTestImplementation(libs.bundles.android.test)
    androidTestImplementation(libs.bundles.compose.test)

    debugImplementation(libs.bundles.debug.ui.tooling)
}