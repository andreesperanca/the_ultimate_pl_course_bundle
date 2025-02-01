// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    //Compiler
    alias(libs.plugins.compose.compiler) apply false
    //Ksp
    alias(libs.plugins.ksp) apply false
    //Room
    alias(libs.plugins.room) apply false
    //Serialization
    alias(libs.plugins.kotlin.serialization) apply false
}