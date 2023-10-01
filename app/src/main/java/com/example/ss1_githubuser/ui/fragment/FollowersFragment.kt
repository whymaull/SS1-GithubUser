package com.example.ss1_githubuser.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ss1_githubuser.adapter.FollowsAdapter
import com.example.ss1_githubuser.data.GithubUser
import com.example.ss1_githubuser.databinding.FragmentFollowsBinding
import com.example.ss1_githubuser.tools.Loading
import com.example.ss1_githubuser.ui.viewmodel.FollowersViewModel


class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowsBinding? = null
    private val binding get() = _binding
    private lateinit var followersViewModel: FollowersViewModel
    private val loading = Loading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FollowersViewModel::class.java]
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

        followersViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding?.progressBar3?.let { loading.showLoading(isLoading, it) }
        }
        followersViewModel.listFollow.observe(viewLifecycleOwner) { listFollower ->
            setDataToFragment(listFollower)
        }
        followersViewModel.status.observe(viewLifecycleOwner) { status ->
            status?.let {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        }
        followersViewModel.getFollowers(
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

