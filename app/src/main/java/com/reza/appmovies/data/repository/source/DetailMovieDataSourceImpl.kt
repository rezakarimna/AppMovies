package com.reza.appmovies.data.repository.source

import com.reza.appmovies.data.api.ApiService
import com.reza.appmovies.data.db.MovieEntity
import com.reza.appmovies.data.db.MoviesDao
import com.reza.appmovies.data.models.ResponseDetail
import retrofit2.Response
import javax.inject.Inject

class DetailMovieDataSourceImpl @Inject constructor(private val apiService: ApiService , private val dao: MoviesDao) :
    DetailMovieDataSource {
    override suspend fun detailMovie(id: Int): Response<ResponseDetail> =
        apiService.detailMovie(id)

    override suspend fun insertMovie(entity: MovieEntity) = dao.insertMovie(entity)

    override suspend fun deleteMovie(entity: MovieEntity) = dao.deleteMovie(entity)

    override suspend fun existsMovie(id: Int): Boolean = dao.existsMovie(id)


}