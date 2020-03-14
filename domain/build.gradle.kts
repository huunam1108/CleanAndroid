plugins {
    id(Plugins.javalib)
    id(Plugins.kotlin)
    kotlin(Plugins.kotlinApt)
    id(Plugins.detekt).version(Versions.detekt)
}

buildscript {
    apply(from = "../ktlint.gradle.kts")
}

tasks {
    check {
        dependsOn("ktlintCheck")
        dependsOn("ktlintFormat")
    }
}

dependencies {
    implementation(Deps.kotlin_stdlib)
    implementation(Deps.rxjava2)
    testImplementation(Deps.junit)
}
