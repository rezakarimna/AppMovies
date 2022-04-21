package com.reza.appmovies.data.repository.datasource

import com.reza.appmovies.data.api.ApiService
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    HomeDataSource {
    override suspend fun topMoviesList(id: Int) = apiService.moviesTopList(id)
}