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
    private val nameKey = stringPreferencesKey("name") // 이름
    private val firstInitKey = booleanPreferencesKey("firstInit") // 앱 최초 진입 여부
    private val weightKey = stringPreferencesKey("weight") // 체중(공복 체중)
    private val dDayKey = stringPreferencesKey("dDay") // d-day
    private val trainingProgramKey = stringPreferencesKey("trainingProgram") // 트레이닝 기록
    private val carbohydrateKey = stringPreferencesKey("carbohydrate") // 탄수화물
    private val proteinKey = stringPreferencesKey("protein") // 단백질
    private val fatKey = stringPreferencesKey("fat") // 지방
    private val waterKey = stringPreferencesKey("water") // 수분량
    private val sodiumKey = stringPreferencesKey("sodium") // 나트륨
    private val potassiumKey = stringPreferencesKey("potassium") // 칼륨
    private val creatineKey = stringPreferencesKey("creatine") // 크레아틴
    private val dietaryFiberKey = stringPreferencesKey("dietaryFiber") // 식이 섬유


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
     * 몸무게 설정
     */
    suspend fun setWeight(weight: String): String {
        context.dataStore.edit {
            it[weightKey] = weight
        }
        return DataStoreResult.SET_WEIGHT
    }

    suspend fun getWeight(): Flow<String>  {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            // firstInitKey 값이 없을 경우 앱 최초 진입으로 판단
            it[weightKey] ?: "WeightNull"
        }
    }

    /**
     * D-Day 설정
     */
    suspend fun setDDay(dDay: String): String {
        context.dataStore.edit {
            it[dDayKey] = dDay
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