plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization") // For kotlin serializations
}

android {
    namespace = "com.example.testtask_ticketssearch.data"
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
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":interface"))

    /**
     * DI: Dagger 2
     */
    val dagger_version = "2.51.1"
    implementation("com.google.dagger:dagger:$dagger_version")
    kapt("com.google.dagger:dagger-compiler:$dagger_version")
    implementation("javax.inject:javax.inject:1")

    /**
     * Network
     */
    // Provider
    val retrofit2_version = "2.11.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit2_version")
    // For retrofit2 serialization (delete after check work kotlin serialization)
    implementation("com.squareup.retrofit2:converter-gson:$retrofit2_version")
    // For kotlin serializations
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    // For logging
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    /**
     * Multithreading: Coroutines
     */
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")

    /**
     * Tests
     */
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}