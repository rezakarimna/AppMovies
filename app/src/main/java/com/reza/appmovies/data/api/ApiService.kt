package com.reza.appmovies.data.api

import academy.nouri.s1_project.models.home.ResponseGenresList
import com.reza.appmovies.data.models.BodyRegister
import com.reza.appmovies.data.models.ResponseRegister
import com.reza.appmovies.data.models.home.ResponseMoviesList
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("register")
    suspend fun registerUser(@Body body: BodyRegister): Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun moviesTopList(@Path("genre_id") id: Int): Response<ResponseMoviesList>

    @GET("genres")
    suspend fun genresList(): Response<ResponseGenresList>

    @GET("movies")
    suspend fun moviesLastList(): Response<ResponseMoviesList>

    @GET("movies")
    suspend fun searchMovies(@Query("q") name:String): Response<ResponseMoviesList>
}