package com.pwfb.domain.usecase.file

import com.pwfb.domain.repository.FileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateLogFileUseCase @Inject constructor(private val fileRepository: FileRepository) {
    operator fun invoke(msg: String) {
        CoroutineScope(Dispatchers.IO).launch {
            fileRepository.createLogFile(msg)
        }
    }
}