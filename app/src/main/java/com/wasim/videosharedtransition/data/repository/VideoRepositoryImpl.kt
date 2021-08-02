package com.wasim.videosharedtransition.data.repository

import android.util.Log
import com.wasim.videosharedtransition.data.model.Video
import com.wasim.videosharedtransition.data.repository.datasource.VideosCacheDataSource
import com.wasim.videosharedtransition.data.repository.datasource.VideosLocalDataSource
import com.wasim.videosharedtransition.data.repository.datasource.VideosRemoteDatasource
import com.wasim.videosharedtransition.domain.repository.VideoRepository
import java.lang.Exception

class VideoRepositoryImpl(
    private val videosRemoteDatasource: VideosRemoteDatasource,
    private val videosLocalDataSource: VideosLocalDataSource,
    private val videosCacheDataSource: VideosCacheDataSource
) : VideoRepository {

    override suspend fun getVideos(): List<Video>? {
       return getVideosFromCache()
    }


    suspend fun getVideosFromAPI(): List<Video> {
        lateinit var videoList: List<Video>
        try {
            val response = videosRemoteDatasource.getVideos()
            val body = response.body()
            if(body!=null){
                videoList = body.categories[0].videos
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return videoList
    }

    private suspend fun getVideosFromDB():List<Video>{
        lateinit var videoList: List<Video>
        try {
           videoList = videosLocalDataSource.getVideosFromDB()
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        if(videoList.isNotEmpty()){
            return videoList
        }else{
            videoList= getVideosFromAPI()
            videosLocalDataSource.saveVideosToDB(videoList)
        }

        return videoList
    }

    private suspend fun getVideosFromCache():List<Video>{
        lateinit var videoList: List<Video>
        try {
            videoList =videosCacheDataSource.getVideosFromCache()
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        if(videoList.isNotEmpty()){
            return videoList
        }else{
            videoList=getVideosFromDB()
            videosCacheDataSource.saveVideosToCache(videoList)
        }

        return videoList
    }







}