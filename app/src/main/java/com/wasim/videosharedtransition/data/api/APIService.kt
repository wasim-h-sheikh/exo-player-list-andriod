package com.wasim.videosharedtransition.data.api

import com.wasim.videosharedtransition.data.model.VideoCategories
import retrofit2.Response
import retrofit2.http.GET


interface APIService {

    @GET("categories")
    suspend fun getVideoCategory(): Response<VideoCategories>

}