package com.wasim.exoplayerlist.data.model

import com.google.gson.annotations.SerializedName

data class VideoCategories(
    @SerializedName("categories")
    val categories: List<Category>
)