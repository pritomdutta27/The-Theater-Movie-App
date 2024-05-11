plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlinx.parcelize)
}

android {
    namespace = "com.pritom.dutta.movie.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.timber)

    //network lib
    implementation(libs.bundles.networking)
    implementation(libs.okhttp.interceptor){
        exclude(group = "org.json", module = "json")
    }

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    //Own Module Import
    implementation(project(":network:domain"))

    //Testing Library
    testImplementation(libs.junit)
    testImplementation(libs.test.mockWebServer)
    testImplementation(libs.test.mockito)
    testImplementation(libs.test.assertj)
    testImplementation(libs.test.flow.turbine)
    testImplementation(libs.test.coroutines)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}