plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.waddleup.core"

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(projects.theme)

    implementation(libs.bundles.accompanist.system.ui)
    implementation(libs.bundles.appcompat.material)
    implementation(libs.bundles.compose.ui)
    implementation(libs.bundles.core.ktx)
    implementation(libs.bundles.datastore)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.lifecycle.coroutines)
    implementation(libs.bundles.lottie)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.serialization)
    implementation(libs.bundles.timber)

    testImplementation(libs.bundles.junit)

    androidTestImplementation(libs.bundles.android.test)
}