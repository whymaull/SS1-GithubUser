package com.example.ss1_githubuser.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.ss1_githubuser.R
import com.example.ss1_githubuser.adapter.SectionPagerAdapter
import com.example.ss1_githubuser.data.DetailResponse
import com.example.ss1_githubuser.databinding.FragmentDetailBinding
import com.example.ss1_githubuser.tools.Loading
import com.example.ss1_githubuser.ui.viewmodel.UserDetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding
    private lateinit var userDetailViewModel: UserDetailViewModel
    private lateinit var detailUser: DetailResponse
    private val loading = Loading()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        userDetailViewModel = ViewModelProvider(this)[UserDetailViewModel::class.java]

        val userLogin = arguments?.getString(ARG_USERNAME)
        if (userLogin != null) {
            userDetailViewModel.getGithubUser(userLogin)
            userDetailViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                binding?.let { loading.showLoading(isLoading, it.progressBar2) }
            }
            userDetailViewModel.listDetail.observe(viewLifecycleOwner) { detailList ->
                detailUser = detailList
                setDataToView()
                setTabLayoutView()

            }
        }
        return binding!!.root
    }

    private fun setDataToView() {
        binding?.apply {
            detailUser.avatarUrl?.let { imgDetailUser.loadImage(it) }
            tvDetailName.text = detailUser.name ?: getString(R.string.noname)
            tvDetailUsername.text = detailUser.login
            tvDetailFollowers.text = getString(R.string.follower, detailUser.followers)
            tvDetailFollowing.text = getString(R.string.following, detailUser.following)
            tvDetailReps.text = getString(R.string.repository, detailUser.publicRepos)
            tvLocation.text = detailUser.location ?: getString(R.string.nolocation)
        }
    }

    private fun setTabLayoutView() {
        val login = Bundle()
        login.putString(EXTRA_FRAGMENT, detailUser.login)
        val sectionPagerAdapter = SectionPagerAdapter(requireActivity(), login)
        val viewPager: ViewPager2 = binding!!.viewPager

        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = binding!!.tabs
        val tabTitle = resources.getStringArray(R.array.tab)

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        private const val ARG_USERNAME = "username"
        const val EXTRA_FRAGMENT = "extra_fragment"

        fun newInstance(username: String): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putString(ARG_USERNAME, username)
            fragment.arguments = args
            return fragment
        }
    }

}
