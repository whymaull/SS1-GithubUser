package com.example.ss1_githubuser.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavUser::class], version = 5)
abstract class FavUserRoomDatabase : RoomDatabase() {
    abstract fun favoriteUserDao(): FavUserDao

    companion object {
        @Volatile
        private var INSTANCE: FavUserRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavUserRoomDatabase {
            if (INSTANCE == null) {
                synchronized(FavUserRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavUserRoomDatabase::class.java, "favorite_user_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as FavUserRoomDatabase
        }
    }
}