package com.reza.appmovies.data.repository.datasource

import com.reza.appmovies.data.api.ApiService
import com.reza.appmovies.data.models.home.ResponseMoviesList
import retrofit2.Response
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    SearchDataSource {
    override suspend fun searchMovies(name: String): Response<ResponseMoviesList> =
        apiService.searchMovies(name)


}