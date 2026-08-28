pluginManagement {
    val hebGitLabReadToken: String? by settings
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    val hebGitLabReadToken: String? by settings
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
}

rootProject.name = "UICart"
include(":app")
include(":uicart-components")
