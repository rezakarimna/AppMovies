package com.reza.appmovies.data.repository

import com.reza.appmovies.data.api.ApiService
import com.reza.appmovies.data.models.home.ResponseMoviesList
import com.reza.appmovies.data.repository.datasource.HomeDataSource
import com.reza.appmovies.data.repository.datasource.HomeRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeRemoteDataSource: HomeDataSource) :
    HomeRepository {
    override suspend fun topMoviesList(id: Int): Response<ResponseMoviesList> =
        homeRemoteDataSource.topMoviesList(id)

    // suspend fun topMoviesList(id:Int) = apiService.moviesTopList(id)
}