package com.wasim.exoplayerlist.presentation.di.core

import com.wasim.exoplayerlist.data.repository.VideoRepositoryImpl
import com.wasim.exoplayerlist.data.repository.datasource.VideosCacheDataSource
import com.wasim.exoplayerlist.data.repository.datasource.VideosLocalDataSource
import com.wasim.exoplayerlist.data.repository.datasource.VideosRemoteDatasource
import com.wasim.exoplayerlist.domain.repository.VideoRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideVideoRepository(
        videosRemoteDatasource: VideosRemoteDatasource,
        videosLocalDataSource: VideosLocalDataSource,
        videosCacheDataSource: VideosCacheDataSource
    ): VideoRepository {

        return VideoRepositoryImpl(
            videosRemoteDatasource,
            videosLocalDataSource,
            videosCacheDataSource
        )


    }

}