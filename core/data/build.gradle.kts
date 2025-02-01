plugins {
    alias(libs.plugins.study.android.library)
    alias(libs.plugins.study.jvm.ktor)
}

android {
    namespace = "hopeapps.dedev.core.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.database)
}