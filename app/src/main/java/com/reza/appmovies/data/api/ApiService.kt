package com.reza.appmovies.data.api

import com.reza.appmovies.data.models.BodyRegister
import com.reza.appmovies.data.models.ResponseRegister
import com.reza.appmovies.data.models.home.ResponseMoviesList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("register")
    suspend fun registerUser(@Body body: BodyRegister): Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun moviesTopList(@Path("genre_id") id: Int): Response<ResponseMoviesList>
}