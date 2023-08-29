@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("themealdb.android.library")
    id("themealdb.android.hilt")
}

android {
    namespace = "com.pouyaa.core.network"
}

dependencies {
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.converter)
    implementation(libs.retrofit)
    implementation(libs.moshi.lazy.adapter)
    implementation(libs.mock.webserver)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.kotlin.test)
}