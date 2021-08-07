package com.wasim.exoplayerlist.util

import android.net.Uri
import android.util.Log
import androidx.databinding.BindingAdapter
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory

class PlayerBinding {
    companion object {
        private lateinit var exoPlayer: SimpleExoPlayer
        private var initialVolume:Float = 0.0f

        // call when scroll to pause any playing player
        fun pauseCurrentPlayingVideo(){
            exoPlayer.playWhenReady = false
        }
        fun enableSound():Boolean{
            if (exoPlayer.volume==initialVolume){
                exoPlayer.volume = 0F
                return false
            }else{
                exoPlayer.volume = initialVolume
                return true
            }
        }
        /*
      *  url is a url of stream video
      * */
        @JvmStatic
        @BindingAdapter(value = ["video_url", "on_state_change", "seek_to", "autoPlay"],requireAll = false)
        fun PlayerView.loadVideo(
            url: String,
            callback: PlayerStateCallback,
            seekTo: Long? = null,
            autoPlay: Boolean = false
        ) {
            if (url == null) return

            exoPlayer = SimpleExoPlayer.Builder(context)
                .build()

            exoPlayer.playWhenReady = autoPlay
            exoPlayer.repeatMode = Player.REPEAT_MODE_ALL
            // When changing track, retain the latest frame instead of showing a black screen
            setKeepContentOnPlayerReset(true)
            // We'll show the controller, change to true if want controllers as pause and start
            this.useController = false
            Log.v("wasim_dev", "PlayerBinding url is $url")
            // Provide url to load the video from here
            val mediaSource = ProgressiveMediaSource.Factory(DefaultHttpDataSourceFactory("Demo"))
                .createMediaSource(
                    Uri.parse(url.replaceFirst("http://", "https://"))
                )

            exoPlayer.prepare(mediaSource)
            this.player = exoPlayer
            initialVolume= exoPlayer.volume
            Log.v("wasim", "seek to$seekTo");
            exoPlayer.seekTo(seekTo!!)
            this.player!!.addListener(object : Player.EventListener {

                override fun onPlayerError(error: ExoPlaybackException) {
                    super.onPlayerError(error)
                    Log.v("wasim_dev", "player error:${error.message}")
                    this@loadVideo.context.toast("Oops! Error occurred while playing media.")
                }

                override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                    super.onPlayerStateChanged(playWhenReady, playbackState)

                    if (playbackState == Player.STATE_BUFFERING) {
                        callback.onVideoBuffering(exoPlayer)
                        // Buffering..
                   }

                    if (playbackState == Player.STATE_READY) {
                        callback.onVideoDurationRetrieved(this@loadVideo.player!!.duration, exoPlayer)

                    }

                    if (playbackState == Player.STATE_READY && exoPlayer.playWhenReady) {
                        // [PlayerView] has started playing/resumed the video
                        callback.onStartedPlaying(exoPlayer)

                    }
                }
            })
        }
    }
}