package com.wasim.videosharedtransition.data.api

import com.wasim.videosharedtransition.data.model.Category
import com.wasim.videosharedtransition.data.model.VideoCategories
import retrofit2.Response
import retrofit2.http.GET


interface APIService {

    @GET("/videos")
    suspend fun getVideos(): Response<VideoCategories>

}