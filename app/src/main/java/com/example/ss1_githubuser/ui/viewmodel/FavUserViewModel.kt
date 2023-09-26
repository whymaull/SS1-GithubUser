package com.example.ss1_githubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ss1_githubuser.database.FavUser
import com.example.ss1_githubuser.reps.FavUserReps

class FavUserViewModel(application: Application) : ViewModel() {
    private val mFavoriteUserRepository: FavUserReps = FavUserReps(application)

    fun getAllFavorites(): LiveData<List<FavUser>> = mFavoriteUserRepository.getAllFavorites()
}