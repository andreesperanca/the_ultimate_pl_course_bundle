plugins {
    alias(libs.plugins.study.android.library)
    alias(libs.plugins.study.jvm.ktor)
}

android {
    namespace = "hopeapps.dedev.auth.data"
}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}