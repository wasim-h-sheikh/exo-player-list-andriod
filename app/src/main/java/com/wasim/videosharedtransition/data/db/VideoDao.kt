package com.wasim.videosharedtransition.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wasim.videosharedtransition.data.model.Video

@Dao
interface VideoDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun saveVideos(videos : List<Video>)

@Query("DELETE FROM table_video")
suspend fun deleteAllVideos()

@Query("SELECT * FROM table_video")
suspend fun getVideos():List<Video>
}