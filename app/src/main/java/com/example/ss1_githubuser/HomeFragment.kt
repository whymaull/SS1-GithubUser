package com.example.ss1_githubuser


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ss1_githubuser.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search_menu ->{
                    val searchFragment = SearchFragment()
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, searchFragment)
                        .addToBackStack(null)
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}