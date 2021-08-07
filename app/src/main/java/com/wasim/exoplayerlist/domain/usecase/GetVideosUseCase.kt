package com.wasim.exoplayerlist.domain.usecase

import com.wasim.exoplayerlist.data.model.Video
import com.wasim.exoplayerlist.domain.repository.VideoRepository

class GetVideosUseCase(private val videoRepository: VideoRepository) {
    suspend fun execute():List<Video>? = videoRepository.getVideos()

}