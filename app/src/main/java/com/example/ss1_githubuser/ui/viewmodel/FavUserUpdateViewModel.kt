package com.example.ss1_githubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ss1_githubuser.database.FavUser
import com.example.ss1_githubuser.reps.FavUserReps

class FavUserUpdateViewModel (application: Application) : ViewModel() {

    private var mFavUserReps: FavUserReps = FavUserReps(application)
    fun insert(user: FavUser) {
        mFavUserReps.insert(user)
    }
    fun update(user: FavUser) {
        mFavUserReps.update(user)
    }
    fun delete(user: FavUser) {
        mFavUserReps.delete(user)
    }
    fun checkUser(username: String): LiveData<FavUser> {
        return mFavUserReps.checkUser(username)
    }
}