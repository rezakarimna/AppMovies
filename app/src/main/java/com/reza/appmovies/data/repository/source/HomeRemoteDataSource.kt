package com.reza.appmovies.data.repository.source

import com.reza.appmovies.data.api.ApiService
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    HomeDataSource {
    override suspend fun topMoviesList(id: Int) = apiService.moviesTopList(id)
    override suspend fun genresList() = apiService.genresList()
    override suspend fun moviesLastList() = apiService.moviesLastList()


}