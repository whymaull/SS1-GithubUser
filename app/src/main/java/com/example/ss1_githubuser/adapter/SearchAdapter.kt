package com.example.ss1_githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ss1_githubuser.data.GithubUser
import com.example.ss1_githubuser.databinding.ListUserBinding

class SearchAdapter(private val listUser: List<GithubUser>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    private fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .circleCrop()
            .into(this)
    }

    class ViewHolder(var binding: ListUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listUser[position]

        with(holder.binding) {
            imgUserphoto.loadImage(user.avatarUrl)
            tvUsername.text = user.login
            tvUrl.text = user.htmlUrl
            root.setOnClickListener { onItemClickCallback.onItemClicked(listUser[position]) }
        }
    }

    override fun getItemCount(): Int = listUser.size

    fun setOnItemClickCallback(onItemClickCallback: Any) {
        this.onItemClickCallback = onItemClickCallback as OnItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: GithubUser)
    }
}