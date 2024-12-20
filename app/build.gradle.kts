
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.projects.agrilembang"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.projects.agrilembang"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildToolsVersion = "34.0.0"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    //Splash Screen
    implementation(libs.androidx.core.splashscreen)

    //Navigation
    implementation(libs.androidx.navigation.compose)

    //Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.48.1")
    kapt ("com.google.dagger:hilt-compiler:2.48.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")


    implementation(libs.play.services.auth)

    //YCharts
    implementation(libs.ycharts)
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")

    implementation("androidx.webkit:webkit:1.12.1")

    //Data Store
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")


}