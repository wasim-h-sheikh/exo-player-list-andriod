package com.wasim.exoplayerlist.presentation.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wasim.exoplayerlist.domain.usecase.GetVideosUseCase

class VideoViewModelFactory(
    private val getVideosUseCase: GetVideosUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideoViewModel(
            getVideosUseCase
        ) as T
    }
}