package com.reza.appmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reza.appmovies.data.db.MovieEntity
import com.reza.appmovies.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(val repository: FavoriteRepository) : ViewModel() {
    private val _favoriteList = MutableLiveData<MutableList<MovieEntity>>()
    val favoriteList: LiveData<MutableList<MovieEntity>>
        get() = _favoriteList

    private val _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean>
        get() = _empty

    fun loadFavoriteList() = viewModelScope.launch {
        val list = repository.allFavoriteList()
        if (list.isNotEmpty()) {
            _favoriteList.postValue(list)
            _empty.postValue(false)
        } else {
            _empty.postValue(true)
        }
    }
}