package com.wasim.exoplayerlist.presentation

import android.app.Application
import com.wasim.exoplayerlist.BuildConfig
import com.wasim.exoplayerlist.presentation.di.Injector
import com.wasim.exoplayerlist.presentation.di.core.*
import com.wasim.exoplayerlist.presentation.di.video.VideoSubComponent

class App : Application(), Injector {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule())
            .build()

    }

    override fun createVideoSubComponent(): VideoSubComponent {
        return appComponent.videoSubComponent().create()
    }



}