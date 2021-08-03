package com.wasim.videosharedtransition.presentation.di.video

import com.wasim.videosharedtransition.presentation.video.VideoFragment
import dagger.Subcomponent

@VideoScope
@Subcomponent(modules = [VideoModule::class])
interface VideoSubComponent {
    fun inject(videoFragment: VideoFragment)
    @Subcomponent.Factory
    interface Factory {
        fun create(): VideoSubComponent
    }

}

