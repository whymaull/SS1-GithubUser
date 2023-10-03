package com.example.ss1_githubuser.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.ss1_githubuser.R
import com.example.ss1_githubuser.adapter.SectionPagerAdapter
import com.example.ss1_githubuser.data.DetailResponse
import com.example.ss1_githubuser.database.FavUser
import com.example.ss1_githubuser.databinding.FragmentDetailBinding
import com.example.ss1_githubuser.tools.Loading
import com.example.ss1_githubuser.tools.loadImage
import com.example.ss1_githubuser.ui.activity.FavUserActivity
import com.example.ss1_githubuser.ui.activity.SettingsActivity
import com.example.ss1_githubuser.ui.viewmodel.FavUserUpdateViewModel
import com.example.ss1_githubuser.ui.viewmodel.UserDetailViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

@Suppress("DEPRECATION")
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var userDetailViewModel: UserDetailViewModel
    private lateinit var detailUser: DetailResponse
    private val loading = Loading()
    private var isFavorite = false

    private val favUserUpdateViewModel by lazy {
        FavUserUpdateViewModel(requireActivity().application)
    }

    private lateinit var fabAdd: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        userDetailViewModel = ViewModelProvider(this)[UserDetailViewModel::class.java]

        val toolbar = binding.toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        val userLogin = arguments?.getString(ARG_USERNAME)
        if (userLogin != null) {
            userDetailViewModel.getGithubUser(userLogin)
            userDetailViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                binding.let { loading.showLoading(isLoading, it.progressBar2) }
            }
            userDetailViewModel.listDetail.observe(viewLifecycleOwner) { detailList ->
                detailUser = detailList
                setDataToView()
                setTabLayoutView()
                fabAdd = binding.fabFavorite

                favUserUpdateViewModel.checkUser("${detailUser.id}")
                    .observe(viewLifecycleOwner) { user ->
                        isFavorite = user != null
                        Log.i("DetailFragment", "onResponse: user $isFavorite ")
                        Log.i("DetailFragment", "onResponse: User ${detailUser.avatarUrl} ")

                        if (isFavorite){
                            fabAdd.setImageResource(R.drawable.baseline_favorite_black)

                        }else{
                            fabAdd.setImageResource(R.drawable.baseline_favorite_border_24)

                        }

                    }

                fabAdd.setOnClickListener {
                    val favUser =    FavUser(
                        id = detailUser.id!!,
                        username = detailUser.login,
                        fullName = detailUser.name,
                        imgUrl = detailUser.avatarUrl
                    )
                    Log.i("DetailFragment", "onCreateView: ${favUser.id} ${favUser.username} ${favUser.imgUrl}  ")

                    if (isFavorite) {
                        favUserUpdateViewModel.delete(favUser)
                        fabAdd.setImageResource(R.drawable.baseline_favorite_border_24)
                        showToast(getString(R.string.delete))
                    } else {
                        favUserUpdateViewModel.insert(favUser)
                        fabAdd.setImageResource(R.drawable.baseline_favorite_black)
                        showToast(getString(R.string.added))
                    }
                }
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }


    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.theme_settings -> {
                // ke Settings Activity
                val settingsIntent = Intent(requireContext(), SettingsActivity::class.java)
                startActivity(settingsIntent)
                true
            }

            R.id.favorites -> {
                // ke Favorites Activity
                val favIntent = Intent(requireContext(), FavUserActivity::class.java)
                startActivity(favIntent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setDataToView() {
        binding.apply {
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
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionPagerAdapter

        val tabs: TabLayout = binding.tabs
        val tabTitle = resources.getStringArray(R.array.tab)

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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
