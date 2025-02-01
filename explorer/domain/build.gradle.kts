plugins {
    alias(libs.plugins.study.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}