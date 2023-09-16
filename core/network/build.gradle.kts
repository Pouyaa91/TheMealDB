@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("themealdb.android.library")
    id("themealdb.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.pouyaa.core.network"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    testImplementation(libs.mock.webserver)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.kotlin.test)
}