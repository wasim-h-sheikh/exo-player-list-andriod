package com.wasim.videosharedtransition.presentation.di.core

import com.wasim.videosharedtransition.data.repository.VideoRepositoryImpl
import com.wasim.videosharedtransition.data.repository.datasource.VideosCacheDataSource
import com.wasim.videosharedtransition.data.repository.datasource.VideosLocalDataSource
import com.wasim.videosharedtransition.data.repository.datasource.VideosRemoteDatasource
import com.wasim.videosharedtransition.domain.repository.VideoRepository
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