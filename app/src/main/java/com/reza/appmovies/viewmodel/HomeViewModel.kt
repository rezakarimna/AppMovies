package com.reza.appmovies.viewmodel

import academy.nouri.s1_project.models.home.ResponseGenresList
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reza.appmovies.data.models.home.ResponseMoviesList
import com.reza.appmovies.data.repository.HomeRepository
import com.reza.appmovies.data.repository.HomeRepositoryImpl
import com.reza.appmovies.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repositoryImpl: HomeRepository) : ViewModel() {

    var topMovesListLiveData = MutableLiveData<ResponseMoviesList>()
    var lastMovesListLiveData = MutableLiveData<ResponseMoviesList>()
    var genresListLiveData = MutableLiveData<ResponseGenresList>()
    var loading = MutableLiveData<Boolean>()

    fun loadTopMoviesList(id: Int) = viewModelScope.launch {
        val response = repositoryImpl.topMoviesList(id)
        if (response.isSuccessful) {
            topMovesListLiveData.postValue(response.body())
        } else {
            Log.i(Constants.MOVIES_TABLE, "loadTopMoviesList: " + response.message())
        }
    }

    fun loadGenresList() = viewModelScope.launch {
        val response = repositoryImpl.genresList()
        if (response.isSuccessful) {
            genresListLiveData.postValue(response.body())
        } else {
            Log.i(Constants.MOVIES_TABLE, "loadTopMoviesList: " + response.message())
        }
    }

    fun loadLastMoviesList() = viewModelScope.launch {
        loading.postValue(true)
        val response = repositoryImpl.moviesLastList()
        if (response.isSuccessful) {
            lastMovesListLiveData.postValue(response.body())
        } else {
            Log.i(Constants.MOVIES_TABLE, "loadTopMoviesList: " + response.message())
        }
        loading.postValue(false)
    }
}