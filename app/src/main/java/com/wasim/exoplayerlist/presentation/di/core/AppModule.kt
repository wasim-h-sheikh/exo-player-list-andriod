package com.wasim.exoplayerlist.presentation.di.core

import android.content.Context
import com.wasim.exoplayerlist.presentation.di.video.VideoSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [VideoSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }

}