package com.wasim.exoplayerlist.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("name")
    val name: String,
    @SerializedName("videos")
    val videos: List<Video>
)