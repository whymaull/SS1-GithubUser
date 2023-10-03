package com.example.ss1_githubuser.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: FavUser)
    @Update
    fun update(user: FavUser)
    @Delete
    fun delete(user: FavUser)
    @Query("SELECT * from FavUser ORDER BY id ASC")
    fun getAllUser(): LiveData<List<FavUser>>

    @Query("SELECT * FROM FavUser WHERE id = :username")
    fun getFavoriteUserByUsername(username: String): LiveData<FavUser>

}