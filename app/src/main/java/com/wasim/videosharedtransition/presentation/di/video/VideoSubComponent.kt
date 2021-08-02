package com.wasim.videosharedtransition.presentation.di.video

import com.wasim.videosharedtransition.presentation.video.VideoActivity
import dagger.Subcomponent

@VideoScope
@Subcomponent(modules = [VideoModule::class])
interface VideoSubComponent {
    fun inject(videoActivity: VideoActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): VideoSubComponent
    }

}

