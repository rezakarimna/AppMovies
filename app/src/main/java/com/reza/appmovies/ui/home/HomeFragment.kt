package com.reza.appmovies.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.reza.appmovies.R
import com.reza.appmovies.databinding.FragmentHomeBinding
import com.reza.appmovies.utils.Constants
import com.reza.appmovies.utils.initRecycler
import com.reza.appmovies.utils.showInVisible
import com.reza.appmovies.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var topMoviesAdapter: TopMoviesAdapter

    @Inject
    lateinit var genresAdapter: GenresAdapter

    @Inject
    lateinit var lastMoviesAdapter: LastMoviesAdapter

    private val viewModel: HomeViewModel by viewModels()
    private val pagerSnapHelper: PagerSnapHelper by lazy { PagerSnapHelper() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //cal api
        viewModel.loadTopMoviesList(3)
        viewModel.loadGenresList()
        viewModel.loadLastMoviesList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Top movies
        observeTopMovieList()
        setRecyclerViewTopMovies()
        setIndicatorTopMovies()
        //Genres
        observeGenresList()
        setRecyclerViewGenres()
        //Last movies
        observeLastMovieList()
        setRecyclerViewLastMovies()
        //loading
        observeLoading()
    }

    private fun observeTopMovieList() {
        viewModel.topMovesListLiveData.observe(viewLifecycleOwner) {
            topMoviesAdapter.differ.submitList(it.data)
        }
    }

    private fun setRecyclerViewTopMovies() {
        binding.topMoviesRecycler.initRecycler(
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
            topMoviesAdapter
        )
    }

    private fun setIndicatorTopMovies() {
        binding.apply {
            pagerSnapHelper.attachToRecyclerView(topMoviesRecycler)
            topMoviesIndicator.attachToRecyclerView(
                topMoviesRecycler,
                pagerSnapHelper
            )
        }
    }

    private fun observeGenresList() {
        viewModel.genresListLiveData.observe(viewLifecycleOwner) {
            genresAdapter.differ.submitList(it)
        }
    }

    private fun setRecyclerViewGenres() {
        binding.genresRecycler.initRecycler(
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
            genresAdapter
        )
    }

    private fun observeLastMovieList() {
        viewModel.lastMovesListLiveData.observe(viewLifecycleOwner) {
            lastMoviesAdapter.setData(it.data)
        }
    }

    private fun setRecyclerViewLastMovies() {
        binding.lastMoviesRecycler.initRecycler(
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false),
            lastMoviesAdapter
        )
    }

    private fun observeLoading() {
        binding.apply {
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    moviesLoading.showInVisible(true)
                    moviesScrollLay.showInVisible(false)
                } else {
                    moviesLoading.showInVisible(false)
                    moviesScrollLay.showInVisible(true)
                }
            }
        }

    }


}