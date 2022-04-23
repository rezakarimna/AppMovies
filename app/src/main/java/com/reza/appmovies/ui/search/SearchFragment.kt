package com.reza.appmovies.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.reza.appmovies.R
import com.reza.appmovies.databinding.FragmentSearchBinding
import com.reza.appmovies.ui.home.LastMoviesAdapter
import com.reza.appmovies.utils.initRecycler
import com.reza.appmovies.utils.showInVisible
import com.reza.appmovies.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    //binding
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var searchAdapter: LastMoviesAdapter

    private val viewModel: SearchViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchMovies()
        observeMoviesList()
        setRecyclerViewMovies()
        observeLoading()
        observeEmptyList()
    }

    private fun searchMovies() {
        binding.searchEdt.doAfterTextChanged {
            val search = it.toString()
            if (search.isNotEmpty()) {
                callApiSearchMovies(search)
            }
        }
    }

    private fun callApiSearchMovies(nameMovies: String) {
        viewModel.loadSearchMovies(nameMovies)
    }

    private fun observeMoviesList() {
        viewModel.moviesList.observe(viewLifecycleOwner) {
            searchAdapter.setData(it.data)
        }
    }

    private fun setRecyclerViewMovies() {
        binding.moviesRecycler.initRecycler(LinearLayoutManager(requireContext()), searchAdapter)
    }

    private fun observeLoading() {
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.searchLoading.showInVisible(true)
            } else {
                binding.searchLoading.showInVisible(false)
            }
        }
    }

    private fun observeEmptyList() {
        binding.apply {
            viewModel.empty.observe(viewLifecycleOwner) {
                if (it) {
                    emptyItemsLay.showInVisible(true)
                    moviesRecycler.showInVisible(false)
                } else {
                    emptyItemsLay.showInVisible(false)
                    moviesRecycler.showInVisible(true)
                }
            }
        }

    }
}