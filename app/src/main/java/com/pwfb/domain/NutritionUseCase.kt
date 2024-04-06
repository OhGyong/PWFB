package com.pwfb.domain

import kotlinx.coroutines.flow.Flow

class NutritionUseCase(
    private val repository: PrefRepository
) {

    /**
     * 이름 설정
     */
    suspend fun setName(name: String): PwfbResultEntity {
        return repository.setName(name)
    }

    suspend fun getName(): Flow<String> {
        return repository.getName()
    }

    /**
     * 최초 진입 여부 설정
     */
    suspend fun setFistInit(): PwfbResultEntity {
        return repository.setFistInit()
    }

    suspend fun getFirstInit(): Flow<Boolean> {
        return repository.getFirstInit()
    }

    /**
     * 몸무게 설정
     */
    suspend fun setWeight(weight: String): PwfbResultEntity {
        return repository.setWeight(weight)
    }

    suspend fun getWeight(): Flow<String> {
        return repository.getWeight()
    }

    /**
     * D-Day 설정
     */
    suspend fun setDDay(dDay: String): PwfbResultEntity {
        return repository.setDDay(dDay)
    }

    suspend fun getDDay(): Flow<String> {
        return repository.getDDay()
    }

    /**
     * 운동 계획 설정
     */
    suspend fun setTrainingProgram(trainingProgram: String): PwfbResultEntity {
        return repository.setTrainingProgram(trainingProgram)
    }

    suspend fun getTrainingProgram(): Flow<String> {
        return repository.getTrainingProgram()
    }

    /**
     * 탄수화물 설정
     */
    suspend fun setCarbohydrate(carbohydrate: String): PwfbResultEntity {
        return repository.setCarbohydrate(carbohydrate)
    }

    suspend fun getCarbohydrate(): Flow<String> {
        return repository.getCarbohydrate()
    }

    /**
     * 단백질 설정
     */
    suspend fun setProtein(protein: String): PwfbResultEntity {
        return repository.setProtein(protein)
    }

    suspend fun getProtein(): Flow<String> {
        return repository.getProtein()
    }

    /**
     * 지방 설정
     */
    suspend fun setFat(fat: String): PwfbResultEntity {
        return repository.setFat(fat)
    }

    suspend fun getFat(): Flow<String> {
        return repository.getFat()
    }

    /**
     * 수분 설정
     */
    suspend fun setWater(water: String): PwfbResultEntity {
        return repository.setWater(water)
    }

    suspend fun getWater(): Flow<String> {
        return repository.getWater()
    }

    /**
     * 나트륨 설정
     */
    suspend fun setSodium(sodium: String): PwfbResultEntity {
        return repository.setSodium(sodium)
    }

    suspend fun getSodium(): Flow<String> {
        return repository.getSodium()
    }

    /**
     * 칼륨 설정
     */
    suspend fun setPotassium(potassium: String): PwfbResultEntity {
        return repository.setPotassium(potassium)
    }

    suspend fun getPotassium(): Flow<String> {
        return repository.getPotassium()
    }

    /**
     * 크레아틴 설정
     */
    suspend fun setCreatine(creatine: String): PwfbResultEntity {
        return repository.setCreatine(creatine)
    }

    suspend fun getCreatine(): Flow<String> {
        return repository.getCreatine()
    }


}