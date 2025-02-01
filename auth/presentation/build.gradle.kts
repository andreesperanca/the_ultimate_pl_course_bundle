plugins {
    alias(libs.plugins.study.android.feature.ui)
}

android {
    namespace = "hopeapps.dedev.auth.presentation"
}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
}