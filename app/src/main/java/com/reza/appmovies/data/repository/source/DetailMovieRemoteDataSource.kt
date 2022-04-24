package com.reza.appmovies.data.repository.source

import com.reza.appmovies.data.api.ApiService
import com.reza.appmovies.data.models.ResponseDetail
import retrofit2.Response
import javax.inject.Inject

class DetailMovieRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    DetailMovieDataSource {
    override suspend fun detailMovie(id: Int): Response<ResponseDetail> =
        apiService.detailMovie(id)


}