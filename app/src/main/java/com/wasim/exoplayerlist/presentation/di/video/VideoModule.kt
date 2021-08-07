package com.wasim.exoplayerlist.presentation.di.video

import com.wasim.exoplayerlist.domain.usecase.GetVideosUseCase
import com.wasim.exoplayerlist.presentation.video.VideoViewModelFactory
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