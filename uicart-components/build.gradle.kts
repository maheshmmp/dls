/*
 *
 *  Created by Mahesh Paul on 3/9/26, 11:17 AM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 3/9/26, 10:45 AM
 *
 */

plugins {
    alias(libs.plugins.androidLibrary)
    `maven-publish`
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.heb.centralmarket.uicart.components"
    compileSdk = 36

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
        }
    }
    buildFeatures {
        compose = true
    }
    testOptions.unitTests.isIncludeAndroidResources = true

    // Configure the Android publication variant so the 'release' publication is properly wired.
    publishing {
        singleVariant("release") {
            withSourcesJar()
            // withJavadocJar() // optional if you generate javadocs/dokka
        }
    }
}

// Set consistent coordinates
group = "com.heb.centralmarket.uicart"
version = "1.4.1.3"

// Single, clean publication configuration
publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = group.toString()
            artifactId = "uicart-android"
            version = project.version.toString()
            afterEvaluate {
                from(components["release"])
            }
        }
    }
    repositories {
        maven("https://gitlab.com/api/v4/projects/64752526/packages/maven") {
            credentials(HttpHeaderCredentials::class) {
                name = "Private-Token"
                value = properties.getOrDefault("hebGitLabReadToken", System.getenv("GITLAB_ACCESS_TOKEN")).toString()
            }
            authentication {
                create<HttpHeaderAuthentication>("header")
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.runtime.android)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.foundation)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    androidTestImplementation(libs.androidx.espresso.core)
    // Google Truth (for assertions)
    testImplementation(libs.truth)
    // Lottie Animation
    implementation(libs.lottie.compose)
    // Coil Compose
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // GoogleMap SDK
    implementation(libs.play.services.maps)
}
