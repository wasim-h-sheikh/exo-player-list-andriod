package com.wasim.videosharedtransition.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_video")
data class Video(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val description: String,
    val sources: List<String>,
    val subtitle: String,
    val thumb: String,
    val title: String
)