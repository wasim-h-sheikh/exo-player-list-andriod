package com.wasim.exoplayerlist.presentation.di.core

import com.wasim.exoplayerlist.data.repository.datasource.VideosCacheDataSource
import com.wasim.exoplayerlist.data.repository.datasourceImpl.VideosCacheDataSourceImpl
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