package com.wasim.videosharedtransition.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wasim.videosharedtransition.data.model.Video


@Database(entities = [Video::class],
version = 1,
exportSchema = false
)
abstract class VideoDatabase : RoomDatabase(){
abstract fun videoDao(): VideoDao
}