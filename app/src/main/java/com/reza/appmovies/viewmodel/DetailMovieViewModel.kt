package com.reza.appmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reza.appmovies.data.db.MovieEntity
import com.reza.appmovies.data.models.ResponseDetail
import com.reza.appmovies.data.repository.DetailMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val repository: DetailMovieRepository) :
    ViewModel() {
    //Api
    private val _detailMovie = MutableLiveData<ResponseDetail>()
    val detailMovie: LiveData<ResponseDetail>
        get() = _detailMovie
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    //Database
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    fun loadDetailMovie(id: Int) = viewModelScope.launch {
        _loading.postValue(true)
        val response = repository.detailMovie(id)
        if (response.isSuccessful) {
            _detailMovie.postValue(response.body())
        }
        _loading.postValue(false)
    }


    suspend fun existsMovie(id: Int) =
        withContext(viewModelScope.coroutineContext) { repository.existsMovie(id) }

    fun favoriteMovie(id: Int, entity: MovieEntity) = viewModelScope.launch {
        val exists = repository.existsMovie(id)
        if (exists) {
            _isFavorite.postValue(false)
            repository.deleteMovie(entity)
        } else {
            _isFavorite.postValue(true)
            repository.insertMovie(entity)
        }
    }
}