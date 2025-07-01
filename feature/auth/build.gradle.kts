plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.waddleup.auth"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.theme)
    implementation(projects.core)
    implementation(projects.navigation)

    implementation(libs.bundles.compose.ui)
    implementation(libs.bundles.core.ktx)
    implementation(libs.bundles.datastore)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.lifecycle.activity)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.timber)

    testImplementation(libs.bundles.junit)

    androidTestImplementation(libs.bundles.android.test)
    androidTestImplementation(libs.bundles.compose.test)

    debugImplementation(libs.bundles.debug.ui.tooling)
}