plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.parcelize)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.navigation.safeargs)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.pritom.dutta.the.theater"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pritom.dutta.the.theater"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
//            fireba {
//                appId = "1:12345678:android:12345678abc"
//                releaseNotes = "test"
//                groups = "android-qa"
//                serviceCredentialsFile = "android-app-distribution-key.json"
//            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.sdp)
    implementation(libs.ssp)

    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.navigation.ui.ktx)
    implementation(libs.lifecycle.livedataKtx)
    implementation(libs.lifecycle.viewmodel.ktx)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.gson.gson)

    //Glide
    implementation(libs.glide)
    ksp(libs.glide.ksp)

    //Own Module
    implementation(project(":network:data"))
    implementation(project(":network:domain"))
    implementation(project(":assets"))

    implementation(project(":features:movies"))
    implementation(project(":features:tv_show"))
    implementation(project(":features:settings"))
    implementation(project(":features:details"))

    //Testing Library
    testImplementation(libs.junit)
    testImplementation(libs.test.mockWebServer)
    testImplementation(libs.test.mockito)
    testImplementation(libs.test.assertj)
    testImplementation(libs.test.flow.turbine)
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation(libs.test.coroutines)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}