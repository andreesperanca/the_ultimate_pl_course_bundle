plugins {
    alias(libs.plugins.study.android.library)
}

android {
    namespace = "hopeapps.dedev.explorer.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.database)
    implementation(projects.explorer.domain)
}