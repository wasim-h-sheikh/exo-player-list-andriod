package com.wasim.videosharedtransition.presentation.di

import com.wasim.videosharedtransition.presentation.di.video.VideoSubComponent

interface Injector {
    fun createVideoSubComponent(): VideoSubComponent
}