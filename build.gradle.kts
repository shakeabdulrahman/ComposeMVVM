// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()  // Google's Maven repository for accessing Google's libraries.
        mavenCentral()  // Maven Central repository for accessing various libraries.
        maven {
            url = uri("https://plugins.gradle.org/m2/") // Custom Maven repository for accessing Gradle plugins.
        }
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.4") // Classpath dependency for the Navigation Safe Args Gradle plugin.
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48.1")
    }
}

plugins {
    id("com.android.application") version "8.1.2" apply false // Android application plugin version 8.1.2. Apply false means the plugin will not be automatically applied.
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false // Kotlin Android plugin
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false // Google's Kotlin Symbol Processing (KSP) plugin
    id("com.google.dagger.hilt.android") version "2.48.1" apply false // Dagger Hilt Android plugin
}
