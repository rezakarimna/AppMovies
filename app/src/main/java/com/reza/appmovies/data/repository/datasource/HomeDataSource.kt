package com.reza.appmovies.data.repository.datasource

import com.reza.appmovies.data.models.home.ResponseMoviesList
import retrofit2.Response

interface HomeDataSource {
    suspend fun topMoviesList(id: Int): Response<ResponseMoviesList>
}