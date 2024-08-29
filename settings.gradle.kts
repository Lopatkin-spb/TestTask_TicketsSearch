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

include(":common:core")

// Feature
include(":feature:profile")
include(":feature:subscriptions")
include(":feature:ticketList")
include(":feature:shorter")
