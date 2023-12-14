package com.pwfb.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
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
    private val firstInitKey = booleanPreferencesKey("firstInit")
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


    /**
     * 이름 설정
     */
    suspend fun setName(name: String): String {
        context.dataStore.edit {
            it[nameKey] = name
        }
        return DataStoreResult.SET_NAME
    }

    suspend fun getName(): Flow<String> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[nameKey] ?: "NameNull"
        }
    }

    /**
     * 최초 진입 여부 설정
     */
    suspend fun setFistInit(): Boolean {
        context.dataStore.edit {
            it[firstInitKey] = false
        }
        return DataStoreResult.SET_FIRST_INIT
    }

    suspend fun getFirstInit(): Flow<Boolean>  {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            // firstInitKey 값이 없을 경우 앱 최초 진입으로 판단
            it[firstInitKey] ?: true
        }
    }

    /**
     * D-Day 설정
     */
    suspend fun setDDay(): String {
        context.dataStore.edit {
            // todo : DateTime 사용
        }
        return DataStoreResult.SET_D_DAY
    }

    suspend fun getDDay(): Flow<Unit> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[dDayKey] ?: ""
        }
    }

    /**
     * 운동 계획 설정
     */
    suspend fun setTrainingProgram(name: String): String {
        context.dataStore.edit {
            it[trainingProgramKey] = name
        }
        return DataStoreResult.SET_NAME
    }

    suspend fun getTrainingProgram(): Flow<Unit> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[trainingProgramKey] ?: ""
        }
    }

    /**
     * 탄수화물 설정
     */
    suspend fun setCarbohydrate(name: String): String {
        context.dataStore.edit {
            it[nameKey] = name
        }
        return DataStoreResult.SET_NAME
    }

    suspend fun getCarbohydrate(): Flow<Unit> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[nameKey] ?: "NameNull"
        }
    }

    /**
     * 단백질 설정
     */
    suspend fun setProtein(name: String): String {
        context.dataStore.edit {
            it[nameKey] = name
        }
        return DataStoreResult.SET_NAME
    }

    suspend fun getProtein(): Flow<Unit> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[nameKey] ?: "NameNull"
        }
    }

    /**
     * 지방 설정
     */
    suspend fun setFat(name: String): String {
        context.dataStore.edit {
            it[nameKey] = name
        }
        return DataStoreResult.SET_NAME
    }

    suspend fun getFat(): Flow<Unit> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[nameKey] ?: "NameNull"
        }
    }

    /**
     * 수분 설정
     */
    suspend fun setWater(name: String): String {
        context.dataStore.edit {
            it[nameKey] = name
        }
        return DataStoreResult.SET_NAME
    }

    suspend fun getWater(): Flow<Unit> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[nameKey] ?: "NameNull"
        }
    }

    /**
     * 나트륨 설정
     */
    suspend fun setSodium(name: String): String {
        context.dataStore.edit {
            it[nameKey] = name
        }
        return DataStoreResult.SET_NAME
    }

    suspend fun getSodium(): Flow<Unit> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[nameKey] ?: "NameNull"
        }
    }

    /**
     * 칼륨 설정
     */
    suspend fun setPotassium(name: String): String {
        context.dataStore.edit {
            it[nameKey] = name
        }
        return DataStoreResult.SET_NAME
    }

    suspend fun getPotassium(): Flow<Unit> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[nameKey] ?: "NameNull"
        }
    }

    /**
     * 크레아틴 설정
     */
    suspend fun setCreatine(name: String): String {
        context.dataStore.edit {
            it[nameKey] = name
        }
        return DataStoreResult.SET_NAME
    }

    suspend fun getCreatine(): Flow<Unit> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[nameKey] ?: "NameNull"
        }
    }

}