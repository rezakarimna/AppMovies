package com.reza.appmovies.viewmodel

import academy.nouri.s1_project.models.home.ResponseGenresList
import android.util.Log
import androidx.lifecycle.LiveData
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

    private var _topMovesListLiveData = MutableLiveData<ResponseMoviesList>()
    val topMovesListLiveData: LiveData<ResponseMoviesList>
        get() = _topMovesListLiveData

    private var _lastMovesListLiveData = MutableLiveData<ResponseMoviesList>()
    val lastMovesListLiveData: LiveData<ResponseMoviesList>
        get() = _lastMovesListLiveData

    private var _genresListLiveData = MutableLiveData<ResponseGenresList>()
    val genresListLiveData: LiveData<ResponseGenresList>
        get() = _genresListLiveData

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun loadTopMoviesList(id: Int) = viewModelScope.launch {
        val response = repositoryImpl.topMoviesList(id)
        if (response.isSuccessful) {
            _topMovesListLiveData.postValue(response.body())
        } else {
            Log.i(Constants.MOVIES_TABLE, "loadTopMoviesList: " + response.message())
        }
    }

    fun loadGenresList() = viewModelScope.launch {
        val response = repositoryImpl.genresList()
        if (response.isSuccessful) {
            _genresListLiveData.postValue(response.body())
        } else {
            Log.i(Constants.MOVIES_TABLE, "loadTopMoviesList: " + response.message())
        }
    }

    fun loadLastMoviesList() = viewModelScope.launch {
        _loading.postValue(true)
        val response = repositoryImpl.moviesLastList()
        if (response.isSuccessful) {
            _lastMovesListLiveData.postValue(response.body())
        } else {
            Log.i(Constants.MOVIES_TABLE, "loadTopMoviesList: " + response.message())
        }
        _loading.postValue(false)
    }
}