package com.wasim.videosharedtransition.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "table_video")
data class Video(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val description: String,
    val sources: List<String>,
    val subtitle: String,
    val thumb: String,
    val title: String
):Parcelable