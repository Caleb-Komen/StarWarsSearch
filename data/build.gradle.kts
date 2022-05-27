plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

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
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))
    implementation(Dependencies.retrofit)
    implementation(Dependencies.gsonConverter)
    implementation(Dependencies.okHttpLogging)
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.hiltCore)
    implementation(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomKtx)

    testImplementation(TestDependencies.junit4)
    testImplementation(TestDependencies.mockWebServer)
    testImplementation(TestDependencies.truth)
    testImplementation(TestDependencies.coroutinesTest)

    androidTestImplementation(TestDependencies.junit4)
    androidTestImplementation(AndroidTestDependencies.testExtJunit)
    androidTestImplementation(TestDependencies.truth)
    androidTestImplementation(TestDependencies.coroutinesTest)
    androidTestImplementation(TestDependencies.archCoreTesting)
    androidTestImplementation(AndroidTestDependencies.testRunner)
}