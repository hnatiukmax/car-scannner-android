plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

fun com.android.build.api.dsl.BaseFlavor.buildConfigString(name: String, value: String) =
    buildConfigField("String", name, "\"$value\"")

android {
    signingConfigs {
        create("release") {
            storePassword = "car-scanner-android-pass"
            storeFile = file("keys/release.keystore")
            keyAlias = "car-scanner-release-key"
            keyPassword = "car-scanner-release-key-pass"
        }
    }
    compileSdk = rootProject.extra["compileSdkVersion"] as Int

    defaultConfig {
        applicationId = "dev.hnatiuk.carscanner"
        minSdk = rootProject.extra["minSdkVersion"] as Int
        targetSdk = rootProject.extra["targetSdkVersion"] as Int

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            resValue("string", "app_name", "@string/app_name_debug")
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("release") {
            resValue("string", "app_name", "@string/app_name_release")
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "productFlavor"
    productFlavors {
        create("dev") {
            dimension = "productFlavor"
            buildConfigString("DATABASE_PREFIX", "")
            buildConfigString("API_URL", "https://car-info-dev.herokuapp.com")
            buildConfigString("API_VERSION", "v1")
            buildConfigString("API_PREFFIX", "/")
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
        dataBinding {
            isEnabled = true
        }
    }
}

apply(from = "../dependencies.gradle.kts")

val String.asDependency: String
    get() = project.extra[this] as String

dependencies {
    implementation(project(":core"))

    implementation("appCompat".asDependency)
    implementation("coreKtx".asDependency)
    implementation("fragmentKtx".asDependency)
    implementation("material".asDependency)
    implementation("cicerone".asDependency)
    implementation("adapterDelegatesLayoutContainer".asDependency)
    implementation("adapterDelegatesViewBinding".asDependency)
    implementation("hiltAndroid".asDependency)
    kapt("hiltCompiler".asDependency)

    //libs for building
    implementation("retrofit".asDependency)
    implementation("retrofitMoshiConverter".asDependency)
    implementation("koinCore".asDependency)
    implementation("koinAndroid".asDependency)
    implementation("koinViewModel".asDependency)

    val anko_version = "0.10.8"
    implementation( "org.jetbrains.anko:anko:$anko_version")
    implementation("org.jetbrains.anko:anko-design:0.10.8")
    implementation("org.jetbrains.anko:anko-cardview-v7:0.10.8")

    val nav_version = "2.5.2"

    // Java language implementation
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    implementation("com.google.android.gms:play-services-vision:20.1.2")

    implementation("com.squareup.okhttp3:logging-interceptor:3.8.1")
    implementation("me.zhanghai.android.materialprogressbar:library:1.6.1")
}