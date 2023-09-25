plugins {
    id("themealdb.android.library")
    id("themealdb.android.library.compose")
}

android {
    namespace = "com.pouyaa.core.ui"
    compileSdk = 33
}

dependencies {
    implementation(libs.lifecycle.runtimeCompose)
    implementation(libs.material3)
}