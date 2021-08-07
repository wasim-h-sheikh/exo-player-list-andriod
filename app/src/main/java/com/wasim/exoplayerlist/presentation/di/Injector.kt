package com.wasim.exoplayerlist.presentation.di

import com.wasim.exoplayerlist.presentation.di.video.VideoSubComponent

interface Injector {
    fun createVideoSubComponent(): VideoSubComponent
}