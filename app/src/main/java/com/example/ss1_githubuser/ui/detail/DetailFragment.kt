package com.example.ss1_githubuser.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.ss1_githubuser.R
import com.example.ss1_githubuser.adapter.SectionPagerAdapter
import com.example.ss1_githubuser.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
//    private val TAB_TITLE = intArrayOf(
//        R.string.tab_1,
//        R.string.tab_2,
//    )

    companion object{
        private const val ARG_USERNAME = "username"
        const val EXTRA_USERNAME = "extra_username"

        fun newInstance(username: String): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putString(ARG_USERNAME, username)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val returnView = binding.root
        val username = arguments?.getString(ARG_USERNAME)
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        if (username != null) {
            // Lakukan sesuatu dengan username
            Toast.makeText(requireContext(), "Username: $username", Toast.LENGTH_SHORT).show()

            // Mendapatkan referensi ke TextView
            val tvName = view.findViewById<TextView>(R.id.tvDetailusername)

            // Mengatur teks TextView dengan nilai username
            tvName.text = username

        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up ViewPager and TabLayout here
        val sectionPagerAdapter = SectionPagerAdapter(requireActivity())
        val viewPager : ViewPager2 = view.findViewById(R.id.view_pager)

    }
//    private fun populateUserData(username: String) {
//        // Dapatkan data pengguna dari sumber data Anda (misalnya, ViewModel)
//        val userData = MyViewModel.getUserData(username)
//
//        // Setel foto profil
//        imageView.setImageResource(userData.profileImageResId) // Ganti dengan sumber gambar yang sesuai
//
//        // Setel username
//        tvDetailusername.text = userData.username
//
//        // Setel followers dan following
//        tvFollowers.text = getString(R.string.followers, userData.followersCount.toString())
//        tvFollowing.text = getString(R.string.following, userData.followingCount.toString())
//    }
}