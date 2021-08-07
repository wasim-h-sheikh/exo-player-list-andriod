package com.wasim.exoplayerlist.presentation.di.core

import com.wasim.exoplayerlist.data.api.APIService
import com.wasim.exoplayerlist.data.repository.datasource.VideosRemoteDatasource
import com.wasim.exoplayerlist.data.repository.datasourceImpl.VideosRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule() {
    @Singleton
    @Provides
    fun provideVideosRemoteDatasource(apiService: APIService): VideosRemoteDatasource {
        return VideosRemoteDataSourceImpl(
            apiService
        )
    }

}