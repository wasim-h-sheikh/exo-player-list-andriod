package com.wasim.exoplayerlist.data.repository.datasource

import com.wasim.exoplayerlist.data.model.Video

interface VideosLocalDataSource {
  suspend fun getVideosFromDB():List<Video>
  suspend fun saveVideosToDB(videos:List<Video>)
  suspend fun clearAll()
}