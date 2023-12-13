package com.pwfb

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.pwfb.repository.PwfbPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@HiltAndroidApp
class PwfbApplication : Application() {

    @Module
    @InstallIn(SingletonComponent::class)
    object DataModule {
        @Singleton
        @Provides
        fun provideDataStoreRepository(@ApplicationContext context: Context)= PwfbPreferencesRepository(context)
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pref_pwfb")
    }
}