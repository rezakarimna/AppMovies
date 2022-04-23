package com.reza.appmovies.data.repository

import academy.nouri.s1_project.models.home.ResponseGenresList
import com.reza.appmovies.data.models.home.ResponseMoviesList
import com.reza.appmovies.data.repository.source.HomeDataSource
import retrofit2.Response
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeRemoteDataSource: HomeDataSource) :
    HomeRepository {
    override suspend fun topMoviesList(id: Int): Response<ResponseMoviesList> =
        homeRemoteDataSource.topMoviesList(id)

    override suspend fun genresList(): Response<ResponseGenresList> =
        homeRemoteDataSource.genresList()

    override suspend fun moviesLastList(): Response<ResponseMoviesList> =
        homeRemoteDataSource.moviesLastList()
}