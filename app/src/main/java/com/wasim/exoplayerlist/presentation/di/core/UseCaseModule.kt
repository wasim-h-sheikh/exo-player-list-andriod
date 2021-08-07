package com.wasim.exoplayerlist.presentation.di.core

import com.wasim.exoplayerlist.domain.repository.VideoRepository
import com.wasim.exoplayerlist.domain.usecase.GetVideosUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetVideosUseCase(videoRepository: VideoRepository): GetVideosUseCase {
        return GetVideosUseCase(videoRepository)
    }

}