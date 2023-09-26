package com.example.ss1_githubuser.tools

import androidx.recyclerview.widget.DiffUtil
import com.example.ss1_githubuser.database.FavUser

class FavDiffCallback(private val mOldFavList: List<FavUser>, private val mNewFavList: List<FavUser>):
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldFavList.size
    }

    override fun getNewListSize(): Int {
        return mNewFavList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldFavList[oldItemPosition].id == mNewFavList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFavList = mOldFavList[oldItemPosition]
        val newFavList = mNewFavList[newItemPosition]
        return oldFavList.login == newFavList.login && oldFavList.htmlUrl == newFavList.htmlUrl && oldFavList.avatarUrl == newFavList.avatarUrl
    }


}