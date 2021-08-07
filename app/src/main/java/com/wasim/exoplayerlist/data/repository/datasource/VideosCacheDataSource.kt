package com.wasim.exoplayerlist.data.repository.datasource

import com.wasim.exoplayerlist.data.model.Video


interface VideosCacheDataSource {
    suspend fun getVideosFromCache():List<Video>
    suspend fun saveVideosToCache(videos:List<Video>)
}