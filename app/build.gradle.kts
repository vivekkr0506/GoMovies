plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.vivek.gomovies"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.vivek.gomovies"
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "API_KEY", "\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZGI2NmU4YWEzZGVkZGQ4ZjVjY2E4YjlkOWRkYTYwMiIsIm5iZiI6MTcyNTA5MTEzMC44LCJzdWIiOiI2NmQyY2QzYWI2MzAyZDFmNTQ5NjViODkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.YmH1v9Do0JOHJvt7lSZs7FI8-Srb8rPZKC8_RCVsYtI\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "API_KEY", "\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZGI2NmU4YWEzZGVkZGQ4ZjVjY2E4YjlkOWRkYTYwMiIsIm5iZiI6MTcyNTA5MTEzMC44LCJzdWIiOiI2NmQyY2QzYWI2MzAyZDFmNTQ5NjViODkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.YmH1v9Do0JOHJvt7lSZs7FI8-Srb8rPZKC8_RCVsYtI\"")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.okhttp.logging.interceptor)

    // Paging3
    implementation (libs.androidx.paging.runtime)

    // Hilt
    implementation (libs.hilt.android)
    kapt (libs.dagger.hilt.compiler)

    // Coil
    implementation (libs.coil)

    // Lifecycle
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.lifecycle.livedata.ktx)
}