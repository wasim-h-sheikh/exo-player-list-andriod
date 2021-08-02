package com.wasim.videosharedtransition.presentation.di.video

import com.wasim.videosharedtransition.domain.usecase.GetVideosUseCase
import com.wasim.videosharedtransition.presentation.video.VideoViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class VideoModule {
    @VideoScope
    @Provides
    fun provideHomeViewModelFactory(
        getVideosUseCase: GetVideosUseCase
    ): VideoViewModelFactory {
        return VideoViewModelFactory(
            getVideosUseCase
        )
    }

}