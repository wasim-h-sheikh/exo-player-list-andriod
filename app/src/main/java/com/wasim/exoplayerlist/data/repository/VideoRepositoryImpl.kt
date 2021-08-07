package com.wasim.exoplayerlist.data.repository

import android.util.Log
import com.wasim.exoplayerlist.data.model.Video
import com.wasim.exoplayerlist.data.repository.datasource.VideosCacheDataSource
import com.wasim.exoplayerlist.data.repository.datasource.VideosLocalDataSource
import com.wasim.exoplayerlist.data.repository.datasource.VideosRemoteDatasource
import com.wasim.exoplayerlist.domain.repository.VideoRepository
import java.lang.Exception

class VideoRepositoryImpl(
    private val videosRemoteDatasource: VideosRemoteDatasource,
    private val videosLocalDataSource: VideosLocalDataSource,
    private val videosCacheDataSource: VideosCacheDataSource
) : VideoRepository {

    override suspend fun getVideos(): List<Video>? {
       return getVideosFromCache()
    }


    private suspend fun getVideosFromAPI(): List<Video> {
        Log.v("wasim_dev", "getVideosFromAPI")
        lateinit var videoList: List<Video>
        try {
            Log.v("wasim_dev", "calling videosRemoteDatasource.getVideos()")
            val response = videosRemoteDatasource.getVideos()
            Log.v("wasim_dev", "after calling videosRemoteDatasource.getVideos()")

            val body = response.body()
            Log.v("wasim_dev", "response.body()")

            if(body!=null){
                videoList = body.categories[0].videos
            }
        } catch (exception: Exception) {
            Log.v("wasim_dev", exception.message.toString())
        }
        return videoList
    }

    private suspend fun getVideosFromDB():List<Video>{
        Log.v("wasim_dev", "getVideosFromDB")
        lateinit var videoList: List<Video>
        try {
           videoList = videosLocalDataSource.getVideosFromDB()
        } catch (exception: Exception) {
            Log.v("wasim_dev", exception.message.toString())
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
        Log.v("wasim_dev", "getVideosFromCache")
        lateinit var videoList: List<Video>
        try {
            videoList =videosCacheDataSource.getVideosFromCache()
        } catch (exception: Exception) {
            Log.v("wasim_dev", exception.message.toString())
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