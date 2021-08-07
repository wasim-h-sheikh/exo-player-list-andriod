package com.wasim.exoplayerlist.domain.repository

import com.wasim.exoplayerlist.data.model.Video

interface VideoRepository {
    suspend fun getVideos():List<Video>?
}