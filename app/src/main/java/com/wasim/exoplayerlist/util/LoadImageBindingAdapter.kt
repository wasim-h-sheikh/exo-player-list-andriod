package com.wasim.exoplayerlist.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.wasim.exoplayerlist.R

class LoadImageBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["thumbnail", "error"], requireAll = false)
        fun loadImage(view: ImageView, profileImage: String?, error: Int) {
            val fullUrl="http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/$profileImage"
            if (!profileImage.isNullOrEmpty()) {
                Glide.with(view.context)
                    .setDefaultRequestOptions(
                        RequestOptions()
                            .placeholder(R.drawable.white_background)
                            .error(R.drawable.white_background)
                    )
                    .load(fullUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(view)
            }
        }
    }
}