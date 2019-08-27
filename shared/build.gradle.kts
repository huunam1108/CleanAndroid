plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Versions.compile_sdk_version)
    buildToolsVersion(Versions.build_tools_version)
    defaultConfig {
        minSdkVersion(Versions.min_sdk_version)
        targetSdkVersion(Versions.target_sdk_version)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Deps.kotlin_stdlib)
    implementation(Deps.support_app_compat)
    implementation(Deps.support_core_ktx)
    testImplementation(Deps.junit)
    androidTestImplementation(Deps.atsl_runner)
    androidTestImplementation(Deps.espresso_core)
}
