package com.reza.appmovies.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.reza.appmovies.R
import com.reza.appmovies.databinding.FragmentFavoriteBinding
import com.reza.appmovies.ui.home.HomeFragmentDirections
import com.reza.appmovies.utils.initRecycler
import com.reza.appmovies.utils.showInVisible
import com.reza.appmovies.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentFavoriteBinding

    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    //Other
    private val viewModel: FavoriteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllFavoriteMovies()
        observeListFavoriteMovies()
        setFavoriteRecycler()
        observeEmptyList()
        moveToDetailFragment()
    }

    private fun getAllFavoriteMovies() {
        viewModel.loadFavoriteList()
    }

    private fun observeListFavoriteMovies() {
        viewModel.favoriteList.observe(viewLifecycleOwner) {
            favoriteAdapter.setData(it)
        }
    }

    private fun setFavoriteRecycler() {
        binding.favoriteRecycler.initRecycler(
            LinearLayoutManager(requireContext()),
            favoriteAdapter
        )
    }

    private fun observeEmptyList() {
        binding.apply {
            viewModel.empty.observe(viewLifecycleOwner) {
                if (it) {
                    emptyItemsLay.showInVisible(true)
                    favoriteRecycler.showInVisible(false)
                } else {
                    emptyItemsLay.showInVisible(false)
                    favoriteRecycler.showInVisible(true)
                }
            }
        }

    }

    private fun moveToDetailFragment() {
        favoriteAdapter.setOnItemClickListener {
            val directions = HomeFragmentDirections.actionToDetail(it.id)
            findNavController().navigate(directions)
        }
    }
}