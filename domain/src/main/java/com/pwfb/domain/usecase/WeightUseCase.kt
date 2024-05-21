package com.pwfb.domain.usecase

import com.pwfb.domain.entity.PwfbResultEntity
import com.pwfb.domain.repository.PrefRepository
import javax.inject.Inject

class WeightUseCase @Inject constructor(
    private val repository: PrefRepository
) {
    suspend operator fun invoke() = repository.getWeight()

    suspend fun setWeight(weight: String): PwfbResultEntity {
        return repository.setWeight(weight)
    }
}