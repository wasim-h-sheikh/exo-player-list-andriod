package com.wasim.videosharedtransition.presentation.di.core

import com.wasim.videosharedtransition.presentation.di.video.VideoSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        DataBaseModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        RemoteDataModule::class,
        LocalDataModule::class,
        CacheDataModule::class
    ]
)
interface AppComponent {
    fun videoSubComponent(): VideoSubComponent.Factory
}