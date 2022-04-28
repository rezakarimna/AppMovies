package com.reza.appmovies.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.reza.appmovies.R
import com.reza.appmovies.data.db.MovieEntity
import com.reza.appmovies.data.models.ResponseDetail
import com.reza.appmovies.databinding.FragmentDetailBinding
import com.reza.appmovies.utils.initRecycler
import com.reza.appmovies.utils.showInVisible
import com.reza.appmovies.viewmodel.DetailMovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentDetailBinding

    @Inject
    lateinit var imagesAdapter: ImagesAdapter

    @Inject
    lateinit var entity: MovieEntity

    //Other
    private var movieID = 0
    private val viewModel: DetailMovieViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Get data
        movieID = args.movieID
        //Call api
        if (movieID > 0) {
            viewModel.loadDetailMovie(movieID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLoading()
        observeDetailMovie()
        defaultFavIconColor()
        observerChangeFavIcon()
        onBack()
    }

    private fun observeLoading() {
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.detailLoading.showInVisible(true)
                binding.detailScrollView.showInVisible(false)
            } else {
                binding.detailLoading.showInVisible(false)
                binding.detailScrollView.showInVisible(true)
            }
        }
    }

    private fun observeDetailMovie() {
        viewModel.detailMovie.observe(viewLifecycleOwner) {
            loadDataAndInitView(it)
            setRecyclerAndAdapter(it)
            favImageClick(it)
        }
    }

    private fun favImageClick(responseDetail: ResponseDetail) {
        binding.favImg.setOnClickListener {
            entity.id = movieID
            entity.poster = responseDetail.poster.toString()
            entity.title = responseDetail.title.toString()
            entity.rate = responseDetail.rated.toString()
            entity.country = responseDetail.country.toString()
            entity.year = responseDetail.year.toString()
            viewModel.favoriteMovie(movieID, entity)
        }
    }

    private fun loadDataAndInitView(responseDetail: ResponseDetail) {
        binding.apply {
            posterBigImg.load(responseDetail.poster)
            posterNormalImg.load(responseDetail.poster) {
                crossfade(true)
                crossfade(800)
            }
            movieNameTxt.text = responseDetail.title
            movieRateTxt.text = responseDetail.imdbRating
            movieTimeTxt.text = responseDetail.runtime
            movieDateTxt.text = responseDetail.released
            movieSummaryInfo.text = responseDetail.plot
            movieActorsInfo.text = responseDetail.actors
        }
    }

    private fun setRecyclerAndAdapter(responseDetail: ResponseDetail) {
        imagesAdapter.differ.submitList(responseDetail.images)
        binding.imagesRecyclerView.initRecycler(
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
            imagesAdapter
        )
    }

    private fun defaultFavIconColor() {
        lifecycleScope.launchWhenCreated {
            if (viewModel.existsMovie(movieID)) {
                binding.favImg.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.scarlet
                    )
                )
            } else {
                binding.favImg.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.philippineSilver
                    )
                )
            }
        }
    }

    private fun observerChangeFavIcon() {
        viewModel.isFavorite.observe(viewLifecycleOwner) {
            if (it) {
                binding.favImg.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.scarlet
                    )
                )
            } else {
                binding.favImg.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.philippineSilver
                    )
                )
            }
        }
    }

    private fun onBack() {
        binding.backImg.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}