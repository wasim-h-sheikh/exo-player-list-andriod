package com.wasim.videosharedtransition.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.wasim.videosharedtransition.R
import com.wasim.videosharedtransition.presentation.video.VideoFragmentDirections

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val directions = VideoFragmentDirections.actionVideoFragmentSelf()
        findNavController(R.id.nav_host_fragment_content).navigate(directions)

    }

}