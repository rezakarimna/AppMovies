package com.reza.appmovies.data.repository.source

import com.reza.appmovies.data.models.home.ResponseMoviesList
import retrofit2.Response

interface SearchDataSource {
    suspend fun searchMovies(name:String): Response<ResponseMoviesList>
}