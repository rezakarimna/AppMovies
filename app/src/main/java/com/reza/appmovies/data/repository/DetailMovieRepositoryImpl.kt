package com.reza.appmovies.data.repository

import com.reza.appmovies.data.db.MovieEntity
import com.reza.appmovies.data.models.ResponseDetail
import com.reza.appmovies.data.repository.source.DetailMovieDataSource
import com.reza.appmovies.data.repository.source.DetailMovieLocalDataSource
import retrofit2.Response
import javax.inject.Inject

class DetailMovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: DetailMovieDataSource,
    private val localDataSource: DetailMovieLocalDataSource
) :
    DetailMovieRepository {
    //remote
    override suspend fun detailMovie(id: Int): Response<ResponseDetail> =
        remoteDataSource.detailMovie(id)

    //local
    override suspend fun insertMovie(entity: MovieEntity) = localDataSource.insertMovie(entity)

    override suspend fun deleteMovie(entity: MovieEntity) = localDataSource.deleteMovie(entity)

    override suspend fun existsMovie(id: Int): Boolean = localDataSource.existsMovie(id)


}