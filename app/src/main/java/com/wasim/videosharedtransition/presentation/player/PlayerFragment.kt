package com.wasim.videosharedtransition.presentation.player

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.Player
import com.google.android.material.transition.MaterialContainerTransform
import com.wasim.videosharedtransition.R
import com.wasim.videosharedtransition.data.model.Video
import com.wasim.videosharedtransition.databinding.FragmentPlayerBinding
import com.wasim.videosharedtransition.presentation.video.VideoFragmentDirections
import com.wasim.videosharedtransition.util.PlayerBinding
import com.wasim.videosharedtransition.util.PlayerBinding.Companion.enableSound
import com.wasim.videosharedtransition.util.PlayerStateCallback
import com.wasim.videosharedtransition.util.PlayerViewAdapter


class PlayerFragment : Fragment() , PlayerStateCallback {
    private lateinit var video: Video
    private var seekToPosition: Long? = null
    private val args: PlayerFragmentArgs by navArgs()
    private lateinit var binding: FragmentPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }

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

}