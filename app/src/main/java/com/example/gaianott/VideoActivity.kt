package com.example.gaianott

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.FragmentActivity

class VideoActivity : FragmentActivity() {

    lateinit var btnBack: ImageView
    lateinit var videoView: VideoView
    lateinit var mediaController: MediaController
    var movieUrl : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        btnBack = findViewById(R.id.btn_back)
        videoView = findViewById(R.id.vv_video)

//        movieUrl = intent.extras?.getString("movieurl").toString()

        mediaController = MediaController(this)
        mediaController.setAnchorView(this.videoView)

        videoView.setMediaController(mediaController)
        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.demo_vid))
        videoView.requestFocus()
        videoView.start()

        videoView.setOnCompletionListener {
            Toast.makeText(applicationContext, "Video completed",
                Toast.LENGTH_LONG).show()
            true
        }

        // display a toast message if any
        // error occurs while playing the video
        videoView.setOnErrorListener { mp, what, extra ->
            Toast.makeText(applicationContext, "An Error Occurred", Toast.LENGTH_LONG).show()
            false
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}