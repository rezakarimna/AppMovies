package com.reza.appmovies.di

import com.appexample.DataManager
import com.appexample.Repo
import com.appexample.textInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
abstract class textmodule {

    @Binds
    abstract fun bindText(repo: Repo) : textInterface
}