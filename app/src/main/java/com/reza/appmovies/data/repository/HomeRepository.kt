package com.reza.appmovies.data.repository

import com.reza.appmovies.data.models.home.ResponseMoviesList
import retrofit2.Response

interface HomeRepository {
    suspend fun topMoviesList(id: Int): Response<ResponseMoviesList>
}