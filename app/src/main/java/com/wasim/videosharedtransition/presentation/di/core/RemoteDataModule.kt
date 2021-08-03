package com.wasim.videosharedtransition.presentation.di.core

import com.wasim.videosharedtransition.data.api.APIService
import com.wasim.videosharedtransition.data.repository.datasource.VideosRemoteDatasource
import com.wasim.videosharedtransition.data.repository.datasourceImpl.VideosRemoteDataSourceImpl
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