package com.example.ss1_githubuser.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(user: FavUser)

    @Query("DELETE FROM FavUser WHERE FavUser.id = :id")
    fun removeFavorite(id: Int)

    @Query("SELECT * FROM FavUser ORDER BY login ASC")
    fun getAllUser(): LiveData<List<FavUser>>

    @Query("SELECT * FROM FavUser WHERE FavUser.id = :id")
    fun getUserById(id: Int): LiveData<FavUser>

}