package com.pwfb.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.pwfb.PwfbApplication.DataModule.dataStore
import com.pwfb.common.DataStoreResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


class PwfbPreferencesRepository @Inject constructor(private val context: Context) {
    private val nameKey = stringPreferencesKey("name")
    private val firstInitKey = stringPreferencesKey("firstInit")
    private val dDayKey = stringPreferencesKey("dDay")
    private val trainingProgramKey = stringPreferencesKey("trainingProgram")
    private val carbohydrateKey = stringPreferencesKey("carbohydrate")
    private val proteinKey = stringPreferencesKey("protein")
    private val fatKey = stringPreferencesKey("fat")
    private val waterKey = stringPreferencesKey("water")
    private val sodiumKey = stringPreferencesKey("sodium")
    private val potassiumKey = stringPreferencesKey("potassium")
    private val creatineKey = stringPreferencesKey("creatine")
    private val dietaryFiberKey = stringPreferencesKey("dietaryFiber")

    suspend fun setName(name: String): String {
        context.dataStore.edit {
            it[nameKey] = name
        }
        return DataStoreResult.SET_NAME
    }

    suspend fun getName(): Flow<Unit> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[nameKey] ?: "getNameNull"
        }
    }
}