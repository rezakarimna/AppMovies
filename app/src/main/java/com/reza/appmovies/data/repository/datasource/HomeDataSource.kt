package com.reza.appmovies.data.repository.datasource

import academy.nouri.s1_project.models.home.ResponseGenresList
import com.reza.appmovies.data.models.home.ResponseMoviesList
import retrofit2.Response

interface HomeDataSource {
    suspend fun topMoviesList(id: Int): Response<ResponseMoviesList>

    suspend fun genresList(): Response<ResponseGenresList>

    suspend fun moviesLastList(): Response<ResponseMoviesList>
}