package com.reza.appmovies.di

import com.reza.appmovies.data.api.ApiService
import com.reza.appmovies.data.repository.HomeRepository
import com.reza.appmovies.data.repository.HomeRepositoryImpl
import com.reza.appmovies.data.repository.SearchRepository
import com.reza.appmovies.data.repository.SearchRepositoryImpl
import com.reza.appmovies.data.repository.datasource.HomeDataSource
import com.reza.appmovies.data.repository.datasource.HomeRemoteDataSource
import com.reza.appmovies.data.repository.datasource.SearchDataSource
import com.reza.appmovies.data.repository.datasource.SearchRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
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
}