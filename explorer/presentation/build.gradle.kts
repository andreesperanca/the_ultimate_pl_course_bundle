plugins {
    alias(libs.plugins.study.android.feature.ui)
}

android {
    namespace = "hopeapps.dedev.explorer.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.explorer.domain)
}