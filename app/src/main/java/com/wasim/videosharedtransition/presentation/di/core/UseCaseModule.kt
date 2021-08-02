package com.wasim.videosharedtransition.presentation.di.core

import com.wasim.videosharedtransition.domain.repository.VideoRepository
import com.wasim.videosharedtransition.domain.usecase.GetVideosUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetVideosUseCase(videoRepository: VideoRepository): GetVideosUseCase {
        return GetVideosUseCase(videoRepository)
    }

}