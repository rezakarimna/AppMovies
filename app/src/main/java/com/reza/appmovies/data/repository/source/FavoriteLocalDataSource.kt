package com.reza.appmovies.data.repository.source

import com.reza.appmovies.data.db.MovieEntity
import com.reza.appmovies.data.db.MoviesDao
import javax.inject.Inject

class FavoriteLocalDataSource @Inject constructor(private val dao: MoviesDao) : FavoriteDataSource {
    override fun allFavoriteList(): MutableList<MovieEntity> = dao.getAllMovies()
}