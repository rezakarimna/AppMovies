package com.reza.appmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reza.appmovies.data.models.home.ResponseMoviesList
import com.reza.appmovies.data.repository.HomeRepository
import com.reza.appmovies.data.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repositoryImpl: HomeRepository) : ViewModel() {

    var topMovesListLiveData = MutableLiveData<ResponseMoviesList>()

    fun loadTopMoviesList(id: Int) = viewModelScope.launch {
        val response = repositoryImpl.topMoviesList(id)
        if (response.isSuccessful) {
            topMovesListLiveData.postValue(response.body())
        }

    }
}