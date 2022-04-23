package com.reza.appmovies.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.reza.appmovies.R
import com.reza.appmovies.databinding.FragmentSearchBinding
import com.reza.appmovies.ui.home.LastMoviesAdapter
import com.reza.appmovies.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    //binding
    lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var lastMoviesAdapter: LastMoviesAdapter

    private val viewModel: SearchViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}