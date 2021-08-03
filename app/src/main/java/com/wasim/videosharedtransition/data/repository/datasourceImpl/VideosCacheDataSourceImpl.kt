package com.wasim.videosharedtransition.data.repository.datasourceImpl

import com.wasim.videosharedtransition.data.model.Video
import com.wasim.videosharedtransition.data.repository.datasource.VideosCacheDataSource

class VideosCacheDataSourceImpl :
    VideosCacheDataSource {
    private var videoList = ArrayList<Video>()

    override suspend fun getVideosFromCache(): List<Video> {
        return videoList
    }

    override suspend fun saveVideosToCache(videos: List<Video>) {
       videoList.clear()
       videoList = ArrayList(videos)
    }
}