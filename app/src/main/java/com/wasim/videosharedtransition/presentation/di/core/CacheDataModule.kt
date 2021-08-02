package com.wasim.videosharedtransition.presentation.di.core

import com.wasim.videosharedtransition.data.repository.datasource.VideosCacheDataSource
import com.wasim.videosharedtransition.data.repository.datasourceImpl.VideosCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {
    @Singleton
    @Provides
    fun provideVideosCacheDataSource(): VideosCacheDataSource {
        return VideosCacheDataSourceImpl()
    }

}