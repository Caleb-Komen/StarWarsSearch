plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.hiltCore)

    testImplementation(TestDependencies.junit4)
    testImplementation(TestDependencies.truth)
    testImplementation(TestDependencies.coroutinesTest)
}