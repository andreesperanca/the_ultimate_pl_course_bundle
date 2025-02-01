plugins {
    alias(libs.plugins.study.android.library)
    alias(libs.plugins.study.jvm.ktor)
}

android {
    namespace = "hopeapps.dedev.explorer.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
}