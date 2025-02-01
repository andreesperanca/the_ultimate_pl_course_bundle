plugins {
    alias(libs.plugins.study.android.library)
}

android {
    namespace = "hopeapps.dedev.core.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.database)
}