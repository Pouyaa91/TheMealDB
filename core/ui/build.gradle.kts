plugins {
    id("themealdb.android.library")
    id("themealdb.android.library.compose")
}

android {
    namespace = "com.pouyaa.core.ui"
}

dependencies {
    implementation(libs.lifecycle.runtimeCompose)
    implementation(libs.material3)
}