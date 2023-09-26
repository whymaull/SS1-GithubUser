package com.example.ss1_githubuser.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavUser(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "login")
    var login: String?,

    @ColumnInfo(name = "html_url")
    var htmlUrl: String? = null,

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String? = null,
)