package com.reza.appmovies.data.repository.source

import com.reza.appmovies.data.db.MovieEntity
import com.reza.appmovies.data.models.ResponseDetail
import retrofit2.Response

interface DetailMovieDataSource {
    suspend fun detailMovie(id:Int): Response<ResponseDetail>
    //Database
    suspend fun insertMovie(entity: MovieEntity)
    suspend fun deleteMovie(entity: MovieEntity)
    suspend fun existsMovie(id: Int) : Boolean
}