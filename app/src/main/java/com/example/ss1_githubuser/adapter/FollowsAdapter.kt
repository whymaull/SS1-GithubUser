package com.example.ss1_githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ss1_githubuser.data.GithubUser
import com.example.ss1_githubuser.databinding.ListFollowBinding

class FollowsAdapter(private val listFollow: List<GithubUser>) :
    RecyclerView.Adapter<FollowsAdapter.ViewHolder>() {
    class ViewHolder(var binding: ListFollowBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val follow = listFollow[position]

        with(holder.binding) {
            Glide.with(root.context)
                .load(follow.avatarUrl)
                .circleCrop()
                .into(imgUserphoto)
            tvUsername.text = follow.login
        }
    }
    override fun getItemCount(): Int = listFollow.size
}