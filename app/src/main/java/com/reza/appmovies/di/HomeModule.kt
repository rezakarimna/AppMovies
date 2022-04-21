package com.reza.appmovies.di

import com.reza.appmovies.data.api.ApiService
import com.reza.appmovies.data.repository.HomeRepository
import com.reza.appmovies.data.repository.HomeRepositoryImpl
import com.reza.appmovies.data.repository.datasource.HomeDataSource
import com.reza.appmovies.data.repository.datasource.HomeRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
class HomeModule {

    @Provides
    fun provideHomeRepository(homeRemoteDataSource: HomeDataSource): HomeRepository =
        HomeRepositoryImpl(homeRemoteDataSource)

    @Provides
    fun provideHomeDataSource(apiService: ApiService): HomeDataSource =
        HomeRemoteDataSource(apiService)
}