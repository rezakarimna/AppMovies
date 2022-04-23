package com.reza.appmovies.data.repository.source

import com.reza.appmovies.data.db.MovieEntity


interface FavoriteDataSource {
    fun allFavoriteList(): MutableList<MovieEntity>
}