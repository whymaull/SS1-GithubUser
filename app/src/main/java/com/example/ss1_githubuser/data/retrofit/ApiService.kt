package com.example.ss1_githubuser.data.retrofit

import com.example.ss1_githubuser.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: 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")
    fun searchUser(
        @Query("q") username: String
    ): Call<GithubResponse>
}