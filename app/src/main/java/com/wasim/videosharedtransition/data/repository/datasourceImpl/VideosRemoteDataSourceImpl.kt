package com.wasim.videosharedtransition.data.repository.datasourceImpl

import com.wasim.videosharedtransition.data.api.APIService
import com.wasim.videosharedtransition.data.model.VideoCategories
import com.wasim.videosharedtransition.data.repository.datasource.VideosRemoteDatasource
import retrofit2.Response

class VideosRemoteDataSourceImpl(
    private val apiService: APIService
): VideosRemoteDatasource {
    override suspend fun getVideos(): Response<VideoCategories> =apiService.getVideoCategory()
}

