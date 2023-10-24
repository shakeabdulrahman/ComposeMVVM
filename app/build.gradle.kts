plugins {
    id("com.android.application") // Plugin for building Android applications.
    id("org.jetbrains.kotlin.android") // Kotlin plugin for Android projects.
    id("com.google.devtools.ksp") // Kotlin Symbol Processing (KSP) plugin.
    id("dagger.hilt.android.plugin") // Dagger Hilt plugin for Android.
    id("androidx.navigation.safeargs.kotlin") // Safe Args plugin for Android Jetpack Navigation component.
    id("kotlin-kapt")
}

android {
    namespace = "com.example.composemvvm" // Namespace for the application.
    compileSdk = 34 // Specifies the version of the Android SDK to compile the project.

    defaultConfig {
        applicationId = "com.example.composemvvm" // The unique identifier for the application package.
        minSdk = 24 // Specifies the minimum API Level required for the application to run.
        targetSdk = 34 // Specifies the API level that the application targets.
        versionCode = 1 // Version code of the application.
        versionName = "1.0" // Version name of the application.

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Instrumentation Runner for running Android tests.
        vectorDrawables {
            useSupportLibrary = true // Enables the use of the Support Library for vector drawables.
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false // Specifies whether to run the ProGuard tool for minimizing the APK file.
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro" // ProGuard rules file for configuring code shrinking, obfuscation, etc.
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8 // Specifies the Java source compatibility.
        targetCompatibility = JavaVersion.VERSION_1_8 // Specifies the Java target compatibility.
    }
    kotlinOptions {
        jvmTarget = "1.8" // Sets the version of the generated Java bytecode.
    }
    buildFeatures {
        compose = true // Enables Jetpack Compose for the project.
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2" // Specifies the version of the Kotlin compiler extension for Jetpack Compose.
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}" // Excludes specific resources from the APK package.
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0") // Kotlin extensions for Android.
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2") // Kotlin extensions for lifecycle awareness.
    implementation("androidx.activity:activity-compose:1.8.0") // Activity library for Jetpack Compose.
    implementation(platform("androidx.compose:compose-bom:2023.10.01")) // Dependency management for Jetpack Compose.
    implementation("androidx.compose.ui:ui") // Jetpack Compose UI library.
    implementation("androidx.compose.ui:ui-graphics") // Jetpack Compose UI Graphics library.
    implementation("androidx.compose.ui:ui-tooling-preview") // Jetpack Compose UI tooling preview library.
    implementation("androidx.compose.material3:material3") // Jetpack Compose Material3 library.
    implementation("androidx.compose.ui:ui-tooling-preview") // Android Studio Preview support for Jetpack Compose.
    debugImplementation("androidx.compose.ui:ui-tooling") // Debugging tooling for Jetpack Compose.
    implementation("androidx.navigation:navigation-compose:2.7.4") // Jetpack Navigation with Compose.
    implementation("com.google.accompanist:accompanist-navigation-animation:0.33.2-alpha") // Animations for Compose Navigation.
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1") // System UI control
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2") // AndroidX ViewModel library with Kotlin extensions.
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2") // Kotlin extensions for LiveData with Lifecycle.
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2") // ViewModel utilities for Compose.
    implementation("com.google.dagger:hilt-android:2.48.1") // Dependency injection with Dagger Hilt for Android.
    kapt("com.google.dagger:hilt-android-compiler:2.48.1") // Annotation processor for Dagger Hilt.
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0") // Hilt extension for Navigation in Compose.
    kapt("com.google.dagger:dagger-android-processor:2.48.1") // Annotation processor for Dagger Hilt.
    implementation("androidx.room:room-runtime:2.6.0") // Android Jetpack's Room persistence library.
    ksp("androidx.room:room-compiler:2.6.0") // Annotation processor for Room.
    implementation("androidx.room:room-ktx:2.6.0") // Kotlin extensions for Room.
    implementation("androidx.datastore:datastore-preferences:1.0.0") // Datastore preference
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit is a type-safe HTTP client for Android and Java.
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2") // An OkHttp interceptor which logs HTTP request and response data.
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // A Retrofit 2 converter which uses Gson for serialization to and from JSON.
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3") // An HTTP client for sending and receiving network requests.
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0") // A Retrofit 2 converter for scalars (primitives and boxed types).
    implementation("io.coil-kt:coil-compose:2.4.0") // Loading image from url


    testImplementation("junit:junit:4.13.2") // JUnit testing framework.
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // Extensions to JUnit for Android testing.
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // UI testing framework for Android.
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01")) // Dependency management for Jetpack Compose in Android tests.
    androidTestImplementation("androidx.compose.ui:ui-test-junit4") // JUnit4 testing support for Jetpack Compose UI.
    debugImplementation("androidx.compose.ui:ui-tooling") // Debugging tooling for Jetpack Compose UI.
    debugImplementation("androidx.compose.ui:ui-test-manifest") // Manifest for testing Jetpack Compose UI.
}
