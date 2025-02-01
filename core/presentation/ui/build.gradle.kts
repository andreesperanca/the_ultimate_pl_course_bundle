plugins {
    alias(libs.plugins.study.android.library.compose)
}

android {
    namespace = "hopeapps.dedev.core.presentation.ui"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(projects.core.domain)
    implementation(projects.core.presentation.designsystem)
}