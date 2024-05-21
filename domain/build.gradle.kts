plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.pwfb.domain"
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        minSdk = AppConfig.MIN_SDK
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
        sourceCompatibility = CompileOption.JAVA_VERSION
        targetCompatibility = CompileOption.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = CompileOption.JVM_TARGET
    }
}

dependencies {

    // KTX
    implementation(Libraries.KTX.CORE)

    // AndroidX ETC
    implementation(Libraries.AndroidX.APP_COMPAT)
    implementation(Libraries.AndroidX.MATERIAL)

    // Hilt
    implementation(Libraries.Hilt.HILT_ANDROID)
    kapt(Libraries.Hilt.HILT_ANDROID_COMPILER)

    // Test
    testImplementation(Libraries.Test.JUNIT)
    androidTestImplementation(Libraries.Test.TEST_EXT_JUNIT)
    androidTestImplementation(Libraries.Test.ESPRESSO_CORE)
}