@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("themealdb.android.library")
    id("themealdb.android.hilt")
}

android {
    namespace = "com.pouyaa.core.data"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:model"))
    implementation(libs.core.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
}