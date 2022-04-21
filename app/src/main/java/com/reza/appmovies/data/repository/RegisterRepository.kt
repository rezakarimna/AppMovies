package com.reza.appmovies.data.repository

import com.reza.appmovies.data.api.ApiService
import com.reza.appmovies.data.models.BodyRegister
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun registerUser(bodyRegister: BodyRegister) = apiService.registerUser(bodyRegister)
}