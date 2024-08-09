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
        maven(url = "https://jitpack.io")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

rootProject.name = "The Theater Android App"
include(":app")
include(":network:data")
include(":network:domain")
//include(":core:baseData")
//include(":core:baseDomain")
//include(":core:baseUI")
include(":assets")
include(":features:movies")
include(":features:tv_show")
include(":features:details")
include(":features:settings")
