package com.wasim.videosharedtransition.presentation

import android.app.Application
import com.wasim.videosharedtransition.BuildConfig
import com.wasim.videosharedtransition.presentation.di.Injector
import com.wasim.videosharedtransition.presentation.di.core.*
import com.wasim.videosharedtransition.presentation.di.video.VideoSubComponent

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