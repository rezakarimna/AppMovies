package com.reza.appmovies.data.repository

import com.reza.appmovies.data.db.MovieEntity
import com.reza.appmovies.data.models.ResponseDetail
import com.reza.appmovies.data.repository.source.DetailMovieDataSource
import retrofit2.Response
import javax.inject.Inject

class DetailMovieRepositoryImpl @Inject constructor(private val dataSource: DetailMovieDataSource) :
    DetailMovieRepository {
    //remote
    override suspend fun detailMovie(id: Int): Response<ResponseDetail> =
        dataSource.detailMovie(id)

    //local
    override suspend fun insertMovie(entity: MovieEntity) = dataSource.insertMovie(entity)

    override suspend fun deleteMovie(entity: MovieEntity) = dataSource.deleteMovie(entity)

    override suspend fun existsMovie(id: Int): Boolean = dataSource.existsMovie(id)


}