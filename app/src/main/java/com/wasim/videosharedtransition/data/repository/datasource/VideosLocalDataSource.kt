package com.wasim.videosharedtransition.data.repository.datasource

import com.wasim.videosharedtransition.data.model.Video

interface VideosLocalDataSource {
  suspend fun getVideosFromDB():List<Video>
  suspend fun saveVideosToDB(videos:List<Video>)
  suspend fun clearAll()
}