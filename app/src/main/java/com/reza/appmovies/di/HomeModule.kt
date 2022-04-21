package com.reza.appmovies.di

import com.reza.appmovies.data.repository.HomeRepository
import com.reza.appmovies.data.repository.HomeRepositoryImpl
import com.reza.appmovies.data.repository.datasource.HomeDataSource
import com.reza.appmovies.data.repository.datasource.HomeRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
abstract class HomeModule {

    @Binds
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindHomeDataSource(homeRemoteDataSource: HomeRemoteDataSource): HomeDataSource


}