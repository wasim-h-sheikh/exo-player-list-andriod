package com.wasim.exoplayerlist.data.repository.datasourceImpl

import com.wasim.exoplayerlist.data.db.VideoDao
import com.wasim.exoplayerlist.data.model.Video
import com.wasim.exoplayerlist.data.repository.datasource.VideosLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideosLocalDataSourceImpl(private val videoDao: VideoDao):
    VideosLocalDataSource {
    override suspend fun getVideosFromDB(): List<Video> {
       return videoDao.getVideos()
    }

    override suspend fun saveVideosToDB(videos: List<Video>) {
        CoroutineScope(Dispatchers.IO).launch {
            videoDao.saveVideos(videos)
        }
    }

    override suspend fun clearAll() {
       CoroutineScope(Dispatchers.IO).launch {
           videoDao.deleteAllVideos()
       }
    }
}