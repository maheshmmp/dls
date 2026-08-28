import java.util.Properties
import kotlin.apply

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.compose.compiler)
}

repositories {
    maven {
        url = uri("https://gitlab.com/api/v4/projects/64752526/packages/maven")
        name = "GitLab"
        credentials(HttpHeaderCredentials::class) {
            name = "Private-Token"
            value =
                properties
                    .getOrDefault("hebGitLabReadToken", System.getenv("GITLAB_ACCESS_TOKEN"))
                    .toString()
        }
        authentication {
            create("header", HttpHeaderAuthentication::class)
        }
    }
}

val secretsProperties = Properties()
val secretsFile = rootProject.file("secrets.properties")
if (secretsFile.exists()) {
    secretsProperties.apply {
        load(secretsFile.inputStream())
    }
}

android {
    namespace = "com.heb.centralmarket.uicart"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.heb.centralmarket.uicart"
        minSdk = 29
        targetSdk = 36
        versionCode = 80
        versionName = "1.3.2.2"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Inject Google Maps key into Manifest
        manifestPlaceholders["googleMapId"] =
            secretsProperties.getProperty("GOOGLE_MAPS_API_KEY_CM")
                ?: System.getenv("GOOGLE_MAPS_API_KEY_CM")
                ?: ""
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.runtime.android)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.runtime.ktx)

    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

    implementation(project(":uicart-components"))
    // implementation(libs.uicart.android)
    implementation(libs.lottie.compose)
    implementation(libs.coil.compose)
}
