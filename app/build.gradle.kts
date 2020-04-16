import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.androidApp)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinExt)
    kotlin(Plugins.kotlinApt)
    id(Plugins.fabric)
    id(Plugins.detekt).version(Versions.detekt)
}

buildscript {
    apply(from = "autodimension.gradle.kts")
    apply(from = "../ktlint.gradle.kts")
}

android {
    compileSdkVersion(Versions.compile_sdk_version)
    buildToolsVersion(Versions.build_tools_version)

    defaultConfig {
        applicationId = "namnh.clean.github"
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
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                file("proguard-rules.pro")
            )
        }
    }

    flavorDimensions("default")
    productFlavors {
        create("develop") {
            applicationIdSuffix = ".dev"
            manifestPlaceholders = mapOf("applicationName" to "@string/app_name")
        }

        create("staging") {
            applicationIdSuffix = ".stg"
            manifestPlaceholders = mapOf("applicationName" to "@string/app_name")
        }

        create("production") {
            manifestPlaceholders = mapOf("applicationName" to "@string/app_name")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    dataBinding {
        isEnabled = true
    }
}

androidExtensions {
    isExperimental = true
}

detekt {
    config = files("$rootDir/config/detekt/detekt.yml")
    input = files("src/main/java")
    // use default reports, see at app/build/reports/detekt
}

tasks {
    clean {
        dependsOn("createDimen")
        mustRunAfter("createDimen")
    }
    check {
        dependsOn("ktlintCheck")
        dependsOn("ktlintFormat")
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":shared"))
    implementation(Deps.kotlin_stdlib)
    implementation(Deps.support_app_compat)
    implementation(Deps.support_core_ktx)
    implementation(Deps.support_design)
    implementation(Deps.constraint_layout)
    implementation(Deps.support_recyclerview)

    implementation(Deps.navigation_fragment)
    implementation(Deps.navigation_ui)

    implementation(Deps.navigation_fragment_ktx)
    implementation(Deps.navigation_ui_ktx)

    implementation(Deps.koin_android)
    implementation(Deps.koin_viewmodel)
    implementation(Deps.koin_ext)

    kapt(Deps.glide_compiler)
    implementation(Deps.glide_runtime)

    implementation(Deps.room_runtime)
    kapt(Deps.room_compiler)

    implementation(Deps.retrofit_runtime)
    implementation(Deps.retrofit_gson)
    implementation(Deps.okhttp_logging_interceptor)

    implementation(Deps.firebase_analytics)
    implementation(Deps.dexter)

    implementation(Deps.swiperefreshlayout)
    implementation(Deps.viewpager2)

    testImplementation(Deps.junit)
    testImplementation(Deps.atsl_ext_junit)
    testImplementation(Deps.mockito_core)
    testImplementation(Deps.mockito_inline)
    testImplementation(Deps.mockito_kotlin)
    testImplementation(Deps.mock_web_server)
    testImplementation(Deps.power_mock_mockito2)
    testImplementation(Deps.power_mock_junit4)
    androidTestImplementation(Deps.atsl_runner)
    androidTestImplementation(Deps.espresso_core)

    implementation(Deps.crashlytics)
    implementation(Deps.firebase_messaging)
    implementation(Deps.play_services_location)
}
apply(plugin = Plugins.googleServices)
