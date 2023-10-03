package com.example.ss1_githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ss1_githubuser.database.FavUser
import com.example.ss1_githubuser.databinding.ListUserBinding
import com.example.ss1_githubuser.tools.FavDiffCallback

class FavUserAdapter(private val onItemClickListener: FavUserAdapter.OnItemClickListener) : RecyclerView.Adapter<FavUserAdapter.FavoriteUserViewHolder>() {
    private val listFavorites = ArrayList<FavUser>()

    fun setFavorites(favorites: List<FavUser>) {
        val diffCallback = FavDiffCallback(this.listFavorites, favorites)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFavorites.clear()
        this.listFavorites.addAll(favorites)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteUserViewHolder {
        val itemRowUserBinding = ListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteUserViewHolder(itemRowUserBinding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: FavoriteUserViewHolder, position: Int) {
        val favorites = listFavorites[position]
        holder.bind(favorites)
    }

    override fun getItemCount(): Int = listFavorites.size

    inner class FavoriteUserViewHolder(private val binding: ListUserBinding, private val  onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: FavUser) {
            with(binding) {
                tvUsername.text = user.username
                tvUrl.text = user.fullName
                Glide.with(binding.root.context)
                    .load(user.imgUrl)
                    .circleCrop()
                    .into(binding.imgUserphoto)

                itemView.setOnClickListener {
                    onItemClickListener.onItemClick(user)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: FavUser)
    }
}