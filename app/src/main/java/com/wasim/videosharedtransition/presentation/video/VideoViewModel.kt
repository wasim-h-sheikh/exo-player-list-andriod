package com.wasim.videosharedtransition.presentation.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wasim.videosharedtransition.domain.usecase.GetVideosUseCase

class VideoViewModel(
    private val getVideosUseCase: GetVideosUseCase
): ViewModel() {

    fun getVideos() = liveData {
        val videoList = getVideosUseCase.execute()
        emit(videoList)
    }

}