plugins {
    id("com.android.library")
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinExt)
}

buildscript {
    apply(from = "autodimension.gradle.kts")
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
        consumerProguardFiles(
            file("proguard-rules.pro")
        )
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                file("proguard-rules.pro")
            )
        }
    }

}

tasks {
    clean {
        dependsOn("createDimen")
        mustRunAfter("createDimen")
    }
}

dependencies {
    implementation(Deps.kotlin_stdlib)
    implementation(Deps.support_app_compat)
    implementation(Deps.support_core_ktx)
    implementation(Deps.support_core_utils)
    implementation(Deps.support_design)
    implementation(Deps.support_recyclerview)

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.atsl_runner)
    androidTestImplementation(Deps.espresso_core)
}
