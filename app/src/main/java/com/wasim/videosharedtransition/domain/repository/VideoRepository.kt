package com.wasim.videosharedtransition.domain.repository

import com.wasim.videosharedtransition.data.model.Video

interface VideoRepository {
    suspend fun getVideos():List<Video>?
}