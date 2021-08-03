package com.wasim.videosharedtransition.presentation.video

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wasim.videosharedtransition.R
import com.wasim.videosharedtransition.data.model.Video
import com.wasim.videosharedtransition.databinding.VideoFragmentBinding
import com.wasim.videosharedtransition.presentation.di.Injector
import com.wasim.videosharedtransition.presentation.player.PlayerFragment
import com.wasim.videosharedtransition.util.*
import javax.inject.Inject

class VideoFragment : Fragment() {

    companion object {
        fun newInstance() = VideoFragment()
    }
    @Inject
    lateinit var factory: VideoViewModelFactory
    private lateinit var videoViewModel: VideoViewModel
    private lateinit var binding:VideoFragmentBinding
    private lateinit var adapter: VideoAdapter
    // for handle scroll and get first visible item index
    private lateinit var scrollListener: RecyclerViewScrollListener
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.video_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoViewModel = ViewModelProvider(this,factory).get(VideoViewModel::class.java)
        initRecyclerView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as Injector).createVideoSubComponent().inject(this)
    }


    private fun initRecyclerView(){
        binding.videoRecyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = VideoAdapter()
        binding.videoRecyclerView.adapter = adapter

        scrollListener = object : RecyclerViewScrollListener() {
            override fun onItemIsFirstVisibleItem(index: Int) {
                Log.d("visible item index", index.toString())
                // play just visible item
                if (index != -1)
                    PlayerViewAdapter.playIndexThenPausePreviousPlayer(index)
            }

        }
        binding.videoRecyclerView.addOnScrollListener(scrollListener)
        adapter.SetOnItemClickListener(object : VideoAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int, video: Video?) {
                startPlayer(video, position)
            }
        })

        displayVideos()
    }

    private fun startPlayer(
        video: Video?,
        position: Int
    ) {
        val mediaContainer = activity?.findViewById<View>(R.id.media_container)

        val extras = FragmentNavigatorExtras((mediaContainer to "mediaContainer") as Pair<View, String>)
        val directions = VideoFragmentDirections.actionVideoFragmentToPlayerFragment(video!!,PlayerViewAdapter.getCurrentPlayerPosition(position)!!)
        findNavController().navigate(directions,extras)
   }

    private fun displayVideos(){
        Log.v("wasim_dev","displayVideos");

        binding.videoProgressBar.visibility = View.VISIBLE
        val responseLiveData = videoViewModel.getVideos()
        responseLiveData.observe(viewLifecycleOwner, Observer {
            Log.v("wasim_dev","displayVideos Observer");
            if(it!=null){
                adapter.setList(it)
                binding.videoProgressBar.visibility = View.GONE
            }else{
                binding.videoProgressBar.visibility = View.GONE
                Toast.makeText(activity,"No data available", Toast.LENGTH_LONG).show()
            }
        })
    }
}