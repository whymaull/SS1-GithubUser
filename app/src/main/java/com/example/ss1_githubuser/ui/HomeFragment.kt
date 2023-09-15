package com.example.ss1_githubuser.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ss1_githubuser.R
import com.example.ss1_githubuser.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyViewModel by viewModels()
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val returnView = binding.root
        val recyclerView = returnView.findViewById<RecyclerView>(R.id.rvUser)
        adapter = UserAdapter()

        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.listReview.observe(viewLifecycleOwner){items ->
            adapter.submitList(items)
        }
        with(binding){
            searchView.setupWithSearchBar(searchBar)
        }
        binding.searchBar.inflateMenu(R.menu.option_menu)
        binding.searchBar.setOnMenuItemClickListener{menuItem ->
            when (menuItem.itemId) {
                R.id.share -> {
//                    val searchFragment = SearchFragment()
//                    requireActivity().supportFragmentManager.beginTransaction()
//                        .replace(R.id.container, searchFragment)
//                        .addToBackStack(null)
//                        .commit()
                    true
                }

                else -> false
            }

        }

        return returnView
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}