plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization") // For kotlin serializations
}

android {
    namespace = "com.example.ticketlist"
    compileSdk = 34

    defaultConfig {
        minSdk = 33
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        dataBinding = false
        buildConfig = true
    }
}

dependencies {
    implementation(project(":common:core"))

    /**
     * Core
     */
    implementation(libs.ktx.core)
    implementation(libs.appcompat)
    implementation(libs.android.material)
    implementation(libs.constraintlayout)

    /**
     * Android Jetpack: ViewModel & Lifecycle
     */
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    kapt(libs.lifecycle.compiler)

    /**
     * DI: Dagger 2
     */
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.javax.inject)

    /**
     * Multithreading: Coroutines
     */
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    /**
     * Navigation
     */
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    /**
     * Network
     */
    // Provider
    implementation(libs.retrofit)
    // For kotlin serializations
    implementation(libs.kotlinx.serialization.json)
    // Converter between provider and kotlin serialization
    implementation(libs.retrofit.converter.kotlinx.serialization)
    // For logging
    implementation(platform(libs.okhttp3.bom))
    implementation(libs.okhttp3.okhttp)
    implementation(libs.okhttp3.logging.interceptor)

    /**
     * Images
     */
    implementation(libs.glide)

    /**
     * Tests
     */
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}