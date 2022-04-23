package com.reza.appmovies.data.repository

import com.reza.appmovies.data.models.home.ResponseMoviesList
import com.reza.appmovies.data.repository.source.SearchDataSource
import retrofit2.Response
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchRemoteDataSource: SearchDataSource) :
    SearchRepository {
    override suspend fun searchMovies(name: String): Response<ResponseMoviesList> =
        searchRemoteDataSource.searchMovies(name)
}