package com.example.moviedbapp.framework.di

import com.example.moviedbapp.data.repositories.PeliculasRepository
import com.example.moviedbapp.data.source.local.PeliculaLocalDataSource
import com.example.moviedbapp.data.source.remote.PeliculaRemoteDataSource
import com.example.moviedbapp.framework.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun peliculasRepositoryProvider(
        database: AppDatabase,
        peliculaLocalDataSource: PeliculaLocalDataSource,
        peliculaRemoteDataSource: PeliculaRemoteDataSource
    ) = PeliculasRepository(database, peliculaLocalDataSource, peliculaRemoteDataSource)
}