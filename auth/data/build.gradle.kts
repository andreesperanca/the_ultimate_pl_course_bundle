plugins {
    alias(libs.plugins.study.android.library)
}

android {
    namespace = "hopeapps.dedev.auth.data"
}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}