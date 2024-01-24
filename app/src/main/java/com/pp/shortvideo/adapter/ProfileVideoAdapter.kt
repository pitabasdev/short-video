package com.pp.shortvideo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.pp.shortvideo.databinding.ProfileVideoItemVideoBinding
import com.pp.shortvideo.model.VideoModel

class ProfileVideoAdapter(options: FirestoreRecyclerOptions<VideoModel>)
    :FirestoreRecyclerAdapter<VideoModel,ProfileVideoAdapter.VideoViewHolder>(options){

    inner class VideoViewHolder(private val binding: ProfileVideoItemVideoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(video: VideoModel){
            Glide.with(binding.thumbnailImageView)
                .load(video.url)
                .into(binding.thumbnailImageView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding=ProfileVideoItemVideoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int, model: VideoModel) {
        holder.bind(model)
    }
}