package com.wasim.videosharedtransition.data.repository.datasourceImpl

import com.wasim.videosharedtransition.data.model.Video
import com.wasim.videosharedtransition.data.repository.datasource.VideosCacheDataSource

class VideosCacheDataSourceImpl :
    VideosCacheDataSource {
    private var artistList = ArrayList<Video>()

    override suspend fun getVideosFromCache(): List<Video> {
        return artistList
    }

    override suspend fun saveVideosToCache(videos: List<Video>) {
       artistList.clear()
       artistList = ArrayList(videos)
    }
}