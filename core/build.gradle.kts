plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = rootProject.extra["compileSdkVersion"] as Int

    defaultConfig {
        minSdk = rootProject.extra["minSdkVersion"] as Int
        targetSdk = rootProject.extra["targetSdkVersion"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    //compose
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

apply(from = "../dependencies.gradle.kts")

val String.asDependency: String
    get() = project.extra[this] as String

dependencies {
    implementation("appCompat".asDependency)
    implementation("coreKtx".asDependency)
    implementation("material".asDependency)
    implementation("cicerone".asDependency)
    implementation("adapterDelegatesLayoutContainer".asDependency)
    implementation("adapterDelegatesViewBinding".asDependency)

    // Choose one of the following:
    // Material Design 3
    implementation("androidx.compose.material3:material3:1.0.0-rc01")
    // or Material Design 2
    implementation("androidx.compose.material:material:1.2.1")
    // or skip Material Design and build directly on top of foundational components
    implementation("androidx.compose.foundation:foundation:1.2.1")
    // or only import the main APIs for the underlying toolkit systems,
    // such as input and measurement/layout
    implementation("androidx.compose.ui:ui:1.2.1")

    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.1")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.1")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.1")

    // Optional - Included automatically by material, only add when you need
    // the icons but not the material library (e.g. when using Material3 or a
    // custom design system based on Foundation)
    implementation("androidx.compose.material:material-icons-core:1.2.1")
    // Optional - Add full set of material icons
    implementation("androidx.compose.material:material-icons-extended:1.2.1")
    // Optional - Add window size utils
    implementation("androidx.compose.material3:material3-window-size-class:1.0.0-rc01")

    // Optional - Integration with activities
    implementation("androidx.activity:activity-compose:1.6.0")
    // Optional - Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    // Optional - Integration with LiveData
    implementation("androidx.compose.runtime:runtime-livedata:1.2.1")
    // Optional - Integration with RxJava
    implementation("androidx.compose.runtime:runtime-rxjava2:1.2.1")
}