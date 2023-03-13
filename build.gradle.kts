

buildscript {
    mapOf(
        "activityComposeVersion" to "1.6.1",
        "composeVersion" to "1.4.0-alpha02",
        "voyagerVersion" to "1.0.0-rc03",
    ).forEach {
//        project.extra.set(it.key, it.value)
        extra.set(it.key, it.value)
    }
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Deps.kotlinGradlePlugin)
        classpath(Deps.androidBuildTools)
        classpath(Deps.sqlDelightGradlePlugin)
        classpath(Deps.hiltGradlePlugin)
        classpath("com.android.tools.build:gradle:7.3.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
