package com.example.ss1_githubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: FavModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application): FavModelFactory {
            if (INSTANCE == null) {
                synchronized(FavModelFactory::class.java) {
                    INSTANCE = FavModelFactory(application)
                }
            }
            return INSTANCE as FavModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavUserViewModel::class.java)) {
            return FavUserViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(FavUserUpdateViewModel::class.java)) {
            return FavUserUpdateViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}