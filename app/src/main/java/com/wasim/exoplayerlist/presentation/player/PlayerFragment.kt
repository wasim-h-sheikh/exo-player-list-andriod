package com.wasim.exoplayerlist.presentation.player

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.Player
import com.wasim.exoplayerlist.R
import com.wasim.exoplayerlist.data.model.Video
import com.wasim.exoplayerlist.databinding.FragmentPlayerBinding
import com.wasim.exoplayerlist.util.PlayerBinding
import com.wasim.exoplayerlist.util.PlayerBinding.Companion.enableSound
import com.wasim.exoplayerlist.util.PlayerStateCallback


class PlayerFragment : Fragment() , PlayerStateCallback {
    private lateinit var video: Video
    private var seekToPosition: Long? = null
    private val args: PlayerFragmentArgs by navArgs()
    private lateinit var binding: FragmentPlayerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_player, container, false)
        video=args.video
        seekToPosition=args.seekToPosition
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            dataModel = video
            callback = this@PlayerFragment
            seekTo= seekToPosition!!
        }
        binding.imageViewCancel.setOnClickListener {
            val directions = PlayerFragmentDirections.actionPlayerFragmentToVideoFragment()
            findNavController().navigate(directions)
        }
        binding.imageViewVolume.setOnClickListener {
            if (enableSound()){
                binding.imageViewVolume.setImageResource(R.drawable.ic_volume_on_32dp)
            }else{
                binding.imageViewVolume.setImageResource(R.drawable.ic_volume_off_32dp)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        PlayerBinding.pauseCurrentPlayingVideo()
    }
    override fun onVideoDurationRetrieved(duration: Long, player: Player) {

    }

    override fun onVideoBuffering(player: Player) {

    }

    override fun onStartedPlaying(player: Player) {

    }

    override fun onFinishedPlaying(player: Player) {

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Checking the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val params = binding.mediaContainer.layoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val params = binding.mediaContainer.layoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = resources.getDimensionPixelSize(R.dimen.player_height)
            binding.mediaContainer.layoutParams = params
        }
    }
}