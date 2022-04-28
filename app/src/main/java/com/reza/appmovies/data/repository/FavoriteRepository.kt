package com.reza.appmovies.data.repository

import com.reza.appmovies.data.db.MovieEntity


interface FavoriteRepository {
    fun allFavoriteList(): MutableList<MovieEntity>

}