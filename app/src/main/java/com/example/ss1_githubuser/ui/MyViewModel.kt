package com.example.ss1_githubuser.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ss1_githubuser.data.response.GithubResponse
import com.example.ss1_githubuser.data.response.ItemsItem
import com.example.ss1_githubuser.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {
    
    private val _listUser = MutableLiveData<List<ItemsItem>>()
    val listReview: LiveData<List<ItemsItem>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MyViewModel"
    }

    init {
        fetchDataFromApi()
    }
    private fun fetchDataFromApi() {
        _isLoading.postValue(true) // Set isLoading to true while fetching data

        val apiService = ApiConfig.getApiService()
        val call = apiService.searchUser("abdul") // Ganti dengan username yang ingin dicari

        call.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                if (response.isSuccessful) {
                    val githubResponse = response.body()
                    val itemsList = githubResponse?.items ?: emptyList()
                    _listUser.postValue(itemsList as List<ItemsItem>?)
                } else {
                    // Handle API error here
                }

                _isLoading.postValue(false) // Set isLoading to false after fetching data
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                // Handle network error here
                _isLoading.postValue(false) // Set isLoading to false if there's an error
            }
        })
    }
}