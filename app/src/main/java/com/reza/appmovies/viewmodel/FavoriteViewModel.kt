package com.reza.appmovies.viewmodel

import androidx.lifecycle.ViewModel
import com.reza.appmovies.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(val repository: FavoriteRepository) : ViewModel() {

}