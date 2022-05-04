package com.reza.appmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reza.appmovies.data.models.home.ResponseMoviesList
import com.reza.appmovies.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository) :
    ViewModel() {
    private val _moviesList = MutableLiveData<ResponseMoviesList>()
    val moviesList: LiveData<ResponseMoviesList>
        get() = _moviesList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean>
        get() = _empty

    fun loadSearchMovies(name: String) = viewModelScope.launch {
        _loading.postValue(true)
        val response = repository.searchMovies(name)
        if (response.isSuccessful) {
            if (response.body()?.data!!.isNotEmpty()) {
                _moviesList.postValue(response.body())
                _empty.postValue(false)
            } else {
                _empty.postValue(true)
            }
        }
        _loading.postValue(false)
    }

}