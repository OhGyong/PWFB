import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.pwfb"
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        applicationId = "com.pwfb"
        minSdk = AppConfig.MIN_SDK
        targetSdk = AppConfig.TARGET_SDK
        versionCode = AppConfig.VERSION_CODE
        versionName = AppConfig.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        fun key(pKey:String): String = gradleLocalProperties(rootDir).getProperty(pKey) ?: ""
        manifestPlaceholders["AdMobAPI_KEY"] = key("AdMobAPI_KEY")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    // Module
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))

    // KTX
    implementation(Libraries.KTX.CORE)
    implementation(Libraries.KTX.KTX_ACTIVITY)
    implementation(Libraries.KTX.KTX_FRAGMENT)
    implementation(Libraries.KTX.KTX_VIEWMODEL)

    // AndroidX ETC
    implementation(Libraries.AndroidX.APP_COMPAT)
    implementation(Libraries.AndroidX.MATERIAL)
    implementation(Libraries.AndroidX.CONSTRAINT_LAYOUT)

    // Splash Screen
    implementation(Libraries.SplashScreen.SPLASH_SCREEN)

    // Navigation
    implementation(Libraries.Navigation.NAVIGATION_FRAGMENT)
    implementation(Libraries.Navigation.NAVIGATION_UI_KTX)

    // Preferences DataStore
    implementation(Libraries.DatastorePreferences.DATASTORE_PREFERENCES)
    implementation(Libraries.DatastorePreferences.DATASTORE_PREFERENCES_CORE)

    // Hilt
    implementation(Libraries.Hilt.HILT_ANDROID)
    kapt(Libraries.Hilt.HILT_ANDROID_COMPILER)

    // CalendarView
    implementation (Libraries.CalendarView.CALENDAR_VIEW)
    implementation (Libraries.CalendarView.THREETENABP)

    // 애드몹
    implementation (Libraries.AdMob.ADMOB)

    // Test
    testImplementation(Libraries.Test.JUNIT)
    androidTestImplementation(Libraries.Test.TEST_EXT_JUNIT)
    androidTestImplementation(Libraries.Test.ESPRESSO_CORE)
}