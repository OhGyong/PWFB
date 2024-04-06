package com.pwfb.domain

sealed class PwfbResultEntity {
    data class Success(val success: Int): PwfbResultEntity()
    data class Failure(val failure: Exception): PwfbResultEntity()
}