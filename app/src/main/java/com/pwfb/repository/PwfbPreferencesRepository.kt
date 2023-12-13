package com.pwfb.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.pwfb.PwfbApplication.DataModule.dataStore
import com.pwfb.common.DataStoreResult.SET_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


class PwfbPreferencesRepository @Inject constructor(private val context: Context) {
    private val nameKey = stringPreferencesKey("name")
    private val firstInit = stringPreferencesKey("firstInit")
    private val dDay = stringPreferencesKey("dDay")
    private val trainingProgram = stringPreferencesKey("trainingProgram")
    private val carbohydrate = stringPreferencesKey("carbohydrate")
    private val protein = stringPreferencesKey("protein")
    private val fat = stringPreferencesKey("fat")
    private val water = stringPreferencesKey("water")
    private val sodium = stringPreferencesKey("sodium")
    private val potassium = stringPreferencesKey("potassium")
    private val creatine = stringPreferencesKey("creatine")
    private val dietaryFiber = stringPreferencesKey("dietaryFiber")

    suspend fun setName(name: String): String {
        context.dataStore.edit {
            it[nameKey] = name
        }
        return SET_NAME
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