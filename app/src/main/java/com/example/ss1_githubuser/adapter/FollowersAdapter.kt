package com.example.ss1_githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ss1_githubuser.data.GithubUser
import com.example.ss1_githubuser.databinding.ListUserBinding

class FollowersAdapter(private val listFollower: List<GithubUser>) : RecyclerView.Adapter<FollowersAdapter.ViewHolder>(){
    class ViewHolder(var binding: ListUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val follower = listFollower[position]

        with(holder.binding) {
            Glide.with(root.context)
                .load(follower.avatarUrl)
                .circleCrop()
                .into(imgUserphoto)
            tvUsername.text = follower.login
            //.text = follower.htmlUrl
        }
    }

    override fun getItemCount(): Int = listFollower.size
}