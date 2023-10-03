package com.example.ss1_githubuser.ui.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.ss1_githubuser.database.FavUser
import com.example.ss1_githubuser.reps.FavUserReps
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FavUserUpdateDeleteVMTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var applicationContext: Application

    @Mock
    private lateinit var githubUserRepository: FavUserReps

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        Mockito.`when`(applicationContext.applicationContext).thenReturn(applicationContext)

        Mockito.`when`(githubUserRepository.checkUser("username"))
            .thenReturn(MutableLiveData(FavUser(32131, "name", "avatarUrl")))
    }

    @Test
    fun insert() {
        val favUser = FavUser(32131, "name", "avatarUrl")

        synchronized(githubUserRepository) {
            githubUserRepository.insert(favUser)
        }
        Mockito.verify(githubUserRepository).insert(favUser)
    }
}