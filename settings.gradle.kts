pluginManagement {
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
rootProject.name = "TestTask_TicketsSearch"
include(":app")
include(":domain")
include(":data")
include(":presentation")
include(":interface")

// Feature
include(":feature:profile")
include(":feature:subscriptions")
include(":feature:ticketList")
