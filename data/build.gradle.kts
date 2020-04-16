plugins {
    id(Plugins.androidLib)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinExt)
    kotlin(Plugins.kotlinApt)
    id(Plugins.detekt).version(Versions.detekt)
}

buildscript {
    apply(from = "../ktlint.gradle.kts")
}

android {
    compileSdkVersion(Versions.compile_sdk_version)
    buildToolsVersion(Versions.build_tools_version)

    defaultConfig {
        minSdkVersion(Versions.min_sdk_version)
        targetSdkVersion(Versions.target_sdk_version)
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles(
            file("proguard-rules.pro")
        )
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                file("proguard-rules.pro")
            )
        }
    }

}

tasks {
    check {
        dependsOn("ktlintCheck")
        dependsOn("ktlintFormat")
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":shared"))
    implementation(Deps.kotlin_stdlib)
    implementation(Deps.support_app_compat)
    implementation(Deps.support_core_ktx)

    implementation(Deps.coroutines_core)
    implementation(Deps.coroutines_android)

    kapt(Deps.room_compiler)
    implementation(Deps.room_runtime)
    implementation(Deps.room_testing)
    implementation(Deps.room_ktx)

    implementation(Deps.retrofit_runtime)
    implementation(Deps.retrofit_gson)
    implementation(Deps.okhttp_logging_interceptor)

    implementation(Deps.koin_android)

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.atsl_runner)
    androidTestImplementation(Deps.espresso_core)
}
