package com.wasim.exoplayerlist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wasim.exoplayerlist.data.model.Video
import com.wasim.exoplayerlist.util.Converters


@Database(entities = [Video::class],
version = 1,
exportSchema = false
)
@TypeConverters(Converters::class)
abstract class VideoDatabase : RoomDatabase(){
abstract fun videoDao(): VideoDao
}