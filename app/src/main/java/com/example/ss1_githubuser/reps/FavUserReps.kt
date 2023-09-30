package com.example.ss1_githubuser.reps

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.ss1_githubuser.database.FavUser
import com.example.ss1_githubuser.database.FavUserDao
import com.example.ss1_githubuser.database.FavUserRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavUserReps(application: Application) {
    private val mFavoriteUserDao: FavUserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavUserRoomDatabase.getDatabase(application)
        mFavoriteUserDao = db.favoriteUserDao()
    }

    fun getAllFavorites(): LiveData<List<FavUser>> = mFavoriteUserDao.getAllUser()

    fun insert(user: FavUser) {
        executorService.execute { mFavoriteUserDao.insert(user) }
    }
    fun delete(user: FavUser) {
        executorService.execute { mFavoriteUserDao.delete(user) }
    }
    fun update(user: FavUser) {
        executorService.execute { mFavoriteUserDao.update(user) }
    }
}