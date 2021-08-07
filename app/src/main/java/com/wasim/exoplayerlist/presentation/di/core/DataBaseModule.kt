package com.wasim.exoplayerlist.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.wasim.exoplayerlist.data.db.VideoDao
import com.wasim.exoplayerlist.data.db.VideoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideVideoDatabase(context: Context): VideoDatabase {
        return Room.databaseBuilder(context, VideoDatabase::class.java, "video_database")
            .build()
    }

    @Singleton
    @Provides
    fun provideVideoDao(videoDatabase: VideoDatabase): VideoDao {
        return videoDatabase.videoDao()
    }

}