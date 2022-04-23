package com.reza.appmovies.data.repository

import com.reza.appmovies.data.db.MovieEntity
import com.reza.appmovies.data.db.MoviesDao
import com.reza.appmovies.data.repository.source.FavoriteDataSource
import com.reza.appmovies.data.repository.source.FavoriteLocalDataSource
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val favoriteLocalDataSource: FavoriteDataSource) :
    FavoriteRepository {
    override fun allFavoriteList(): MutableList<MovieEntity> =
        favoriteLocalDataSource.allFavoriteList()
}