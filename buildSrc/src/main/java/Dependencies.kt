object Versions {

    // KTX
    const val CORE = "1.12.0"
    const val KTX_ACTIVITY = "1.8.1"
    const val KTX_FRAGMENT = "1.6.2"
    const val KTX_VIEWMODEL = "2.6.2"

    // AndroidX ETC
    const val APP_COMPAT = "1.6.1"
    const val MATERIAL = "1.10.0"
    const val CONSTRAINT_LAYOUT = "2.1.4"

    // Splash
    const val SPLASH_SCREEN = "1.0.1"

    // Navigation
    const val NAVIGATION_FRAGMENT = "2.7.5"
    const val NAVIGATION_UI_KTX = "2.7.5"

    // Preferences DataStore
    const val DATASTORE_PREFERENCES = "1.0.0"
    const val DATASTORE_PREFERENCES_CORE = "1.0.0"

    // Hilt
    const val HILT = "2.46"

    // CalendarView
    const val CALENDAR_VIEW = "2.0.1"
    const val THREETENABP = "1.2.0"

    // AdMob
    const val ADMOB = "22.6.0"

    // Test
    const val JUNIT = "4.13.2"
    const val TEST_EXT_JUNIT = "1.1.5"
    const val TEST_ESPRESSO = "3.5.1"
}


object Libraries {

    object AndroidX {
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    }

    object KTX {
        const val CORE = "androidx.core:core-ktx:${Versions.CORE}"
        const val KTX_ACTIVITY = "androidx.activity:activity-ktx:${Versions.KTX_ACTIVITY}"
        const val KTX_FRAGMENT = "androidx.fragment:fragment-ktx:${Versions.KTX_FRAGMENT}"
        const val KTX_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.KTX_VIEWMODEL}"
    }

    object SplashScreen {
        const val SPLASH_SCREEN = "androidx.core:core-splashscreen:${Versions.SPLASH_SCREEN}"
    }

    object Navigation {
        const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION_FRAGMENT}"
        const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION_UI_KTX}"
    }

    object DatastorePreferences {
        const val DATASTORE_PREFERENCES = "androidx.datastore:datastore-preferences:${Versions.DATASTORE_PREFERENCES}"
        const val DATASTORE_PREFERENCES_CORE = "androidx.datastore:datastore-preferences-core:${Versions.DATASTORE_PREFERENCES_CORE}"
    }

    object Hilt {
        const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-compiler:${Versions.HILT}"
    }

    object CalendarView {
        const val CALENDAR_VIEW = "com.github.prolificinteractive:material-calendarview:${Versions.CALENDAR_VIEW}"
        const val THREETENABP = "com.jakewharton.threetenabp:threetenabp:${Versions.THREETENABP}" // CalendarView 년, 월 표시
    }

    object AdMob {
        const val ADMOB = "com.google.android.gms:play-services-ads:${Versions.ADMOB}"
    }

    object Test {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"
        const val TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.TEST_EXT_JUNIT}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.TEST_ESPRESSO}"
    }
}