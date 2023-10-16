pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TheMealDB"
include(":app")
include(":core:network")
include(":core:data")
include(":core:model")
include(":core:domain")
include(":feature:categories")
include(":core:common")
include(":core:ui")
include(":feature:meals")
include(":feature:mealinfo")
