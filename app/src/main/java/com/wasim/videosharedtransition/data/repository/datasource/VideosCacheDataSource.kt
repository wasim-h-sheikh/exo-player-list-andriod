package com.wasim.videosharedtransition.data.repository.datasource

import com.wasim.videosharedtransition.data.model.Video


interface VideosCacheDataSource {
    suspend fun getVideosFromCache():List<Video>
    suspend fun saveVideosToCache(videos:List<Video>)
}