object Versions {
    const val kotlin = "1.3.41"
    const val android_gradle_plugin = "3.5.0"

    const val compile_sdk_version = 29
    const val build_tools_version = "29.0.2"
    const val min_sdk_version = 21
    const val target_sdk_version = 29

    const val apache_commons = "2.5"
    const val appcompat = "1.1.0-rc01"
    const val arch_core = "2.0.1"
    const val atsl_core = "1.1.0"
    const val atsl_rules = "1.1.1"
    const val atsl_runner = "1.1.1"
    const val atsl_junit = "1.1.0"
    const val benchmark = "1.0.0-alpha04"
    const val constraint_layout = "2.0.0-alpha2"
    const val core_ktx = "1.0.0"
    const val dagger = "2.16"
    const val dexmaker = "2.2.0"
    const val espresso = "3.1.1"
    const val fragment = "1.2.0-alpha02"
    const val glide = "4.8.0"
    const val hamcrest = "1.3"
    const val junit = "4.12"
    const val lifecycle = "2.2.0-alpha03"
    const val mockito = "2.25.0"
    const val mockito_all = "1.10.19"
    const val mockito_android = "2.25.0"
    const val mockwebserver = "3.8.1"
    const val navigation = "2.2.0-alpha01"
    const val okhttp_logging_interceptor = "3.9.0"
    const val paging = "2.1.0-rc01"
    const val retrofit = "2.4.0"
    const val retrofit_adapter = "1.0.0"
    const val robolectric = "4.2"
    const val room = "2.1.0-alpha06"
    const val rx_android = "2.1.0"
    const val rxjava2 = "2.2.3"
    const val support = "1.0.0"
    const val timber = "4.5.1"
    const val work = "2.1.0"
    const val javax_inject = "1@jar"
}

object ClassPaths {
    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Deps {
    const val benchmark = "androidx.benchmark:benchmark-junit4:${Versions.benchmark}"
    const val benchmark_gradle = "androidx.benchmark:benchmark-gradle-plugin:${Versions.benchmark}"

    const val support_annotations = "androidx.annotation:annotation:${Versions.support}"
    const val support_app_compat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val support_recyclerview = "androidx.recyclerview:recyclerview:${Versions.support}"
    const val support_cardview = "androidx.cardview:cardview:${Versions.support}"
    const val support_design = "com.google.android.material:material:${Versions.support}"
    const val support_core_utils = "androidx.legacy:legacy-support-core-utils:${Versions.support}"
    const val support_core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val support_fragment_runtime = "androidx.fragment:fragment:${Versions.fragment}"
    const val support_fragment_runtime_ktx = "androidx.fragment:fragment-ktx:`.fragment}"
    const val support_fragment_testing = "androidx.fragment:fragment-testing:${Versions.fragment}"

    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    const val room_rxjava2 = "androidx.room:room-rxjava2:${Versions.room}"
    const val room_testing = "androidx.room:room-testing:${Versions.room}"

    const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    const val lifecycle_java8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycle_extension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"

    const val arch_core_runtime = "androidx.arch.core:core-runtime:${Versions.arch_core}"
    const val arch_core_testing = "androidx.arch.core:core-testing:${Versions.arch_core}"

    const val retrofit_runtime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofit_mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    const val retrofit_adapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.retrofit_adapter}"
    const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}"

    const val dagger_runtime = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_android = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val dagger_android_support_compiler = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val espresso_intents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"

    const val atsl_core = "androidx.test:core:${Versions.atsl_core}"
    const val atsl_ext_junit = "androidx.test.ext:junit:${Versions.atsl_junit}"
    const val atsl_runner = "androidx.test:runner:${Versions.atsl_runner}"
    const val atsl_rules = "androidx.test:rules:${Versions.atsl_rules}"

    const val mockito_core = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockito_all = "org.mockito:mockito-all:${Versions.mockito_all}"
    const val mockito_android = "org.mockito:mockito-android:${Versions.mockito_android}"

    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlin_test = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlin_allopen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"

    const val paging_ktx = "androidx.paging:paging-runtime-ktx:${Versions.paging}"

    const val glide_runtime = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val dexmaker = "com.linkedin.dexmaker:dexmaker-mockito:${Versions.dexmaker}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val junit = "junit:junit:${Versions.junit}"
    const val mock_web_server = "com.squareup.okhttp3:mockwebserver:${Versions.mockwebserver}"
    const val rxjava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxjava2}"
    const val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android}"
    const val javax_inject = "javax.inject:javax.inject:${Versions.javax_inject}"

    const val hamcrest = "org.hamcrest:hamcrest-all:${Versions.hamcrest}"
    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    const val work_runtime = "androidx.work:work-runtime:${Versions.work}"
    const val work_testing = "androidx.work:work-testing:${Versions.work}"
    const val work_firebase = "androidx.work:work-firebase:${Versions.work}"
    const val work_runtime_ktx = "androidx.work:work-runtime-ktx:${Versions.work}"

    const val navigation_runtime = "androidx.navigation:navigation-runtime:${Versions.navigation}"
    const val navigation_runtime_ktx = "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
    const val navigation_fragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui = "androidx.navigation:navigation-ui:${Versions.navigation}"
    const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigation_safe_args_plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}
