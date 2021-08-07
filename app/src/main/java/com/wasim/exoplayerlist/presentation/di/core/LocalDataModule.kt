package com.wasim.exoplayerlist.presentation.di.core

import com.wasim.exoplayerlist.data.db.VideoDao
import com.wasim.exoplayerlist.data.repository.datasource.VideosLocalDataSource
import com.wasim.exoplayerlist.data.repository.datasourceImpl.VideosLocalDataSourceImpl
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