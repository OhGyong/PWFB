package com.pwfb.data

import com.pwfb.common.Firebase.crashlytics
import com.pwfb.domain.repository.FileRepository
import com.pwfb.util.getCurrentTime
import com.pwfb.util.getCurrentToday
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(private val logsDirectory: File) : FileRepository {
    override suspend fun createLogFile(msg: String) {
        try {
            if (!logsDirectory.exists()) {
                logsDirectory.mkdirs()
            }

            val file = File(logsDirectory, "${getCurrentToday()}.txt")

            if (!file.exists()) withContext(Dispatchers.IO) {
                file.createNewFile()
            }

            val header = "【ERROR】[${getCurrentToday() + " " + getCurrentTime()}]"

            withContext(Dispatchers.IO) {
                BufferedWriter(FileWriter(file, true)).use { buf ->
                    buf.append("$header $msg")
                    buf.newLine()
                }
            }
        } catch (e: Exception) {
            crashlytics.recordException(e)
        }
    }

    override fun getFile(): File? = logsDirectory.listFiles()?.find {
        it.name == getCurrentToday() + ".txt"
    }
}