plugins {
    alias(libs.plugins.android.application)

    // BARU: aktifkan KSP di modul app
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.edho_3tic"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.edho_3tic"
        minSdk = 29
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)

    // Pertemuan 9 dan 10
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation("com.tbuonomo:dotsindicator:5.1.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // BARU (Pertemuan 12): Room Database
    val roomVersion = "2.8.5"
    implementation("androidx.room:room-runtime:$roomVersion")   // inti Room
    implementation("androidx.room:room-ktx:$roomVersion")       // dukungan coroutine (suspend)
    ksp("androidx.room:room-compiler:$roomVersion")             // pembuat kode DAO dan Database

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}