package com.reza.appmovies.data.repository.datasource

import com.reza.appmovies.data.api.ApiService
import com.reza.appmovies.data.models.home.ResponseMoviesList
import retrofit2.Response
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    HomeDataSource {
    override suspend fun topMoviesList(id: Int) = apiService.moviesTopList(id)
    override suspend fun genresList() = apiService.genresList()
    override suspend fun moviesLastList() = apiService.moviesLastList()


}