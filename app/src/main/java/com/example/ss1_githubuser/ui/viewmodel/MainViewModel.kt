package com.example.ss1_githubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ss1_githubuser.database.FavUser
import com.example.ss1_githubuser.reps.FavUserReps

class MainViewModel(application: Application) : ViewModel() {
    private val mNoteRepository: FavUserReps = FavUserReps(application)

    fun getAllFavorites(): LiveData<List<FavUser>> = mNoteRepository.getAllFavorites()
}