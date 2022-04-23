package com.reza.appmovies.data.repository

import com.reza.appmovies.data.models.home.ResponseMoviesList
import retrofit2.Response

interface SearchRepository {
    suspend fun searchMovies(name:String): Response<ResponseMoviesList>
}