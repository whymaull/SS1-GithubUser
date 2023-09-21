package com.example.ss1_githubuser.ui.detail


import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ss1_githubuser.R
import com.example.ss1_githubuser.adapter.SearchAdapter
import com.example.ss1_githubuser.data.GithubUser
import com.example.ss1_githubuser.databinding.FragmentHomeBinding
import com.example.ss1_githubuser.tools.Loading
import com.example.ss1_githubuser.ui.viewmodel.MyViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val myViewModel  by viewModels<MyViewModel>()
    private val listUser = ArrayList<GithubUser>()
    private val loading = Loading()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvUser.layoutManager = layoutManager

        myViewModel.listGithubUser.observe(this) { listGithubUser ->
            setUserData(listGithubUser)
        }
        myViewModel.isLoading.observe(this) {
            loading.showLoading(it, binding.progressBar)
        }
        myViewModel.totalCount.observe(this) {
            showText(it)
        }
        myViewModel.status.observe(this) { status ->
            status?.let {
                Toast.makeText(context, status.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        initSearchView()
        //myViewModel.searchGithubUser(randomStartingList())
        return binding.root
    }

//    private fun randomStartingList(): String {
//        return "wahyu"
//    }

    private fun initSearchView() {
        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    binding.rvUser.visibility = View.VISIBLE
                    myViewModel.searchGithubUser(it)
                    setUserData(listUser)
                }
                hideKeyboard()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setUserData(listGithubUser: List<GithubUser>?) {
        listUser.clear()
        listGithubUser?.let { listUser.addAll(it) }
        val adapter = SearchAdapter(listUser)
        binding.rvUser.adapter = adapter

        adapter.setOnItemClickCallback(object : SearchAdapter.OnItemClickCallback{
            override fun onItemClicked(data: GithubUser) {
                showSelectedUser(data)
            }

        })
    }

    private fun showSelectedUser(data: GithubUser) {
        val detailFragment = DetailFragment.newInstance(data.login)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, detailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
        binding.searchView.setQuery("", false)
        binding.searchView.clearFocus()
    }

    private fun showText(totalCount: Int) {
        binding.apply {
            if (totalCount == 0) {
                tvNotice.visibility = View.VISIBLE
                tvNotice.text = resources.getString(R.string.user_not_found)
            } else {
                tvNotice.visibility = View.INVISIBLE
            }
        }
    }
    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.rvUser.windowToken, 0)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

