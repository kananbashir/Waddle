pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Waddle"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":theme")
include(":core")
include(":feature:onboarding")
include(":feature:auth")
include(":navigation")
include(":feature:home")
