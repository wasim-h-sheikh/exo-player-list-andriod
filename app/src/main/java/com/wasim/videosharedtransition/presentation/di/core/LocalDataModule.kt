package com.wasim.videosharedtransition.presentation.di.core

import com.wasim.videosharedtransition.data.db.VideoDao
import com.wasim.videosharedtransition.data.repository.datasource.VideosLocalDataSource
import com.wasim.videosharedtransition.data.repository.datasourceImpl.VideosLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideVideosLocalDataSource(videoDao: VideoDao): VideosLocalDataSource {
        return VideosLocalDataSourceImpl(videoDao)
    }

}