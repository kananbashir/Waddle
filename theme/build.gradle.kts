plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.waddleup.app.theme"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.bundles.accompanist.system.ui)
    implementation(libs.bundles.appcompat.material)
    implementation(libs.bundles.compose.ui)
    implementation(libs.bundles.core.ktx)

    testImplementation(libs.bundles.junit)

    androidTestImplementation(libs.bundles.android.test)
}
