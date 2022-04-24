package com.reza.appmovies.data.repository

import com.reza.appmovies.data.models.ResponseDetail
import com.reza.appmovies.data.repository.source.DetailMovieDataSource
import retrofit2.Response
import javax.inject.Inject

class DetailMovieRepositoryImpl @Inject constructor(private val remoteDataSource: DetailMovieDataSource) :
    DetailMovieRepository {
    override suspend fun detailMovie(id: Int): Response<ResponseDetail> =
        remoteDataSource.detailMovie(id)


}