UiCart - Android
=============================

UICart is a Android package designed to centralize and streamline the development of reusable UI components across multiple Android applications. By encapsulating common UI elements such as buttons, colors and fonts.

## Integration/Usage

To use the library in any porject:

* Add the below repository dependency to the main project build.gradle.kts
    
        val gitLabToken = project.findProperty("gitLabToken") as String?
            ?: throw GradleException("GitLab token not found. Please set 'gitLabToken' in gradle.properties or CI_JOB_TOKEN as an environment variable.")
        println("GitLab token detected: ${gitLabToken.isNotEmpty()}")
        maven {
            url = uri("https://gitlab.com/api/v4/projects/64752526/packages/maven")
            name = "GitLab"
            credentials(HttpHeaderCredentials::class) {
                name = if (project.hasProperty("gitLabToken")) "Private-Token" else "Job-Token"
                value = gitLabToken
            }
            authentication {
                create("header", HttpHeaderAuthentication::class)
            }
        }
* Add gitLabToken which the token for the profile in GitLab to the gradle.properties
* Usage Eg: implementation("com.heb.centralmarket.uicart:uicart-android:1.0.0-alpha05") in main project
