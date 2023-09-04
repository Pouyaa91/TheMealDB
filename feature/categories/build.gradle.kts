plugins {
    id("themealdb.android.feature")
    id("themealdb.android.library.compose")
}

android {
    namespace = "com.pouyaa.feature.foryou"
}

dependencies {
    implementation(libs.activity.compose)
}