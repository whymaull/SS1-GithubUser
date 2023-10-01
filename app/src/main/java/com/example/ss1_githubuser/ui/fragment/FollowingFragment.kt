package com.example.ss1_githubuser.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ss1_githubuser.adapter.FollowsAdapter
import com.example.ss1_githubuser.data.GithubUser
import com.example.ss1_githubuser.databinding.FragmentFollowsBinding
import com.example.ss1_githubuser.tools.Loading
import com.example.ss1_githubuser.ui.viewmodel.FollowingViewModel

class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowsBinding? = null
    private val binding get() = _binding
    private lateinit var followingViewModel: FollowingViewModel
    private val loading = Loading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FollowingViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followingViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding?.progressBar3?.let { loading.showLoading(isLoading, it) }
        }
        followingViewModel.listFollowing.observe(viewLifecycleOwner) { listFollower ->
            setDataToFragment(listFollower)
        }
        followingViewModel.status.observe(viewLifecycleOwner) { status ->
            status?.let {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        }
        followingViewModel.getFollowing(
            arguments?.getString(DetailFragment.EXTRA_FRAGMENT).toString()
        )
    }

    private fun setDataToFragment(listFollower: List<GithubUser>) {
        val listUser = ArrayList<GithubUser>()
        binding?.apply {
            for (user in listFollower) {
                listUser.clear()
                listUser.addAll(listFollower)
            }
            rvFollow.layoutManager = LinearLayoutManager(context)
            val adapter = FollowsAdapter(listFollower)
            rvFollow.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}