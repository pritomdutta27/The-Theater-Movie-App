// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlinx.parcelize) apply false
    alias(libs.plugins.navigation.safeargs) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}