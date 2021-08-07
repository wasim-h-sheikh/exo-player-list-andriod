package com.wasim.exoplayerlist.presentation.video

import androidx.lifecycle.*
import com.wasim.exoplayerlist.domain.usecase.GetVideosUseCase

class VideoViewModel(
    private val getVideosUseCase: GetVideosUseCase
): ViewModel() {
    fun getVideos() = liveData {
        val videoList = getVideosUseCase.execute()
        emit(videoList)
    }
}