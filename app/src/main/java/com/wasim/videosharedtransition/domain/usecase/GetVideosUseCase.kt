package com.wasim.videosharedtransition.domain.usecase

import com.wasim.videosharedtransition.data.model.Video
import com.wasim.videosharedtransition.domain.repository.VideoRepository

class GetVideosUseCase(private val videoRepository: VideoRepository) {
    suspend fun execute():List<Video>? = videoRepository.getVideos()

}