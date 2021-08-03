package com.wasim.videosharedtransition.presentation.video

import android.util.Log
import androidx.lifecycle.*
import com.wasim.videosharedtransition.data.model.Video
import com.wasim.videosharedtransition.domain.usecase.GetVideosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoViewModel(
    private val getVideosUseCase: GetVideosUseCase
): ViewModel() {
    fun getVideos() = liveData {
        val videoList = getVideosUseCase.execute()
        emit(videoList)
    }
}