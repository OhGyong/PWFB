package com.pwfb.domain.usecase

import com.pwfb.domain.entity.PwfbResultEntity
import com.pwfb.domain.repository.PrefRepository
import javax.inject.Inject

class DdayUseCase @Inject constructor(
    private val repository: PrefRepository
) {
    suspend operator fun invoke() = repository

    suspend fun setDday(dDay: String): PwfbResultEntity {
        return repository.setDDay(dDay)
    }

    suspend fun setFistInit(): PwfbResultEntity {
        return repository.setFistInit()
    }
}