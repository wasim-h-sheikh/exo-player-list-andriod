package com.wasim.exoplayerlist.data.repository.datasourceImpl

import com.wasim.exoplayerlist.data.api.APIService
import com.wasim.exoplayerlist.data.model.VideoCategories
import com.wasim.exoplayerlist.data.repository.datasource.VideosRemoteDatasource
import retrofit2.Response

class VideosRemoteDataSourceImpl(
    private val apiService: APIService
): VideosRemoteDatasource {
    override suspend fun getVideos(): Response<VideoCategories> =apiService.getVideos()
}

