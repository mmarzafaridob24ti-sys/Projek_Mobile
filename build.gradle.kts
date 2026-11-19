// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false

    // BARU: KSP, pemroses anotasi yang membuat kode Room saat build
    id("com.google.devtools.ksp") version "2.3.11" apply false
}