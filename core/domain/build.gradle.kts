plugins {
    id("themealdb.android.library")
    id("themealdb.android.hilt")
}

android {
    namespace = "com.pouyaa.core.data"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(libs.core.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
}