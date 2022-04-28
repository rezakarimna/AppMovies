package com.reza.appmovies.data.repository

import com.reza.appmovies.data.db.MovieEntity
import com.reza.appmovies.data.models.ResponseDetail
import retrofit2.Response

interface DetailMovieRepository {
    //api
    suspend fun detailMovie(id:Int): Response<ResponseDetail>
    //Database
    suspend fun insertMovie(entity: MovieEntity)
    suspend fun deleteMovie(entity: MovieEntity)
    suspend fun existsMovie(id: Int) : Boolean
}