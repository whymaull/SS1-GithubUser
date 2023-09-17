package com.example.ss1_githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ss1_githubuser.data.GithubUser
import com.example.ss1_githubuser.databinding.ListUserBinding

class FollowingAdapter(private val listFollowing: List<GithubUser>) : RecyclerView.Adapter<FollowingAdapter.ViewHolder>(){
    class ViewHolder(var binding: ListUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val following = listFollowing[position]

        with(holder.binding) {
            com.bumptech.glide.Glide.with(root.context)
                .load(following.avatarUrl)
                .circleCrop()
                .into(imgUserphoto)
            tvUsername.text = following.login
            //tvUrl.text = following.htmlUrl
        }
    }

    override fun getItemCount(): Int = listFollowing.size
}