package com.pwfb.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.pwfb.domain.repository.PrefRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PrefDataSource {
    @Provides
    fun providePrefRepositoryImpl(@ApplicationContext context: Context): PrefRepository = PrefRepositoryImpl(context.dataStore)
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pref_pwfb")
}