package com.example.moviedbapp.framework.di

import com.example.moviedbapp.data.repositories.PeliculasRepository
import com.example.moviedbapp.data.source.PeliculaLocalDataSource
import com.example.moviedbapp.data.source.PeliculaRemoteDataSource
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
        peliculaLocalDataSource: PeliculaLocalDataSource,
        peliculaRemoteDataSource: PeliculaRemoteDataSource,
        @Named("apiKey") apiKey: String
    ) = PeliculasRepository(peliculaLocalDataSource, peliculaRemoteDataSource, apiKey)
}