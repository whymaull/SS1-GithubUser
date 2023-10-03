package com.example.ss1_githubuser.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ss1_githubuser.adapter.FavUserAdapter
import com.example.ss1_githubuser.database.FavUser
import com.example.ss1_githubuser.databinding.ActivityFavUserBinding
import com.example.ss1_githubuser.ui.fragment.DetailFragment
import com.example.ss1_githubuser.ui.fragment.HomeFragment
import com.example.ss1_githubuser.ui.viewmodel.FavModelFactory
import com.example.ss1_githubuser.ui.viewmodel.FavUserViewModel

class FavUserActivity : AppCompatActivity(), FavUserAdapter.OnItemClickListener {

    private var _binding: ActivityFavUserBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: FavUserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavUserBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val mainViewModel = obtainViewModel(this@FavUserActivity)
        mainViewModel.getAllFavorites().observe(this) { user ->
            if (user != null) {
                adapter.setFavorites(user)
            }
        }
        adapter = FavUserAdapter(this)
        binding?.rvFavorites?.layoutManager = LinearLayoutManager(this)
        binding?.rvFavorites?.setHasFixedSize(true)
        binding?.rvFavorites?.adapter = adapter

    }
    private fun obtainViewModel(activity: AppCompatActivity): FavUserViewModel {
        val factory = FavModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavUserViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(item: FavUser) {
        val username = item.username
        val intent = Intent(this@FavUserActivity, HomeFragment::class.java)
        intent.putExtra("username", username)
        startActivity(intent)
    }
}