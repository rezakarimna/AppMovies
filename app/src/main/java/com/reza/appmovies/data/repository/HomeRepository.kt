package com.reza.appmovies.data.repository

import academy.nouri.s1_project.models.home.ResponseGenresList
import com.reza.appmovies.data.models.home.ResponseMoviesList
import retrofit2.Response

interface HomeRepository {
    suspend fun topMoviesList(id: Int): Response<ResponseMoviesList>
    suspend fun genresList(): Response<ResponseGenresList>
    suspend fun moviesLastList(): Response<ResponseMoviesList>
}