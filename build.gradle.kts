buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")
    }
}

plugins {
    id("com.android.application") version "7.1.1" apply false
    id("com.android.library") version "7.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

apply(from = "dependencies.gradle.kts")

ext {
    extra["compileSdkVersion"] = 33
    extra["minSdkVersion"] = 26
    extra["targetSdkVersion"] = 33
}
