package com.pwfb.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.pwfb.common.DataStoreResult
import com.pwfb.PwfbApplication.DataModule.dataStore
import com.pwfb.data.PrefRepositoryImpl.PrefKey.carbohydrateKey
import com.pwfb.data.PrefRepositoryImpl.PrefKey.creatineKey
import com.pwfb.data.PrefRepositoryImpl.PrefKey.dDayKey
import com.pwfb.data.PrefRepositoryImpl.PrefKey.fatKey
import com.pwfb.data.PrefRepositoryImpl.PrefKey.firstInitKey
import com.pwfb.data.PrefRepositoryImpl.PrefKey.nameKey
import com.pwfb.data.PrefRepositoryImpl.PrefKey.potassiumKey
import com.pwfb.data.PrefRepositoryImpl.PrefKey.proteinKey
import com.pwfb.data.PrefRepositoryImpl.PrefKey.sodiumKey
import com.pwfb.data.PrefRepositoryImpl.PrefKey.trainingProgramKey
import com.pwfb.data.PrefRepositoryImpl.PrefKey.waterKey
import com.pwfb.data.PrefRepositoryImpl.PrefKey.weightKey
import com.pwfb.domain.PrefRepository
import com.pwfb.domain.PwfbResultEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class PrefRepositoryImpl@Inject constructor(private val context: Context) : PrefRepository {

    private object PrefKey {
        val nameKey = stringPreferencesKey("name") // 이름
        val firstInitKey = booleanPreferencesKey("firstInit") // 앱 최초 진입 여부
        val weightKey = stringPreferencesKey("weight") // 체중(공복 체중)
        val dDayKey = stringPreferencesKey("dDay") // d-day
        val trainingProgramKey = stringPreferencesKey("trainingProgram") // 트레이닝 기록
        val carbohydrateKey = stringPreferencesKey("carbohydrate") // 탄수화물
        val proteinKey = stringPreferencesKey("protein") // 단백질
        val fatKey = stringPreferencesKey("fat") // 지방
        val waterKey = stringPreferencesKey("water") // 수분량
        val sodiumKey = stringPreferencesKey("sodium") // 나트륨
        val potassiumKey = stringPreferencesKey("potassium") // 칼륨
        val creatineKey = stringPreferencesKey("creatine") // 크레아틴
        val dietaryFiberKey = stringPreferencesKey("dietaryFiber") // 식이 섬유
    }

    /**
     * 이름 설정
     */
    override suspend fun setName(name: String): PwfbResultEntity {
        return try {
            context.dataStore.edit {
                it[nameKey] = name
            }
            PwfbResultEntity.Success(DataStoreResult.RESULT_OK)
        } catch (error: Exception) {
            PwfbResultEntity.Failure(error)
        }
    }

    override suspend fun getName(): Flow<String> {
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
    override suspend fun setFistInit(): PwfbResultEntity {
        return try {
            context.dataStore.edit {
                it[firstInitKey] = false
            }
            PwfbResultEntity.Success(DataStoreResult.RESULT_OK)
        } catch (error: Exception) {
            PwfbResultEntity.Failure(error)
        }
    }

    override suspend fun getFirstInit(): Flow<Boolean>  {
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
    override suspend fun setWeight(weight: String): PwfbResultEntity {
        return try {
            context.dataStore.edit {
                it[weightKey] = weight
            }
            PwfbResultEntity.Success(DataStoreResult.RESULT_OK)
        } catch (error: Exception) {
            PwfbResultEntity.Failure(error)
        }
    }

    override suspend fun getWeight(): Flow<String>  {
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
    override suspend fun setDDay(dDay: String): PwfbResultEntity {
        return try {
            context.dataStore.edit {
                it[dDayKey] = dDay
            }
            PwfbResultEntity.Success(DataStoreResult.RESULT_OK)
        } catch (error: Exception) {
            PwfbResultEntity.Failure(error)
        }
    }

    override suspend fun getDDay(): Flow<String> {
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
    override suspend fun setTrainingProgram(trainingProgram: String): PwfbResultEntity {
        return try {
            context.dataStore.edit {
                it[trainingProgramKey] = trainingProgram
            }
            PwfbResultEntity.Success(DataStoreResult.RESULT_OK)
        } catch (error: Exception) {
            PwfbResultEntity.Failure(error)
        }
    }

    override suspend fun getTrainingProgram(): Flow<String> {
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
    override suspend fun setCarbohydrate(carbohydrate: String): PwfbResultEntity {
        return try {
            context.dataStore.edit {
                it[carbohydrateKey] = carbohydrate
            }
            PwfbResultEntity.Success(DataStoreResult.RESULT_OK)
        } catch (error: Exception) {
            PwfbResultEntity.Failure(error)
        }
    }

    override suspend fun getCarbohydrate(): Flow<String> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[carbohydrateKey] ?: ""
        }
    }

    /**
     * 단백질 설정
     */
    override suspend fun setProtein(protein: String): PwfbResultEntity {
        return try {
            context.dataStore.edit {
                it[proteinKey] = protein
            }
            PwfbResultEntity.Success(DataStoreResult.RESULT_OK)
        } catch (error: Exception) {
            PwfbResultEntity.Failure(error)
        }
    }

    override suspend fun getProtein(): Flow<String> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[proteinKey] ?: ""
        }
    }

    /**
     * 지방 설정
     */
    override suspend fun setFat(fat: String): PwfbResultEntity {
        return try {
            context.dataStore.edit {
                it[fatKey] = fat
            }
            PwfbResultEntity.Success(DataStoreResult.RESULT_OK)
        } catch (error: Exception) {
            PwfbResultEntity.Failure(error)
        }
    }

    override suspend fun getFat(): Flow<String> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[fatKey] ?: ""
        }
    }

    /**
     * 수분 설정
     */
    override suspend fun setWater(water: String): PwfbResultEntity {
        return try {
            context.dataStore.edit {
                it[waterKey] = water
            }
            PwfbResultEntity.Success(DataStoreResult.RESULT_OK)
        } catch (error: Exception) {
            PwfbResultEntity.Failure(error)
        }
    }

    override suspend fun getWater(): Flow<String> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[waterKey] ?: ""
        }
    }

    /**
     * 나트륨 설정
     */
    override suspend fun setSodium(sodium: String): PwfbResultEntity {
        return try {
            context.dataStore.edit {
                it[sodiumKey] = sodium
            }
            PwfbResultEntity.Success(DataStoreResult.RESULT_OK)
        } catch (error: Exception) {
            PwfbResultEntity.Failure(error)
        }
    }

    override suspend fun getSodium(): Flow<String> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[sodiumKey] ?: ""
        }
    }

    /**
     * 칼륨 설정
     */
    override suspend fun setPotassium(potassium: String): PwfbResultEntity {
        return try {
            context.dataStore.edit {
                it[potassiumKey] = potassium
            }
            PwfbResultEntity.Success(DataStoreResult.RESULT_OK)
        } catch (error: Exception) {
            PwfbResultEntity.Failure(error)
        }
    }

    override suspend fun getPotassium(): Flow<String> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[potassiumKey] ?: ""
        }
    }

    /**
     * 크레아틴 설정
     */
    override suspend fun setCreatine(creatine: String): PwfbResultEntity {
        return try {
            context.dataStore.edit {
                it[creatineKey] = creatine
            }
            PwfbResultEntity.Success(DataStoreResult.RESULT_OK)
        } catch (error: Exception) {
            PwfbResultEntity.Failure(error)
        }
    }

    override suspend fun getCreatine(): Flow<String> {
        return context.dataStore.data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[creatineKey] ?: ""
        }
    }
}