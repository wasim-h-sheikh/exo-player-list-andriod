package com.wasim.exoplayerlist.data.api

import com.wasim.exoplayerlist.data.model.VideoCategories
import retrofit2.Response
import retrofit2.http.GET


interface APIService {

    @GET("/videos")
    suspend fun getVideos(): Response<VideoCategories>

}