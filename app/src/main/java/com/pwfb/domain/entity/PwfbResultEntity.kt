package com.pwfb.domain.entity

sealed class PwfbResultEntity {
    data class Success(val success: Int): PwfbResultEntity()
    data class Failure(val failure: Exception): PwfbResultEntity()
    data class Data(val data: Any): PwfbResultEntity()
}