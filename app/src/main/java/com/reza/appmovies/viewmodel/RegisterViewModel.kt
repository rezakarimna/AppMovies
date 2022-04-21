package com.reza.appmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reza.appmovies.data.models.BodyRegister
import com.reza.appmovies.data.models.ResponseRegister
import com.reza.appmovies.data.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerRepository: RegisterRepository) :
    ViewModel() {

    val registerUser = MutableLiveData<ResponseRegister>()
    val loading = MutableLiveData<Boolean>()

    fun sendRegisterUser(bodyRegister: BodyRegister) = viewModelScope.launch {
        loading.postValue(true)
        val response = registerRepository.registerUser(bodyRegister)
        if (response.isSuccessful) {
            registerUser.postValue(response.body())
        }
        loading.postValue(false)

    }
}