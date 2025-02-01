plugins {
    alias(libs.plugins.study.android.library)
    alias(libs.plugins.study.android.room)
}

android {
    namespace = "hopeapps.dedev.core.database"

}

dependencies {
    implementation(projects.core.domain)
}