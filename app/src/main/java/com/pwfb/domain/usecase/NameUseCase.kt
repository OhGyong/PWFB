package com.pwfb.domain.usecase

import com.pwfb.domain.entity.PwfbResultEntity
import com.pwfb.domain.repository.PrefRepository
import javax.inject.Inject

class NameUseCase @Inject constructor(
    private val repository: PrefRepository
) {
    suspend operator fun invoke() = repository.getName()

    suspend fun setName(name: String): PwfbResultEntity {
        return repository.setName(name)
    }
}