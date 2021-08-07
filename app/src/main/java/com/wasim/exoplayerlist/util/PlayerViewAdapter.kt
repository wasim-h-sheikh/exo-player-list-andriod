package com.wasim.exoplayerlist.util

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.SimpleExoPlayer


// extension function for show toast
fun Context.toast(text: String){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

class PlayerViewAdapter {

    companion object{
        // for hold all players generated
        private var playersMap: MutableMap<Int, SimpleExoPlayer>  = mutableMapOf()
        // for hold current player
        private var currentPlayingVideo: Pair<Int, SimpleExoPlayer>? = null
        fun releaseAllPlayers(){
            playersMap.map {
                it.value.release()
            }
        }

        // call when item recycled to improve performance
        fun releaseRecycledPlayers(index: Int){
            playersMap[index]?.release()
        }

        // call when scroll to pause any playing player
        fun pauseCurrentPlayingVideo(){
            if (currentPlayingVideo != null){
                currentPlayingVideo?.second?.playWhenReady = false
            }
        }

        fun playIndexThenPausePreviousPlayer(index: Int){
            if (playersMap.get(index)?.playWhenReady == false) {
                pauseCurrentPlayingVideo()
                playersMap.get(index)?.playWhenReady = true
                currentPlayingVideo = Pair(index, playersMap.get(index)!!)
            }

        }

        fun getCurrentPlayerPosition(index: Int):Long?{
            return playersMap[index]?.currentPosition
        }
        fun getCurrentPlayerInstance(index: Int):SimpleExoPlayer?{
            return playersMap[index]
        }
        /*
        *  url is a url of stream video
        * thumbnail for show before video start
        * */
        @JvmStatic
        @BindingAdapter(value = ["video_url", "on_state_change", "thumbnail", "item_index", "autoPlay"], requireAll = false)
        fun PlayerView.loadVideo(url: String, callback: PlayerStateCallback, thumbnail: ImageView, item_index: Int? = null, autoPlay: Boolean = false) {
            if (url == null) return
            val player = SimpleExoPlayer.Builder(context).build()
            player.volume= 0F
            player.playWhenReady = autoPlay
            player.repeatMode = Player.REPEAT_MODE_ALL
            // When changing track, retain the latest frame instead of showing a black screen
            setKeepContentOnPlayerReset(true)
            // We'll show the controller, change to true if want controllers as pause and start
            this.useController = false
            Log.v("wasim_dev","url is $url")
            // Provide url to load the video from here
            val mediaSource = ProgressiveMediaSource.Factory(DefaultHttpDataSourceFactory("Demo")).createMediaSource(Uri.parse(url.replaceFirst("http://","https://")))

            player.prepare(mediaSource)

            this.player = player

            // add player with its index to map
            if (playersMap.containsKey(item_index))
                PlayerViewAdapter.playersMap.remove(item_index)
            if (item_index != null)
                playersMap[item_index] = player

            this.player!!.addListener(object : Player.EventListener {

                override fun onPlayerError(error: ExoPlaybackException) {
                    super.onPlayerError(error)
                    Log.v("wasim_dev", "player error:${error.message}")
                    this@loadVideo.context.toast("Oops! Error occurred while playing media.")
                }

                override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                    super.onPlayerStateChanged(playWhenReady, playbackState)

                    if (playbackState == Player.STATE_BUFFERING){
                        callback.onVideoBuffering(player)
                        // Buffering..
                        // set thumbnail visible
                        thumbnail.visibility = View.VISIBLE
                        Log.v("wasim_dev","thumbnail.visibility = View.VISIBLE")
                    }

                    if (playbackState == Player.STATE_READY){

                        // set thumbnail gone
                        thumbnail.visibility = View.GONE
                        Log.v("wasim_dev","thumbnail.visibility = View.GONE")

                        callback.onVideoDurationRetrieved(this@loadVideo.player!!.duration, player)
                    }

                    if (playbackState == Player.STATE_READY && player.playWhenReady){
                        // [PlayerView] has started playing/resumed the video
                        callback.onStartedPlaying(player)
                    }
                }
            })
        }


    }
}