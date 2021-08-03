package com.wasim.videosharedtransition.presentation.video

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.Player
import com.wasim.videosharedtransition.R
import com.wasim.videosharedtransition.data.model.Video
import com.wasim.videosharedtransition.databinding.VideoListItemBinding
import com.wasim.videosharedtransition.util.PlayerStateCallback
import com.wasim.videosharedtransition.util.PlayerViewAdapter.Companion.releaseRecycledPlayers


class VideoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    PlayerStateCallback {
    private val videoList = ArrayList<Video>()
    private var mItemClickListener: OnItemClickListener? =
        null

    fun setList(videos: List<Video>) {
        videoList.clear()
        videoList.addAll(videos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): VideoPlayerViewHolder {
        val binding: VideoListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context), R.layout.video_list_item, viewGroup, false
        )
        return VideoPlayerViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {

        //Here you can fill your row view
        if (holder is VideoPlayerViewHolder) {
            val model = getItem(position)
            val genericViewHolder = holder

            // send data to view holder
            genericViewHolder.onBind(model)
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        val position = holder.adapterPosition
        releaseRecycledPlayers(position)
        super.onViewRecycled(holder)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    private fun getItem(position: Int): Video {
        return videoList[position]
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(
            view: View?,
            position: Int,
            video: Video?
        )
    }

    inner class VideoPlayerViewHolder(private val binding: VideoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(video: Video) {
            // handel on item click
            binding.root.setOnClickListener {
                mItemClickListener!!.onItemClick(
                    it,
                    adapterPosition,
                    video
                )
            }

            binding.apply {
                dataModel = video
                callback = this@VideoAdapter
                index = adapterPosition
                executePendingBindings()
            }


        }
    }

    override fun onVideoDurationRetrieved(duration: Long, player: Player) {}

    override fun onVideoBuffering(player: Player) {}

    override fun onStartedPlaying(player: Player) {
        Log.d("playvideo", "start" + player.contentDuration)

    }


    override fun onFinishedPlaying(player: Player) {}
}