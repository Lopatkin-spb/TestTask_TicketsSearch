plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.testtask_ticketssearch"
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
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":interface"))
    // Feature
    implementation(project(":feature:profile"))
    implementation(project(":feature:subscriptions"))
    implementation(project(":feature:ticketList"))

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