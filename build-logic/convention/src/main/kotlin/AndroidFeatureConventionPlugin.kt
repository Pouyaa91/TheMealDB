import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("themealdb.android.library")
                apply("themealdb.android.hilt")
                apply("kotlinx-serialization")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation", project(":core:model"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:common"))
                add("implementation", project(":core:ui"))
                add("implementation", libs.findLibrary("coil.kt").get())
                add("implementation", libs.findLibrary("coil.kt.compose").get())
                add("implementation", libs.findLibrary("hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("lifecycle.viewModelCompose").get())
                add("implementation", libs.findLibrary("material3").get())
                add("implementation", libs.findLibrary("material.icons.extended").get())
                add("implementation", libs.findLibrary("kotlin.serialization.json").get())
                add("implementation", libs.findLibrary("androidx.navigation.compose").get())

            }
        }
    }
}