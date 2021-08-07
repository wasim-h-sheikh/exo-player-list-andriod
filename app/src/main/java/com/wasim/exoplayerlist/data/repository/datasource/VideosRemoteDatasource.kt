package com.wasim.exoplayerlist.data.repository.datasource

import com.wasim.exoplayerlist.data.model.VideoCategories
import retrofit2.Response

interface VideosRemoteDatasource {
   suspend fun getVideos(): Response<VideoCategories>
}