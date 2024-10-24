package com.pwfb.data

import android.content.Context
import com.pwfb.domain.repository.FileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FileModule {
    @Provides
    @Singleton
    fun provideFileRepository(@ApplicationContext applicationContext: Context): FileRepository
            = FileRepositoryImpl(File(applicationContext.filesDir, "logs"))
}