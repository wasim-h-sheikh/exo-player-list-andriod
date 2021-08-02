package com.wasim.videosharedtransition.data.repository.datasource

import com.wasim.videosharedtransition.data.model.Video
import com.wasim.videosharedtransition.data.model.VideoCategories
import retrofit2.Response

interface VideosRemoteDatasource {
   suspend fun getVideos(): Response<VideoCategories>
}