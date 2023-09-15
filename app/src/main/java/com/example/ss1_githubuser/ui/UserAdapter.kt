package com.example.ss1_githubuser.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ss1_githubuser.data.response.ItemsItem
import com.example.ss1_githubuser.databinding.ListUserBinding

class UserAdapter : ListAdapter<ItemsItem, UserAdapter.MyViewModel>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewModel {
        val binding = ListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewModel(binding)
    }

    override fun onBindViewHolder(holder: MyViewModel, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }

    class MyViewModel(private val binding: ListUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: ItemsItem){
            binding.tvUsername.text= "${review.login}"
            Glide.with(binding.root)
                .load(review.avatarUrl)
                .into(binding.imgUserphoto)

            Log.i("Image", "${review.avatarUrl}")
        }

    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>(){
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}