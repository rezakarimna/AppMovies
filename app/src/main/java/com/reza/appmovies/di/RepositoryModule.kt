package com.reza.appmovies.di

import com.reza.appmovies.data.api.ApiService
import com.reza.appmovies.data.db.MoviesDao
import com.reza.appmovies.data.repository.*
import com.reza.appmovies.data.repository.source.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideHomeRepository(homeRemoteDataSource: HomeDataSource): HomeRepository =
        HomeRepositoryImpl(homeRemoteDataSource)

    @Provides
    @Singleton
    fun provideHomeDataSource(apiService: ApiService): HomeDataSource =
        HomeRemoteDataSource(apiService)

    @Provides
    @Singleton
    fun provideSearchRepository(searchRemoteDataSource: SearchDataSource): SearchRepository =
        SearchRepositoryImpl(searchRemoteDataSource)

    @Provides
    @Singleton
    fun provideSearchDataSource(apiService: ApiService): SearchDataSource =
        SearchRemoteDataSource(apiService)

    @Provides
    @Singleton
    fun provideFavoriteRepository(favoriteLocalDataSource: FavoriteDataSource): FavoriteRepository =
        FavoriteRepositoryImpl(favoriteLocalDataSource)

    @Provides
    @Singleton
    fun provideFavoriteDataSource(dao: MoviesDao): FavoriteDataSource =
        FavoriteLocalDataSource(dao)

    @Provides
    @Singleton
    fun provideDetailMovieRepository(detailMovieDataSource: DetailMovieDataSource , localDataSource: DetailMovieLocalDataSource): DetailMovieRepository =
        DetailMovieRepositoryImpl(detailMovieDataSource , localDataSource)

    @Provides
    @Singleton
    fun provideDetailMovieLocalDataSource(dao: MoviesDao) =
        DetailMovieLocalDataSource(dao)

    @Provides
    @Singleton
    fun provideDetailMovieRemoteDataSource(apiService: ApiService): DetailMovieDataSource =
        DetailMovieRemoteDataSource(apiService)
}

