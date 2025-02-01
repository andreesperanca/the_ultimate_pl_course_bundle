plugins {
    alias(libs.plugins.study.android.library)
}

android {
    namespace = "hopeapps.dedev.explorer.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
}