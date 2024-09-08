package com.pwfb.domain.repository

import com.pwfb.domain.entity.PwfbResultEntity
import kotlinx.coroutines.flow.Flow

interface PrefRepository {

    /**
     * 이름 설정
     */
    suspend fun setName(name: String): PwfbResultEntity
    suspend fun getName(): Flow<String>

    /**
     * 최초 진입 여부 설정
     */
    suspend fun setFistInit(): PwfbResultEntity
    suspend fun getFirstInit(): Flow<Boolean>

    /**
     * 몸무게 설정
     */
    suspend fun setWeight(weight: String): PwfbResultEntity
    suspend fun getWeight(): Flow<String>

    /**
     * D-Day 설정
     */
    suspend fun setDDay(dDay: String): PwfbResultEntity
    suspend fun getDDay(): Flow<String>

    /**
     * 운동 계획 설정
     */
    suspend fun setTrainingProgram(trainingProgram: String): PwfbResultEntity
    suspend fun getTrainingProgram(): Flow<String>

    /**
     * 탄수화물 설정
     */
    suspend fun setCarbohydrate(carbohydrate: String): PwfbResultEntity
    suspend fun getCarbohydrate(): Flow<String>

    /**
     * 단백질 설정
     */
    suspend fun setProtein(protein: String): PwfbResultEntity
    suspend fun getProtein(): Flow<String>

    /**
     * 지방 설정
     */
    suspend fun setFat(fat: String): PwfbResultEntity
    suspend fun getFat(): Flow<String>

    /**
     * 수분 설정
     */
    suspend fun setWater(water: String): PwfbResultEntity
    suspend fun getWater(): Flow<String>

    /**
     * 나트륨 설정
     */
    suspend fun setSodium(sodium: String): PwfbResultEntity
    suspend fun getSodium(): Flow<String>

    /**
     * 칼륨 설정
     */
    suspend fun setPotassium(potassium: String): PwfbResultEntity
    suspend fun getPotassium(): Flow<String>

    /**
     * 크레아틴 설정
     */
    suspend fun setCreatine(creatine: String): PwfbResultEntity
    suspend fun getCreatine(): Flow<String>

    /**
     * 식이 섬유 설정
     */
    suspend fun setDietaryFiber(dietaryFiber: String): PwfbResultEntity
    suspend fun getDietaryFiber(): Flow<String>

}