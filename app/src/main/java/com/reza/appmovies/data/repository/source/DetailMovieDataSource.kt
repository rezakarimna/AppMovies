package com.reza.appmovies.data.repository.source

import com.reza.appmovies.data.models.ResponseDetail
import retrofit2.Response

interface DetailMovieDataSource {
    suspend fun detailMovie(id:Int): Response<ResponseDetail>
}