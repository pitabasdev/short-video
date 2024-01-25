package com.pp.shortvideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pp.shortvideo.adapter.VideoListAdapter
import com.pp.shortvideo.databinding.ActivitySingleVideoPlayerBinding
import com.pp.shortvideo.databinding.ProfileVideoItemVideoBinding
import com.pp.shortvideo.model.VideoModel

class SingleVideoPlayerActivity : AppCompatActivity() {
    lateinit var binding: ActivitySingleVideoPlayerBinding
    lateinit var videoId: String
    lateinit var adapter: VideoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySingleVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        videoId=intent.getStringExtra("videoId")!!
        setViewPager()

    }
    fun setViewPager(){
        val options=FirestoreRecyclerOptions.Builder<VideoModel>()
            .setQuery(
                Firebase.firestore.collection("videos")
                    .whereEqualTo("videoId",videoId),
                VideoModel::class.java
            ).build()
        adapter= VideoListAdapter(options)
        binding.videPager.adapter=adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.stopListening()
    }

}