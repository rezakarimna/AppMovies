package com.reza.appmovies.data.repository

import com.reza.appmovies.data.models.ResponseDetail
import retrofit2.Response

interface DetailMovieRepository {
    suspend fun detailMovie(id:Int): Response<ResponseDetail>
}