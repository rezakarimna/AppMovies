package com.reza.appmovies.data.api

import com.reza.appmovies.data.models.BodyRegister
import com.reza.appmovies.data.models.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun registerUser(@Body body: BodyRegister): Response<ResponseRegister>
}