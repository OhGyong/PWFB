package com.pwfb.domain.repository

import java.io.File

interface FileRepository {
    suspend fun createLogFile(msg: String)
    fun getFile(): File?
}