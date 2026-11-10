plugins {
    alias(libs.plugins.android.application)
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

    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    // GridLayout: menyusun tombol dalam petak (dipakai di tab Panduan)
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    // ViewPager2: halaman yang bisa digeser kiri-kanan (dipakai di Tutorial)
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    // DotsIndicator: titik penanda halaman (dipakai di Tutorial)
    implementation("com.tbuonomo:dotsindicator:5.1.0")
    implementation(libs.androidx.activity)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
